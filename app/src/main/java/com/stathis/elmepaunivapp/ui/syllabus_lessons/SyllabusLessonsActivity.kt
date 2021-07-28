package com.stathis.elmepaunivapp.ui.syllabus_lessons

import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import kotlinx.android.synthetic.main.activity_syllabus_lessons.*

class SyllabusLessonsActivity : ElmepaActivity(R.layout.activity_syllabus_lessons) {

    private lateinit var viewModel : SyllabusLessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusLessonsViewModel::class.java)
    }

    override fun startOps() {
        syllabus_lessons_recycler.adapter = viewModel.adapter

        val lessonInfo = intent.getStringExtra("LESSONS_INFO")
        val lessonArray = intent.getStringExtra("ARRAY")

        when(lessonInfo.isNullOrEmpty()){
            false -> {
                when(lessonArray.isNullOrEmpty()){
                    false -> viewModel.createList(lessonInfo,lessonArray)
                }
            }
        }
    }

    override fun stopOps() {}
}