package com.stathis.news.announcements

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.news.FetchAnnouncementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    useCase: FetchAnnouncementsUseCase
) : BaseViewModel(app) {

    val announcements = useCase.invoke().flow
        .flowOn(dispatcher)
        .cachedIn(viewModelScope)
}
