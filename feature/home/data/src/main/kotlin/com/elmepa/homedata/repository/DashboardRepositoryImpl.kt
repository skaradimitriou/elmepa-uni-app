package com.elmepa.homedata.repository

import android.app.Application
import com.elmepa.homedomain.model.DashboardCard
import com.elmepa.homedomain.model.DashboardCardType
import com.elmepa.homedomain.repository.DashboardRepository
import com.stathis.common.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class DashboardRepositoryImpl @Inject constructor(
    private val app: Application
) : DashboardRepository {

    override fun fetchDashboardOptions(): Flow<List<DashboardCard>> = flow {
        val list = listOf(
            DashboardCard(
                seq = 1,
                title = app.getString(R.string.announcements),
                imageRes = R.drawable.home_announcements,
                type = DashboardCardType.ANNOUNCEMENTS
            ),
            DashboardCard(
                seq = 2,
                title = app.getString(R.string.events),
                imageRes = R.drawable.home_events,
                type = DashboardCardType.EVENTS
            ),
            DashboardCard(
                seq = 3,
                title = app.getString(R.string.students),
                imageRes = R.drawable.home_students,
                type = DashboardCardType.STUDENTS
            ),
            DashboardCard(
                seq = 4,
                title = app.getString(R.string.department),
                imageRes = R.drawable.home_department,
                type = DashboardCardType.DEPARTMENT
            ),
            DashboardCard(
                seq = 5,
                title = app.getString(R.string.application_forms_title),
                imageRes = R.drawable.application_forms,
                type = DashboardCardType.APPLICATION_FORMS
            ),
//            DashboardCard(
//                title = app.getString(R.string.research_in_dept),
//                imageRes = R.drawable.home_research,
//                type = DashboardCardType.RESEARCH
//            ),
            DashboardCard(
                seq = 6,
                title = app.getString(R.string.faq_title),
                imageRes = R.drawable.home_faq,
                type = DashboardCardType.FAQ
            ),
            DashboardCard(
                seq = 7,
                title = app.getString(R.string.contact),
                imageRes = R.drawable.contact,
                type = DashboardCardType.CONTACT
            ),
            DashboardCard(
                seq = 8,
                title = app.getString(R.string.about_app_title),
                imageRes = R.drawable.about_app,
                type = DashboardCardType.ABOUT
            )
        )
        emit(list)
    }.flowOn(Dispatchers.IO)
}
