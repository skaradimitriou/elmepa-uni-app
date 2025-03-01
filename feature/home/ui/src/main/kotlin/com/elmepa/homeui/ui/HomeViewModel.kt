package com.elmepa.homeui.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.homedomain.usecase.FetchDashboardDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    fetchDashboardDetailsUseCase: FetchDashboardDetailsUseCase
) : ViewModel() {

    val dashboardDetails: StateFlow<HomeView.State>
        get() = _dashboardDetails.asStateFlow()

    private val _dashboardDetails: MutableStateFlow<HomeView.State> = MutableStateFlow(HomeView.State.Loading)

    private val _effect = MutableSharedFlow<HomeView.Effect>()
    val effect: SharedFlow<HomeView.Effect> = _effect.asSharedFlow()

    init {
        fetchDashboardDetailsUseCase()
            .onEach { data ->
                _dashboardDetails.update { HomeView.State.Content(data) }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onAction(action: HomeView.UIAction) = when (action) {
        is HomeView.UIAction.OptionTap -> {
            viewModelScope.launch {
                _effect.emit(HomeView.Effect.OpenDashboardOption(action.option))
            }
        }
    }
}

