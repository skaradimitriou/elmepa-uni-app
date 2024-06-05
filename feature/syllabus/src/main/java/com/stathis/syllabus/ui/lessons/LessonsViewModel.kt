package com.stathis.syllabus.ui.lessons

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: com.stathis.domain.FetchLessonsUseCase
) : BaseViewModel(app) {

    val lessons: StateFlow<NetworkResult<List<com.stathis.model.UiModel>>>
        get() = _lessons

    private val _lessons = MutableStateFlow<NetworkResult<List<com.stathis.model.UiModel>>>(NetworkResult.Loading())

    fun fetchLessonsByFields(
        programme: ProgrammeType,
        orientation: OrientationType,
        semesterName: String,
    ) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(programme, orientation, semesterName).collect { data ->
                _lessons.emit(data)
            }
        }
    }
}