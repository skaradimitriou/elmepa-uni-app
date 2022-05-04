package com.stathis.elmepaunivapp.ui.department

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.department.adapter.DepartmentAdapter
import com.stathis.elmepaunivapp.ui.department.model.*
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselParent
import com.stathis.elmepaunivapp.util.readJsonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepartmentViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val data = MutableLiveData<DepartmentResponse>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback: DepartmentClickListener
    val adapter = DepartmentAdapter(this)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private fun getData() {
        app.readJsonData<DepartmentResponse>("department_data.json", data = {
            it?.let { data.postValue(it) }
        })
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner) {
            adapter.submitList(
                listOf(
                    CarouselParent(it.carouselItems),
                    HorizontalDepartmentItem(getString(R.string.deptSyllabusItems), it.syllabusItems),
                    VerticalDepartmentItem(getString(R.string.deptProgramms), it.programmes),
                    HorizontalDepartmentItem(getString(R.string.deptDepMembers), it.depMembers),
                    HorizontalDepartmentItem(getString(R.string.deptSocial), it.links),
                )
            )
        }
    }

    fun release(owner: LifecycleOwner) = data.removeObservers(owner)

    fun bindCallbacks(callback: DepartmentClickListener) {
        this.callback = callback
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Programme -> callback.openProgrammes(view.tag as Programme)
            is SocialChannel -> callback.openSocial(view.tag as SocialChannel)
            is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
        }
    }
}