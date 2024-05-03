package com.stathis.data.repository

import android.app.Application
import com.stathis.domain.repository.SyllabusRepository
import com.stathis.model.syllabus.Orientation
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Semester
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SyllabusRepositoryImpl @Inject constructor(
    private val app: Application
) : SyllabusRepository {

    override suspend fun fetchSemesters(): Flow<List<Orientation>> = flow {
        val semesters = listOf(
            Semester("Εξάμηνο Α"),
            Semester("Εξάμηνο Β"),
            Semester("Εξάμηνο Γ"),
            Semester("Εξάμηνο Δ"),
            Semester("Εξάμηνο Ε"),
            Semester("Εξάμηνο ΣΤ"),
            Semester("Εξάμηνο Ζ"),
            Semester("Εξάμηνο Η"),
        )

        val orientations = listOf(
            Orientation(
                title = app.getString(com.stathis.core.R.string.data_orientation),
                semesters = semesters,
                type = OrientationType.DATA,
                isExpanded = true
            ),
            Orientation(
                title = app.getString(com.stathis.core.R.string.ba_orientation),
                semesters = semesters,
                type = OrientationType.BA
            ),
            Orientation(
                title = app.getString(com.stathis.core.R.string.mkt_orientation),
                semesters = semesters,
                type = OrientationType.MARKETING
            ),
        )

        emit(orientations)
    }
}