package com.stathis.elmepaunivapp.ui.main.department

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.main.department.model.*
import com.stathis.elmepaunivapp.ui.main.department.recyclerviews.DepartmentAdapter

class DepartmentViewModel(app : Application) : AndroidViewModel(app), ElmepaClickListener {

    private val repo = DepartmentRepository(app)
    val departmentData = repo.departmentList
    private lateinit var callback : DepartmentClickListener
    val adapter = DepartmentAdapter(this)

    fun observeData(owner: LifecycleOwner) {
        departmentData.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        departmentData.removeObservers(owner)
    }

    fun bindCallbacks(callback : DepartmentClickListener) {
        this.callback = callback
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is FieldOfStudy -> callback.openSyllabus(view.tag as FieldOfStudy)
            is Programme -> callback.openProgrammes(view.tag as Programme)
            is SocialChannel -> callback.openSocial(view.tag as SocialChannel)
            is VirtualTourModel -> callback.openVirtualTour(view.tag as VirtualTourModel)
            is Research -> callback.openResearch(view.tag as Research)
        }
    }
}
