package com.stathis.elmepaunivapp.ui.syllabus_lessons

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.LessonsClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.Lesson
import com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler.LessonsAdapter

class SyllabusLessonsViewModel(app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = LessonsAdapter(this)
    private lateinit var callback : LessonsClickListener

    fun createList(lessonInfo: String, lessonArray: String) {
        val list = arrayListOf<LocalModel>()
        list.add(LessonHeader(lessonInfo))

        val gson = Gson().fromJson(lessonArray, JsonArray::class.java)

        gson.forEach {
            val item = Gson().fromJson(it, Lesson::class.java)
            list.add(item)
        }

        Log.d("List",list.toString())
        adapter.submitList(list)
    }



    override fun onItemClick(view: View) {
        when(view.tag){
            is Lesson -> callback.onLessonTap(view.tag as Lesson)
        }
    }
}