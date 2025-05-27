package com.elmepa.news.events

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.elmepa.news.events.EventsView.Effect
import com.elmepa.news.events.EventsView.UIAction
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.news.FetchEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EventsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    eventsUseCase: FetchEventsUseCase
) : BaseViewModel(app) {

    val events = eventsUseCase()
        .flow
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun onAction(action: UIAction) {
        viewModelScope.launch(Dispatchers.Default) {
            val effect = when (action) {
                is UIAction.OnEventTap -> Effect.NavigateToDetails(action.event)
                is UIAction.Back -> Effect.Back
            }
            _effect.emit(effect)
        }
    }
}
