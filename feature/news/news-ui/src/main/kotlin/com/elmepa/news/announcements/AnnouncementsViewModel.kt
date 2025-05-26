package com.elmepa.news.announcements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.elmepa.news.announcements.AnnouncementsView.Effect
import com.elmepa.news.announcements.AnnouncementsView.UIAction
import com.stathis.domain.news.FetchAnnouncementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AnnouncementsViewModel @Inject constructor(
    fetchAnnouncementsUseCase: FetchAnnouncementsUseCase
) : ViewModel() {

    val announcements = fetchAnnouncementsUseCase().flow
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun onAction(action: UIAction) {
        viewModelScope.launch(Dispatchers.Default) {
            val effect = when (action) {
                is UIAction.OnAnnouncementTap -> Effect.NavigateToDetails(action.announcement)
                is UIAction.Back -> Effect.Back
            }
            _effect.emit(effect)
        }
    }
}

