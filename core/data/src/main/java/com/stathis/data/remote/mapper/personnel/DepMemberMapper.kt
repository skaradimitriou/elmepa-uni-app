package com.stathis.data.remote.mapper.personnel

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.personnel.DepMemberDto
import com.stathis.data.remote.model.personnel.SkillDto
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
        skills = this?.skills.toDomainModel()
    )

    @JvmName("toSkillDomainModel")
    private fun List<SkillDto>?.toDomainModel() = toListOf {
        Skill(
            title = it.title.toNotNull(),
            value = it.value?.toIntOrNull().toNotNull(),
        )
    }
}