package com.stathis.syllabus.ui.lessons

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.model.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchLessonsUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
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
    private val useCase: FetchLessonsUseCase
) : BaseViewModel(app) {

    val lessons: StateFlow<NetworkResult<List<UiModel>>>
        get() = _lessons

    private val _lessons = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchLessonsForSemesterAndOrientation(semesterName: String, orientation: OrientationType) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(semesterName, orientation).collect { data ->
                _lessons.emit(data)
            }
        }
    }
}