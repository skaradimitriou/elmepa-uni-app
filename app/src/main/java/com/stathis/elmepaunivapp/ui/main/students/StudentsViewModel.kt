package com.stathis.elmepaunivapp.ui.main.students

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.*
import com.stathis.elmepaunivapp.ui.main.students.recycler.StudentAdapter

class StudentsViewModel(app : Application) : AndroidViewModel(app), ElmepaClickListener {

    private lateinit var callback : StudentsScreenCallback
    val adapter = StudentAdapter(this)
    private val repo = StudentsRepository(app)
    private val studentScreenData = repo.data

    fun bindCallbacks(callback : StudentsScreenCallback) {
        this.callback = callback
    }

    fun observe(owner : LifecycleOwner){
        studentScreenData.observe(owner, Observer {
            adapter.submitList(listOf(
                CarouselParent(it.carouselItems),
                NewStudentItem("Σπουδαστικά θέματα",it.syllabusItems),
                NewStudentItem("Χρήσιμοι Σύνδεσμοι",it.links)))
        })
    }

    fun release(owner: LifecycleOwner){
        studentScreenData.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
            is LinkItem -> callback.openLink(view.tag as LinkItem)
            is SyllabusItem -> callback.openSyllabus(view.tag as SyllabusItem)
        }
    }
}