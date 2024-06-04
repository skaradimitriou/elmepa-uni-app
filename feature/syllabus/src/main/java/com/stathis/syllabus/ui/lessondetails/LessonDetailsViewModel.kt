package com.stathis.syllabus.ui.lessondetails

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchLessonDetailsUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.Lesson
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchLessonDetailsUseCase
) : BaseViewModel(app) {

    val lessonDetails: StateFlow<NetworkResult<List<Lesson>>>
        get() = _lessonDetails

    private val _lessonDetails =
        MutableStateFlow<NetworkResult<List<Lesson>>>(NetworkResult.Loading())

    fun fetchLessonDetails(programmeType: ProgrammeType, lessonName: String) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(programmeType, lessonName).collect { data ->
                _lessonDetails.emit(data)
            }
        }
    }
}