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

    private lateinit var model : List<Semester>

    fun getBaList(data : MutableLiveData<List<Semester>>,error : MutableLiveData<Boolean>) {
        try {
            val jsonString = app.assets.open("undergraduate_ba_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            error.value = true
        }
    }

    fun getDataList(data : MutableLiveData<List<Semester>>,error : MutableLiveData<Boolean>) {
        try {
            val jsonString = app.assets.open("undergraduate_data_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            error.value = true
        }
    }

    fun getMktList(data : MutableLiveData<List<Semester>>,error : MutableLiveData<Boolean>) {
        try {
            val jsonString = app.assets.open("undergraduate_mkt_syllabus.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            error.value = true
        }
    }
}
