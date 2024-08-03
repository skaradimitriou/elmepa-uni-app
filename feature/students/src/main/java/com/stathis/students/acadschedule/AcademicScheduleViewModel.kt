package com.stathis.students.acadschedule

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.students.FetchAcademicScheduleUseCase
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcademicScheduleViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchAcademicScheduleUseCase
) : BaseViewModel(app) {

    val schedule: StateFlow<NetworkResult<List<UiModel>>>
        get() = _schedule.asStateFlow()

    private val _schedule = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchAcademicSchedule() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { data ->
                _schedule.emit(data)
            }
        }
    }
}