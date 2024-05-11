package com.stathis.core.util.networkmanager

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Implementation of the [NetworkManager] interface.
 */

class NetworkManagerImpl @Inject constructor(
    app: Application
) : NetworkManager {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val job = scope.coroutineContext.job

    private val connectivityManager =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _networkStatus = MutableStateFlow<NetworkStatus>(NetworkStatus.Undefined)

    private var isOnFirstLaunch = true

    override fun listenForNetworkStatusChanges() = _networkStatus

    private val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onUnavailable() = updateNetworkStatus(NetworkStatus.Unavailable)

        override fun onAvailable(network: Network) {
            if (isOnFirstLaunch) {
                updateNetworkStatus(NetworkStatus.Available)
            } else {
                updateNetworkStatus(NetworkStatus.Restored)
            }
        }

        override fun onLost(network: Network) = updateNetworkStatus(NetworkStatus.Unavailable)
    }

    init {
        build()
    }

    override fun build() {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkStatusCallback)
    }

    private fun updateNetworkStatus(status: NetworkStatus, firstLaunch: Boolean = false) {
        isOnFirstLaunch = firstLaunch
        scope.launch {
            _networkStatus.emit(status)
            delay(300)
            _networkStatus.emit(NetworkStatus.Undefined)
        }
    }

    override fun unregisterCallback() {
        job.cancel()
        connectivityManager.unregisterNetworkCallback(networkStatusCallback)
    }
}