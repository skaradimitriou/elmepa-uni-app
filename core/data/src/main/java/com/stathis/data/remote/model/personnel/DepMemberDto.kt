package com.stathis.data.remote.model.personnel

data class DepMemberDto(
    val image: String? = null,
    val fullName: String? = null,
    val profession: String? = null,
    val description: String? = null,
    val linkToResume: String? = null,
    val skills: List<SkillDto>? = null,
    val links: List<String?>? = null
)

data class SkillDto(
    val title: String? = null,
    val value: String? = null
)