package com.stathis.elmepaunivapp.ui.main.students

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.StudentResponse
import java.io.IOException

class StudentsRepository(val app : Application) {

    /*
     * Carousel -> Schedule, Academic Schedule, Mentor Program, Erasmus+
     * This should change automatically after 2 seconds
     *
     * Syllabus -> Redesign the 3 major streams to be more UI friendly
     *
     * Useful links -> Vertical Scroll & redesign to be more simple like a list with many options
     *
     * So, the parent model will have a list of:
     *
     * CarouselItem (3 items)
     * SyllabusItem (3 items)
     * UsefulLinksItem (12 items)
     *
     */

    val professors = MutableLiveData<StudentResponse>()
    private lateinit var studentScreenData : StudentResponse

    init {
        getStudentsScreenData()
    }

    fun getStudentsScreenData() {
        try {
            val jsonString = app.assets.open("students_screen.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<StudentResponse>() {}.type
            studentScreenData  = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),studentScreenData.toString())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}