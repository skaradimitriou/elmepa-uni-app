package com.elmepa.syllabus.programmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.syllabus.const.toTabPosition
import com.elmepa.syllabus.programmes.ProgrammesView.Effect
import com.elmepa.syllabus.programmes.ProgrammesView.Effect.ChangeSelectedTab
import com.elmepa.syllabus.programmes.ProgrammesView.Effect.NavigateToLessonList
import com.elmepa.syllabus.programmes.ProgrammesView.State
import com.elmepa.syllabus.programmes.ProgrammesView.UIAction
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.FetchSemestersUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProgrammesViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchSemestersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    fun fetchSemestersByProgramme(programme: ProgrammeType?, orientation: OrientationType? = null) {
        useCase.invoke(programme, orientation)
            .onStart {
                _state.update { State.Loading }
            }
            .onEach { result ->
                _state.update { result.toUiState(programme) }
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    fun onAction(action: UIAction) {
        viewModelScope.launch(dispatcher) {
            val effect = when (action) {
                is UIAction.OnSemesterClick -> NavigateToLessonList(action.semester)

                is UIAction.OnTabSelection -> {
                    _state.update {
                        (it as State.Content).copy(selectedTabPosition = action.tabIndex)
                    }
                    ChangeSelectedTab(action.tabIndex)
                }
            }

            _effect.emit(effect)
        }
    }

    private fun NetworkResult<List<Programme>>.toUiState(programme: ProgrammeType?) = when (this) {
        is NetworkResult.Loading -> State.Loading

        is NetworkResult.Success -> {
            val selectedTabPosition = programme.toTabPosition()
            State.Content(
                selectedTabPosition = selectedTabPosition,
                programmes = data?.mapIndexed { index, elem ->
                    elem.copy(isExpanded = index == selectedTabPosition)
                }?.toList().orEmpty()
            )
        }

        is NetworkResult.Failure -> State.Error
    }
}
