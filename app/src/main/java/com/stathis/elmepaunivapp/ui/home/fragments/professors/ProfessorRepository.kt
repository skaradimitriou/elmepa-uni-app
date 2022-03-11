package com.stathis.elmepaunivapp.ui.home.fragments.professors

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.ui.home.fragments.professors.model.Professor
import java.io.IOException
import java.util.*

class ProfessorRepository(val app : Application) {

    val professors = MutableLiveData<List<Professor>>()
    private lateinit var professorList : List<Professor>

    init {
        getProfessorListFromAssets()
    }

    fun getProfessorListFromAssets() {
            try {
                val jsonString = app.assets.open("professors.json").bufferedReader().use { it.readText() }
                val listPersonType = object : TypeToken<List<Professor>>() {}.type
                professorList = Gson().fromJson(jsonString, listPersonType)
                Log.d(app.getString(R.string.app_name),professorList.toString())
            } catch (ioException: IOException) {
                ioException.printStackTrace()
            }
    }

    fun getProfessors(){
        Collections.sort(professorList) { p0, p1 ->
            p0?.fullName!!.compareTo(p1!!.fullName)
        }

        professors.value = professorList
    }

    fun filter(text: String) {
        val filteredList = arrayListOf<Professor>()
        for (item in professorList) {
            if (item.fullName.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        professors.value = filteredList
    }
}