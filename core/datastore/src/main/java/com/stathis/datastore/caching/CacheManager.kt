package com.stathis.datastore.caching

/**
 * Helper class used to store & retrieve cache timestamps from/to local storage.
 */
interface CacheManager {

    suspend fun saveCacheTimestamp(key: String, timestamp: Long)

    suspend fun getCacheTimestamp(key: String): Long

    suspend fun shouldRefresh(key: String): Boolean
}
