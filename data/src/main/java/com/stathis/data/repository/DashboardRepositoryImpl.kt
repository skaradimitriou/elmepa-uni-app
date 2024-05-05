package com.stathis.data.repository

import android.app.Application
import com.stathis.core.R
import com.stathis.core.base.UiModel
import com.stathis.domain.repository.DashboardRepository
import com.stathis.model.general.GeneralCardModel
import com.stathis.model.home.DashboardOption
import com.stathis.model.home.OptionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val app: Application
) : DashboardRepository {

    override suspend fun fetchDashboardDetails(): Flow<List<UiModel>> = flow {
        val list = listOf(
            GeneralCardModel(
                image = R.drawable.elmepa_logo,
                title = app.getString(R.string.dashboard_elmepa),
                description = app.getString(R.string.dashboard_det)
            ),
            DashboardOption(
                title = app.getString(R.string.announcements),
                drawable = R.drawable.home_announcements,
                type = OptionType.ANNOUNCEMENTS
            ),
            DashboardOption(
                title = app.getString(R.string.department),
                drawable = R.drawable.home_department,
                type = OptionType.DEPARTMENT
            ),
            DashboardOption(
                title = app.getString(R.string.students),
                drawable = R.drawable.home_students,
                type = OptionType.STUDENTS
            ),
            DashboardOption(
                title = app.getString(R.string.research_in_dept),
                drawable = R.drawable.home_research,
                type = OptionType.RESEARCH
            ),
            DashboardOption(
                title = app.getString(R.string.contact),
                drawable = R.drawable.contact,
                type = OptionType.CONTACT
            ),
            DashboardOption(
                title = app.getString(R.string.about_app_title),
                drawable = R.drawable.about_app,
                type = OptionType.ABOUT
            )
        )
        emit(list)
    }
}