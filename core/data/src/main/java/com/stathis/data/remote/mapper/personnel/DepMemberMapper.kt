package com.stathis.data.remote.mapper.personnel

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.personnel.DepMemberDto
import com.stathis.data.remote.model.personnel.SkillDto
import com.stathis.model.common.Link
import com.stathis.model.common.LinkType
import com.stathis.model.department.DepMember
import com.stathis.model.department.Skill


object DepMemberMapper : BaseMapper<List<DepMemberDto>?, List<DepMember>> {

    override fun toDomainModel(dtoModel: List<DepMemberDto>?) = dtoModel.toListOf {
        it.toDomainModel()
    }

    @JvmName("toDepMemberDomainModel")
    private fun DepMemberDto?.toDomainModel() = DepMember(
        image = this?.image.toNotNull(),
        fullName = this?.fullName.toNotNull(),
        profession = this?.profession.toNotNull(),
        description = this?.description.toNotNull(),
        linkToResume = this?.linkToResume.toNotNull(),
        skills = this?.skills.toDomainModel(),
        links = this?.links.toDomainModel()
    )

    @JvmName("toSkillDomainModel")
    private fun List<SkillDto>?.toDomainModel() = toListOf {
        Skill(
            title = it.title.toNotNull(),
            value = it.value?.toIntOrNull().toNotNull(),
        )
    }

    @JvmName("toLinkDomainModel")
    private fun List<String?>?.toDomainModel() = toListOf { url ->
        Link(
            openUrl = url.toCleanUrl(),
            type = url.toLinkType()
        )
    }

    private fun String?.toCleanUrl() = if (this?.contains("mail") == true) {
        substringAfter("mailto:")
    } else {
        this.toNotNull()
    }

    private fun String?.toLinkType() = when {
        this?.contains("mail") == true -> LinkType.MAIL
        this?.contains("linkedin") == true -> LinkType.LINKEDIN
        this?.contains("research") == true -> LinkType.RESEARCH_GATE
        this?.contains("google") == true -> LinkType.GOOGLE_SCHOLAR
        else -> LinkType.UNKNOWN
    }
}