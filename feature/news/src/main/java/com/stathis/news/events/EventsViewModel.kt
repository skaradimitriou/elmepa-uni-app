package com.stathis.news.events

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.news.FetchEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    eventsUseCase: FetchEventsUseCase
) : BaseViewModel(app) {

    val events = eventsUseCase.invoke()
        .flow
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)
}