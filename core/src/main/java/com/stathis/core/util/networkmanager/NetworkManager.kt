package com.stathis.core.util.networkmanager

import kotlinx.coroutines.flow.StateFlow

/**
 * Base interface used for handling network changes inside the app.
 */

interface NetworkManager {

    /**
     * Initialization of the mechanism.
     */

    fun build()


    /**
     * Register on this method to acquire the current [NetworkStatus]
     */

    fun listenForNetworkStatusChanges(): StateFlow<NetworkStatus>

    /**
     * Unregisters the callback registered on build method.
     */

    fun unregisterCallback()
}