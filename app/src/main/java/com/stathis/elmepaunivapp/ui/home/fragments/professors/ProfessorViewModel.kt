package com.stathis.elmepaunivapp.ui.home.fragments.professors

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.ui.home.fragments.professors.model.Professor
import com.stathis.elmepaunivapp.ui.home.fragments.professors.recyclerview.ProfessorsAdapter

class ProfessorViewModel(app : Application) : ElmepaViewModel(app), ElmepaClickListener {

    private val repo = ProfessorRepository(app)
    val professors = repo.professors
    val adapter = ProfessorsAdapter(this)
    private lateinit var callback : ProfessorScreenClickListener

    fun getProfessors(callback : ProfessorScreenClickListener) {
        this.callback = callback

        repo.getProfessors()
    }

    fun filter(text: String) {
        repo.filter(text)
    }

    fun observeData(owner : LifecycleOwner) {
        professors.observe(owner, Observer{
            adapter.submitList(it)
        })
    }

    fun removeObserver(owner : LifecycleOwner){
        professors.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is Professor -> callback.openDialog(view.tag as Professor)
        }
    }
}
