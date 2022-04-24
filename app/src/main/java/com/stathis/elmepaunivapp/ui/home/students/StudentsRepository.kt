package com.stathis.elmepaunivapp.ui.home.students

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.home.students.model.StudentResponse
import java.io.IOException

class StudentsRepository(val app : Application) {

    val data = MutableLiveData<StudentResponse>()
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

            data.value = studentScreenData
        } catch (ioException: IOException) {
            //ioException.printStackTrace()
        }
    }
}