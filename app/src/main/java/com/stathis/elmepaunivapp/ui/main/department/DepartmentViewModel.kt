package com.stathis.elmepaunivapp.ui.main.department

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.main.department.model.*
import com.stathis.elmepaunivapp.ui.main.department.recyclerviews.DepartmentAdapter
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselParent

class DepartmentViewModel(app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    private val repo = DepartmentRepository(app)
    val data = MutableLiveData<DepartmentResponse>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback : DepartmentClickListener
    val adapter = DepartmentAdapter(this)

    init {
        repo.getStudentsScreenData(data,error)
    }

    fun observeData(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(listOf(
                CarouselParent(it.carouselItems),
                NewDepartmentItem(getString(R.string.deptSyllabusItems),it.syllabusItems),
                NewDepartmentItem(getString(R.string.deptProgramms),it.programmes),
                NewDepartmentItem(getString(R.string.deptDepMembers),it.depMembers),
                NewDepartmentItem(getString(R.string.deptSocial),it.links),
                EmptyModel()
            ))
        })
    }

    fun removeObserver(owner: LifecycleOwner) = data.removeObservers(owner)

    fun bindCallbacks(callback : DepartmentClickListener) {
        this.callback = callback
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is FieldOfStudy -> callback.openSyllabus(view.tag as FieldOfStudy)
            is Programme -> callback.openProgrammes(view.tag as Programme)
            is SocialChannel -> callback.openSocial(view.tag as SocialChannel)
            is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
        }
    }
}