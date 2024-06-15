package com.stathis.data.remote.datasource

import com.stathis.data.remote.mapper.personnel.DepMemberMapper
import com.stathis.data.remote.model.personnel.DepMemberDto
import com.stathis.data.remote.model.personnel.SkillDto
import com.stathis.data.util.DATA_WIDTH
import com.stathis.data.util.DEP_BTN
import com.stathis.data.util.DEP_BTN_A
import com.stathis.data.util.DEP_IMG
import com.stathis.data.util.DEP_INFO
import com.stathis.data.util.DEP_MEMBERS_URL
import com.stathis.data.util.DEP_SKILLS
import com.stathis.data.util.DEP_SKILL_AMOUNT
import com.stathis.data.util.DEP_SKILL_CONTAINER
import com.stathis.data.util.DEP_SPAN_TITLE
import com.stathis.data.util.H3
import com.stathis.data.util.H4
import com.stathis.data.util.IMG_SOURCE
import com.stathis.data.util.IMG_TYPE
import com.stathis.data.util.LI
import com.stathis.data.util.MAIN_CONTENT
import com.stathis.data.util.PERCENT
import com.stathis.data.util.P_TAG
import com.stathis.data.util.ROW
import com.stathis.data.util.SECTION
import com.stathis.data.util.URL_ATTR
import com.stathis.model.department.DepMember
import com.stathis.model.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class DepartmentDataSourceImpl : DepartmentDataSource {

    override suspend fun fetchDepMembers(): Flow<NetworkResult<List<DepMember>>> = flow {
        try {
            val dtoModels = Jsoup.connect(DEP_MEMBERS_URL).get()
                .select(MAIN_CONTENT).select(SECTION).select(ROW).map { html ->
                    val image = html.select(DEP_IMG).select(IMG_TYPE).attr(IMG_SOURCE)
                    val profession = html.select(DEP_INFO).select(H4).text()
                    val fullName = html.select(DEP_INFO).select(H3).text()
                    val description = html.select(DEP_INFO).select(P_TAG).text()
                    val linkToResume = html.select(DEP_BTN).select(DEP_BTN_A).attr(URL_ATTR)

                    val skills = html.select(DEP_SKILLS).select(LI)
                        .map { skill ->
                            val title = skill.select(DEP_SPAN_TITLE).text()
                            val value = skill.select(DEP_SKILL_CONTAINER)
                                .select(DEP_SKILL_AMOUNT)
                                .attr(DATA_WIDTH)
                                .removeSuffix(PERCENT)

                            SkillDto(title, value)
                        }

                    DepMemberDto(image, fullName, profession, description, linkToResume, skills)
                }

            val domainModels = DepMemberMapper.toDomainModel(dtoModels)
            emit(NetworkResult.Success(domainModels))
        } catch (e: Exception) {
            emit(NetworkResult.Failure(e.localizedMessage))
        }
    }
}