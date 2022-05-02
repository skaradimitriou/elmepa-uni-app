package com.stathis.elmepaunivapp.ui.lessons

import android.app.Application
import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.LessonsClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.model.syllabus.Semester
import com.stathis.elmepaunivapp.ui.lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.lessons.model.Lesson
import com.stathis.elmepaunivapp.ui.lessons.adapter.LessonsAdapter

class LessonsViewModel(app : Application) : ElmepaViewModel(app), ElmepaClickListener {

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