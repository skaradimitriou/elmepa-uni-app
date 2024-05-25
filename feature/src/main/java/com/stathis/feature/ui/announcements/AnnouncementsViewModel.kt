package com.stathis.feature.ui.announcements

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.model.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchAnnouncementsUseCase
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchAnnouncementsUseCase
) : BaseViewModel(app) {

    val announcements: StateFlow<NetworkResult<List<UiModel>>>
        get() = _announcements

    private val _announcements =
        MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchAnnouncements(forceUpdate: Boolean? = false) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(forceUpdate).collect { data ->
                _announcements.emit(data)
            }
        }
    }
}
