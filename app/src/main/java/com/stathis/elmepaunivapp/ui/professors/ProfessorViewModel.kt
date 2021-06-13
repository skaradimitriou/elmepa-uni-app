package com.stathis.elmepaunivapp.ui.professors

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.ui.professors.model.Professor
import com.stathis.elmepaunivapp.ui.professors.recyclerview.ProfessorsAdapter
import com.stathis.elmepaunivapp.ui.professors.repo.ProfessorRepository

class ProfessorViewModel : ViewModel(), ElmepaClickListener {

    private val repo = ProfessorRepository()
    val professors = repo.professors
    val professorList = repo.getProfessors()
    val adapter = ProfessorsAdapter(this)
    private lateinit var callback : ProfessorScreenClickListener

    fun getProfessors(callback : ProfessorScreenClickListener) {
        this.callback = callback

        repo.getProfessors()
    }

    fun filter(text: String) {
        repo.filter(text)
    }

    override fun onItemClick(view: View) {
        when(view.tag){
            is Professor -> callback.openDialog(view.tag as Professor)
        }
    }

    fun observeData(owner : LifecycleOwner) {
        professors.observe(owner, Observer{
           adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun removeObserver(owner : LifecycleOwner){
        professors.removeObservers(owner)
    }
}
