package com.elmepa.support.model

data class Faq(
    val question: String,
    val answer: String,
    val seq: Int,
    var isExpanded: Boolean = false
)
