package com.stathis.syllabus.util

import com.stathis.model.syllabus.ProgrammeType

/**
 * Helper fun to transform the tab position to a [ProgrammeType].
 */

fun Int.toProgrammeType() = when (this) {
    0 -> ProgrammeType.UNDERGRADUATE
    1 -> ProgrammeType.POSTGRADUATE
    else -> ProgrammeType.UNDEFINED
}

/**
 * Helper fun to transform a [ProgrammeType] to the tab position.
 */

fun ProgrammeType?.toTabPosition() = when (this) {
    ProgrammeType.UNDEFINED -> 0
    ProgrammeType.UNDERGRADUATE -> 0
    ProgrammeType.POSTGRADUATE -> 1
    null -> 0
}