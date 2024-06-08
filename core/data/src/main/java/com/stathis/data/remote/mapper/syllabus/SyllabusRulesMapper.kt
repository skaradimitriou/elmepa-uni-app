package com.stathis.data.remote.mapper.syllabus

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.remote.mapper.BaseMapper
import com.stathis.data.remote.model.syllabus.SyllabusRuleResponseDto
import com.stathis.model.syllabus.SyllabusRule

object SyllabusRulesMapper : BaseMapper<SyllabusRuleResponseDto?, List<SyllabusRule>> {

    override fun toDomainModel(dtoModel: SyllabusRuleResponseDto?) =
        dtoModel?.semesterRules.toListOf {
            SyllabusRule(
                semester = it.semester.toNotNull(),
                description = it.description.toNotNull()
            )
        }
}