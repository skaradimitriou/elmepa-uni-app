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

    fun decideWhichList(position : Int,data : MutableLiveData<List<Semester>>, error: MutableLiveData<Boolean>) = when(position){
        0 -> getLocalData("undergraduate_data_syllabus.json",data,error)
        1 -> getLocalData("undergraduate_ba_syllabus.json",data,error)
        2 -> getLocalData("undergraduate_mkt_syllabus.json",data,error)
        else -> Unit
    }

    fun getLocalData(fileName : String, data : MutableLiveData<List<Semester>>,error : MutableLiveData<Boolean>){
        try {
            val jsonString = app.assets.open(fileName).bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            error.value = true
        }
    }
}
