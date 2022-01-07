package com.stathis.elmepaunivapp.ui.research

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ResearchClickListener
import com.stathis.elmepaunivapp.ui.research.model.ResearchModel
import com.stathis.elmepaunivapp.ui.research.recycler.ResearchAdapter
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import java.io.IOException

class ResearchViewModel(val app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = ResearchAdapter(this)
    private lateinit var callback : ResearchClickListener
    private lateinit var model : List<ResearchModel>
    val data = MutableLiveData<List<ResearchModel>>()

    fun bindCallbacks(callback : ResearchClickListener){
        this.callback = callback
    }

    init {
        getResearchData()
    }

    fun getResearchData() {
        try {
            val jsonString = app.assets.open("research.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<ResearchModel>>() {}.type
            model = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),model.toString())
            data.value = model
        } catch (ioException: IOException) {
            //ioException.printStackTrace()
        }
    }

    fun observe(owner : LifecycleOwner){
        data.observe(owner,Observer{
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is UsefulLinks -> callback.onItemTap(view.tag as UsefulLinks)
        }
    }
}
