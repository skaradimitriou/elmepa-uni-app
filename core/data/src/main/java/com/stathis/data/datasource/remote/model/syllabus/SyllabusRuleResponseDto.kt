package com.stathis.data.datasource.remote.model.syllabus

data class SyllabusRuleResponseDto(
    val programmeType: String? = null,
    val semesterRules: List<SyllabusRuleDto>? = null
)

data class SyllabusRuleDto(
    val semester: String? = null,
    val description: String? = null
)