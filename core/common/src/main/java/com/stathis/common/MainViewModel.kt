package com.stathis.common

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.common.util.networkmanager.NetworkManager
import com.stathis.model.navigation.NavigationAction
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
    private val networkManager: NetworkManager
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

    override fun onCleared() {
        networkManager.unregisterCallback()
        super.onCleared()
    }

    data class NavModel(
        val action: NavigationAction? = null,
        val bundle: Bundle? = null
    )
}