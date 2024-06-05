package com.stathis.common.util.networkmanager

sealed class NetworkStatus {
    data object Undefined : NetworkStatus()
    data object Available : NetworkStatus()
    data object Restored : NetworkStatus()
    data object Unavailable : NetworkStatus()
}