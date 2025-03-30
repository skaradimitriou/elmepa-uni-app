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
        image = this?.image.orEmpty(),
        fullName = this?.fullName.orEmpty(),
        profession = this?.profession.orEmpty().lowercase().uppercase(),
        description = this?.description.orEmpty(),
        linkToResume = this?.linkToResume.orEmpty(),
        skills = this?.skills.toDomainModel(),
        links = this?.links.toDomainModel().plusResumeItem(this?.linkToResume)
    )

    @JvmName("toSkillDomainModel")
    private fun List<SkillDto>?.toDomainModel() = toListOf {
        Skill(
            title = it.title.orEmpty().lowercase().uppercase(),
            value = it.value?.toIntOrNull().toNotNull(),
        )
    }

    @JvmName("toLinkDomainModel")
    private fun List<String?>?.toDomainModel() = this?.mapIndexed { position, url ->
        Link(
            title = url.toLinkTitle(),
            openUrl = url.toCleanUrl(),
            type = position.toLinkType()
        )
    } ?: listOf()

    private fun String?.toLinkTitle() = this?.let { value ->
        when {
            value.contains("linkedin") -> "Linkedin"
            value.contains("researchgate") -> "ResearchGate"
            value.contains("scholar.google") -> "Google Scholar"
            else -> "E-mail"
        }
    } ?: run { this.orEmpty() }

    private fun List<Link>.plusResumeItem(linkToResume: String? = null) = linkToResume?.let { resumeUrl ->
        this.plus(
            Link(
                title = "Βιογραφικό",
                openUrl = resumeUrl,
                type = LinkType.CV
            )
        )
    } ?: run { this }

    fun String?.toCleanUrl() = if (this?.contains("mail") == true) {
        substringAfter("mailto:")
    } else {
        this.orEmpty()
    }

    private fun Int?.toLinkType() = when (this) {
        0 -> LinkType.MAIL
        1 -> LinkType.RESEARCH_GATE
        2 -> LinkType.LINKEDIN
        3 -> LinkType.GOOGLE_SCHOLAR
        else -> LinkType.UNKNOWN
    }
}
