package com.stathis.announcements.events

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.announcements.FetchEventsUseCase
import com.stathis.model.UiModel
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
    private val eventsUseCase: FetchEventsUseCase
) : BaseViewModel(app) {

    val events: StateFlow<NetworkResult<List<UiModel>>>
        get() = _events

    private val _events =
        MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchDepartmentEvents(forceUpdate: Boolean? = false) {
        viewModelScope.launch(dispatcher) {
            eventsUseCase.invoke(forceUpdate).collect { data ->
                _events.emit(data)
            }
        }
    }
}