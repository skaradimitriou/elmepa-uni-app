package com.stathis.elmepaunivapp.ui.syllabus

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import java.io.IOException

class SyllabusRepository(val app : Application){

    val data = MutableLiveData<List<Semester>>()
    private lateinit var model : List<Semester>

    init {
        getBaList()
    }

    fun getBaList() {
        try {
            val jsonString = app.assets.open("undergraduate_ba_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun getDataList() {
        try {
            val jsonString = app.assets.open("undergraduate_data_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    fun getMktList() {
        try {
            val jsonString = app.assets.open("undergraduate_mkt_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}
