package com.stathis.model.syllabus


/*
 * FIXME: refactor this later on
 */

data class Lesson(
    var name: String,
    val mandatory: Boolean,
    val description: String,
)