package com.elmepa.syllabus.lessons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.FetchLessonsUseCase
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.LessonHeader
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LessonListViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchLessonsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<LessonsView.State>(LessonsView.State.Init)
    val state: StateFlow<LessonsView.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LessonsView.Effect>()
    val effect: SharedFlow<LessonsView.Effect> = _effect.asSharedFlow()

    fun fetchLessonsByFields(
        programme: ProgrammeType,
        orientation: OrientationType,
        semesterName: String
    ) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(programme, orientation, semesterName)
                .onStart {
                    _state.update { LessonsView.State.Loading(semesterName) }
                }
                .collect { data ->
                    _state.update { data.toUiState(semesterName) }
                }
        }
    }

    fun onAction(action: LessonsView.UIAction) {
        viewModelScope.launch(dispatcher) {
            val effect = when (action) {
                is LessonsView.UIAction.LessonTap -> LessonsView.Effect.NavigateToLessonDetails(action.lessonName)
            }

            _effect.emit(effect)
        }
    }

    private fun NetworkResult<List<UiModel>>.toUiState(semester: String) = when (this) {
        is NetworkResult.Loading -> LessonsView.State.Loading(semester)
        is NetworkResult.Success -> LessonsView.State.Content(
            semester = semester,
            informativeText = data?.filterIsInstance<LessonHeader>()?.firstOrNull()?.title.orEmpty(),
            lessons = data?.filterIsInstance<Lesson>()?.toImmutableList() ?: persistentListOf()
        )

        is NetworkResult.Failure -> LessonsView.State.Error
    }
}
