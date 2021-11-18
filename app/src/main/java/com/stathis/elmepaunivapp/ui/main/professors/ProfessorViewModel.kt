package com.stathis.elmepaunivapp.ui.main.professors

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.ProfessorScreenClickListener
import com.stathis.elmepaunivapp.ui.main.professors.model.Professor
import com.stathis.elmepaunivapp.ui.main.professors.recyclerview.ProfessorsAdapter

class ProfessorViewModel(app : Application) : AndroidViewModel(app), ElmepaClickListener {

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
