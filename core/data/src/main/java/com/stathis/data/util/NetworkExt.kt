package com.stathis.data.util

import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.tasks.await

/**
 * Helper methods used to fire a specific query to FireStore db and map it
 * into a domain model in repository layer
 */

suspend inline fun <reified DataModel, DomainModel> getAndMapResponse(
    query: DocumentReference,
    mapInto: (DataModel?) -> DomainModel
): DomainModel {
    return try {
        val result = query.get().await().toObject(DataModel::class.java)
        mapInto.invoke(result)
    } catch (e: Exception) {
        mapInto.invoke(null)
    }
}
