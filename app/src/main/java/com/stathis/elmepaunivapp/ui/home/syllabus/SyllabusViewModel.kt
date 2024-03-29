package com.stathis.elmepaunivapp.ui.home.syllabus

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.ui.home.syllabus.adapter.SemesterAdapter
import com.stathis.elmepaunivapp.model.syllabus.Semester
import com.stathis.elmepaunivapp.util.ShimmerHelper
import com.stathis.elmepaunivapp.util.readLocalJsonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyllabusViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val data = MutableLiveData<List<Semester>>()
    val adapter = SemesterAdapter(this)
    private lateinit var callback: SyllabusClickListener

    init {
        getData(0)
    }

    fun getData(tabPosition: Int) {
        adapter.submitList(ShimmerHelper.list)

        when (tabPosition) {
            0 -> getSemesters("undergraduate_data_syllabus.json")
            1 -> getSemesters("undergraduate_ba_syllabus.json")
            2 -> getSemesters("undergraduate_mkt_syllabus.json")
        }
    }

    fun observe(owner: LifecycleOwner, callback: SyllabusClickListener) {
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(it)
        }
    }

    private fun getSemesters(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            app.readLocalJsonList<Semester>(fileName, data = { list ->
                data.postValue(list)
            })
        }
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Semester -> callback.onSemesterTap(view.tag as Semester)
        }
    }
}