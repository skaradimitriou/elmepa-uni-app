package com.stathis.elmepaunivapp.ui.home.professors

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.model.professor.Professor
import com.stathis.elmepaunivapp.ui.home.professors.adapter.ProfessorsAdapter
import com.stathis.elmepaunivapp.util.ShimmerHelper
import com.stathis.elmepaunivapp.util.equalsName
import com.stathis.elmepaunivapp.util.readLocalJson
import com.stathis.elmepaunivapp.util.sortedAlphabetically
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ProfessorViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val professors = MutableLiveData<List<Professor>>()
    private lateinit var professorList: List<Professor>
    val adapter = ProfessorsAdapter(this)
    private lateinit var callback: ProfessorScreenClickListener

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private fun startShimmer() {
        adapter.submitList(ShimmerHelper.list)
    }

    fun getData() {
        startShimmer()
        try {
            val json = app.readLocalJson("professors.json")
            val type = object : TypeToken<List<Professor>>() {}.type
            professorList = Gson().fromJson(json, type)
            professors.postValue(professorList.sortedAlphabetically())
        } catch (ioException: IOException) {
            professors.value = listOf()
        }
    }

    fun addCallback(callback: ProfessorScreenClickListener) {
        this.callback = callback
    }

    fun filter(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredList = arrayListOf<Professor>()

            professorList.forEach { model ->
                if (model.equalsName(text)) filteredList.add(model)
            }

            professors.postValue(filteredList)
        }
    }

    fun observe(owner: LifecycleOwner) {
        professors.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        professors.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Professor -> callback.openDialog(view.tag as Professor)
        }
    }
}
