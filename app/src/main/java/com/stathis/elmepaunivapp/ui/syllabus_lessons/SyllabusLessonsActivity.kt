package com.stathis.elmepaunivapp.ui.syllabus_lessons

import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import kotlinx.android.synthetic.main.activity_syllabus_lessons.*

class SyllabusLessonsActivity : ElmepaActivity(R.layout.activity_syllabus_lessons) {

    private lateinit var viewModel : SyllabusLessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusLessonsViewModel::class.java)
    }

    override fun startOps() {
        syllabus_lessons_recycler.adapter = viewModel.adapter

        val semester = intent.getParcelableExtra<Semester>(resources.getString(R.string.syllabus_intent_data))

        semester?.let { viewModel.bindList(it) }
    }

    override fun stopOps() {}
}