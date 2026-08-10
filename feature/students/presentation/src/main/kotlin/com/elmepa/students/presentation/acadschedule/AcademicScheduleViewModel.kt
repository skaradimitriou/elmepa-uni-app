package com.elmepa.students.presentation.acadschedule

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.elmepa.students.presentation.acadschedule.AcademicScheduleView.Effect
import com.elmepa.students.presentation.acadschedule.AcademicScheduleView.State
import com.elmepa.students.presentation.acadschedule.AcademicScheduleView.UIAction
import com.stathis.common.base.BaseViewModel
import com.stathis.domain.students.FetchAcademicScheduleUseCase
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AcademicScheduleViewModel @Inject constructor(
    app: Application,
    fetchAcademicScheduleUseCase: FetchAcademicScheduleUseCase
) : BaseViewModel(app) {

    val state: StateFlow<State> = fetchAcademicScheduleUseCase()
        .map { result -> result.toUiState() }
        .onStart { emit(State.Loading) }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun onAction(action: UIAction) {
        viewModelScope.launch {
            val effect = when (action) {
                is UIAction.OnBackArrowTap -> Effect.GoBack
            }

            _effect.emit(effect)
        }
    }

    private fun NetworkResult<List<UiModel>>.toUiState() = when (this) {
        is NetworkResult.Loading -> State.Loading
        is NetworkResult.Success<List<UiModel>> -> State.Content(data!!)
        is NetworkResult.Failure -> State.Error
    }
}
