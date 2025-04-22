package com.elmepa.syllabus.const

import com.stathis.model.syllabus.ProgrammeType

/**
 * Helper fun to transform a [ProgrammeType] to the tab position.
 */
fun ProgrammeType?.toTabPosition() = when (this) {
    ProgrammeType.UNDERGRADUATE_MST -> 0
    ProgrammeType.POSTGRADUATE_MST -> 1
    else -> 0
}
