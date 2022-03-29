package com.stathis.elmepaunivapp.ui.home.fragments.professors

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.ui.home.fragments.professors.model.Professor
import com.stathis.elmepaunivapp.ui.home.fragments.professors.recyclerview.ProfessorsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class ProfessorViewModel(val app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val professors = MutableLiveData<List<Professor>>()
    private lateinit var professorList : List<Professor>
    val adapter = ProfessorsAdapter(this)
    private lateinit var callback : ProfessorScreenClickListener

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
            professors.value = listOf()
        }
    }

    fun getProfessors(callback : ProfessorScreenClickListener) {
        this.callback = callback

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
            professors.value = professorList
    }

    fun observeData(owner : LifecycleOwner) {
        professors.observe(owner, Observer{
            adapter.submitList(it)
        })
    }

    fun removeObserver(owner : LifecycleOwner){
        professors.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is Professor -> callback.openDialog(view.tag as Professor)
        }
    }
}
