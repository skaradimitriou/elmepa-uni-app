package com.stathis.feature.ui

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.core.util.networkmanager.NetworkManager
import com.stathis.domain.usecase.TryReconnectingUseCase
import com.stathis.feature.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val networkManager: NetworkManager,
    private val useCase: TryReconnectingUseCase
) : BaseViewModel(app) {

    val networkStatus = networkManager.listenForNetworkStatusChanges()

    val navState: StateFlow<NavModel?>
        get() = _navState

    private val _navState = MutableStateFlow<NavModel?>(null)

    fun navigateWithAction(action: NavigationAction?, data: Bundle? = null) {
        viewModelScope.launch(dispatcher) {
            _navState.emit(NavModel(action, data))
        }
    }

    fun tryReconnecting() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke()
        }
    }

    override fun onCleared() {
        networkManager.unregisterCallback()
        super.onCleared()
    }

    data class NavModel(
        val action: NavigationAction? = null,
        val bundle: Bundle? = null
    )
}