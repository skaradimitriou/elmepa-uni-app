package com.stathis.elmepaunivapp.ui.syllabus_lessons

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.listeners.latest.LessonsClickListenerKt
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonHeader
import com.stathis.elmepaunivapp.ui.syllabus_lessons.model.LessonKt
import com.stathis.elmepaunivapp.ui.syllabus_lessons.recycler.LessonsAdapter

class SyllabusLessonsViewModel : ViewModel(), ElmepaClickListener {

    val adapter = LessonsAdapter(this)
    private lateinit var callback : LessonsClickListenerKt

    fun createList(lessonInfo: String, lessonArray: String) {
        val list = arrayListOf<LocalModel>()
        list.add(LessonHeader(lessonInfo))

        val gson = Gson().fromJson(lessonArray, JsonArray::class.java)

        gson.forEach {
            val item = Gson().fromJson(it, LessonKt::class.java)
            list.add(item)
        }

        Log.d("List",list.toString())
        adapter.submitList(list)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is LessonKt -> callback.onLessonTap(view.tag as LessonKt)
        }
    }
}