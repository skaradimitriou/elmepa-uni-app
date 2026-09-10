package com.elmepa.departmentv2.domain.model

data class DepartmentResponse(
    val featured: List<FeaturedItem>,
    val members: List<DepartmentMember>,
    val modules: List<DepartmentModule>,
    val programmes: List<DepartmentProgramme>,
    val social: List<SocialLink>
)

data class FeaturedItem(
    val imageUrl: String,
    val title: String,
    val description: String
)

data class DepartmentMember(
    val imageUrl: String,
    val fullName: String,
    val profession: String
)

data class DepartmentModule(
    val title: String,
    val description: String
)

data class DepartmentProgramme(
    val title: String,
    val description: String
)

data class SocialLink(
    val name: String
)
