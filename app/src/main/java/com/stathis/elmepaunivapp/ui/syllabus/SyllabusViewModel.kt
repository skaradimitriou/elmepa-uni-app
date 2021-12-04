package com.stathis.elmepaunivapp.ui.syllabus

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.syllabus.recyclerview.SemesterAdapter

class SyllabusViewModel(val app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    val context = app
    private lateinit var callback : SyllabusClickListener
    private val repo = SyllabusRepository(app)
    val data = repo.data
    val adapter = SemesterAdapter(this)

    init{
        getDataList()
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun bindCallback(callback : SyllabusClickListener) {
        this.callback = callback
    }

    fun getDataList() = repo.getDataList()
    fun getMktList() = repo.getMktList()
    fun getBaList() = repo.getBaList()
    
    override fun onItemClick(view: View) {
        when(view.tag){
            is Semester -> callback.onSemesterTap(view.tag as Semester)
        }
    }
}
