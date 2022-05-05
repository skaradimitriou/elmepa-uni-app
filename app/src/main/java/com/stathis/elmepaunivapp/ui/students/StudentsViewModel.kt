package com.stathis.elmepaunivapp.ui.students

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
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.students.recycler.StudentAdapter
import com.stathis.elmepaunivapp.ui.students.model.*
import com.stathis.elmepaunivapp.util.readJsonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class StudentsViewModel(val app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    private lateinit var callback : StudentsScreenCallback
    val adapter = StudentAdapter(this)
    val data = MutableLiveData<StudentResponse>()

    init {
        viewModelScope.launch(Dispatchers.IO){
            getData()
        }
    }

    private fun getData(){
        app.readJsonData<StudentResponse>("students_screen.json", data = {
            data.postValue(it)
        })
    }

    fun bindCallbacks(callback : StudentsScreenCallback) {
        this.callback = callback
    }

    fun observe(owner : LifecycleOwner){
        data.observe(owner) {
            adapter.submitList(listOf(
                CarouselParent(it.carouselItems),
                UsefulLinksParent(getString(R.string.student_useful_links),it.links)
            ))
        }
    }

    fun release(owner: LifecycleOwner) = data.removeObservers(owner)

    override fun onItemClick(view: View) = when(view.tag){
        is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
        is LinkItem -> callback.openLink(view.tag as LinkItem)
        else -> Unit
    }
}