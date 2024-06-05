package com.stathis.announcements.events

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val eventsUseCase: com.stathis.domain.announcements.FetchEventsUseCase
) : BaseViewModel(app) {

    val events: StateFlow<NetworkResult<List<com.stathis.model.UiModel>>>
        get() = _events

    private val _events =
        MutableStateFlow<NetworkResult<List<com.stathis.model.UiModel>>>(NetworkResult.Loading())

    fun fetchDepartmentEvents(forceUpdate: Boolean? = false) {
        viewModelScope.launch(dispatcher) {
            eventsUseCase.invoke(forceUpdate).collect { data ->
                _events.emit(data)
            }
        }
    }
}