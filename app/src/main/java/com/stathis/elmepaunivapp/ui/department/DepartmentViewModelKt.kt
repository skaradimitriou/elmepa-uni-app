package com.stathis.elmepaunivapp.ui.department

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.listeners.latest.DepartmentClickListener
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.department.model.*
import com.stathis.elmepaunivapp.ui.department.recyclerviews.DepartmentAdapterKt

class DepartmentViewModelKt : ViewModel(),ElmepaClickListener {

    private val repo = DepartmentRepository()
    val departmentData = repo.departmentList
    private lateinit var callback : DepartmentClickListener
    val adapter = DepartmentAdapterKt(this)

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
            is ResearchKt -> callback.openResearch(view.tag as ResearchKt)
        }
    }
}
