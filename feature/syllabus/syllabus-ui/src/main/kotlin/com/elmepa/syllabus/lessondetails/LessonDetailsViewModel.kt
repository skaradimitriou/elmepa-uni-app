package com.elmepa.syllabus.lessondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.FetchLessonDetailsUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class LessonDetailsViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchLessonDetailsUseCase
) : ViewModel() {

    val state: StateFlow<LessonDetailsView.State>
        get() = _state

    private val _state = MutableStateFlow<LessonDetailsView.State>(LessonDetailsView.State.Loading)

    private val _effect = MutableSharedFlow<LessonDetailsView.Effect>()
    val effect: SharedFlow<LessonDetailsView.Effect> = _effect.asSharedFlow()

    fun onAction(action: LessonDetailsView.UIAction) {
        viewModelScope.launch {
            when (action) {
                is LessonDetailsView.UIAction.GoBack -> _effect.emit(LessonDetailsView.Effect.GoBack)
            }
        }
    }

    fun fetchLessonDetails(programmeType: ProgrammeType, lessonName: String) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(programmeType, lessonName)
                .onEach { result ->
                    val state = when (result) {
                        is NetworkResult.Loading -> LessonDetailsView.State.Loading

                        is NetworkResult.Success -> {
                            result.data?.firstOrNull()?.let { lesson ->
                                LessonDetailsView.State.Content(
                                    lessonName = lesson.name,
                                    commitment = lesson.hours,
                                    lessonDescription = lesson.description,
                                    credits = lesson.credits
                                )
                            } ?: LessonDetailsView.State.Error
                        }

                        is NetworkResult.Failure -> LessonDetailsView.State.Error
                    }
                    _state.update { state }
                }
                .launchIn(viewModelScope)
        }
    }
}
