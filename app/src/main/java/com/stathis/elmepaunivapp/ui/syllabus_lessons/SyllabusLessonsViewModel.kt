package com.stathis.elmepaunivapp.ui.syllabus_lessons

import android.app.Application
import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.LessonsClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson
import com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler.LessonsAdapter

class SyllabusLessonsViewModel(app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = LessonsAdapter(this)
    private lateinit var callback : LessonsClickListener

    fun bindList(data : Semester){
        val list = arrayListOf<LocalModel>()
        list.addAll(data.lessons)
        list.add(0,LessonHeader(data.lessonInfo))

        adapter.submitList(list)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is Lesson -> callback.onLessonTap(view.tag as Lesson)
        }
    }
}