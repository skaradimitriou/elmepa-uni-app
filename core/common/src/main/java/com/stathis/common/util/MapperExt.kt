package com.stathis.common.util

import com.google.firebase.firestore.QuerySnapshot

fun String?.toNotNull() = this ?: ""
fun Int?.toNotNull() = this ?: 0
fun Long?.toNotNull() = this ?: 0L
fun Float?.toNotNull() = this ?: 0.0f
fun Double?.toNotNull() = this ?: 0.0
fun Boolean?.toNotNull() = this ?: false

fun <T> List<T>?.toNotNull() = this ?: listOf()

fun <DtoModel, DomainModel> List<DtoModel>?.toListOf(
    performMapping: (DtoModel) -> DomainModel
): List<DomainModel> = this?.map { performMapping.invoke(it) } ?: listOf()

/**
 * Helper fun to transform firestore results to a list of a certain object.
 *
 * Usage:
 *
 * val documents = firestore.collection("XXX").get().await()
 * val list = documents.toListOf<Foo>()
 *
 * list is now either a list of foo objects or an empty list
 */
suspend inline fun <reified T> QuerySnapshot.toListOf(): List<T> = try {
    val list = mutableListOf<T>()
    documents.forEach { document ->
        val model = document.toObject(T::class.java)
        model?.let { list.add(it) }
    }
    list
} catch (e: Exception) {
    listOf()
}