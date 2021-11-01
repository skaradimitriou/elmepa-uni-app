package com.stathis.elmepaunivapp.ui.syllabus

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.syllabus.recyclerview.SemesterAdapter
import com.stathis.elmepaunivapp.ui.syllabus.repos.BusinessLessonsRepo
import com.stathis.elmepaunivapp.ui.syllabus.repos.DataLessonsRepo
import com.stathis.elmepaunivapp.ui.syllabus.repos.MarketingLessonsRepo

class SyllabusViewModel(app : Application) : AndroidViewModel(app), ElmepaClickListener {
    
    val context = app
    private lateinit var callback : SyllabusClickListener
    val adapter = SemesterAdapter(this)

    init{
        getDataList()
    }

    fun bindCallback(callback : SyllabusClickListener) {
        this.callback = callback
    }

    fun getDataList(){
        val repo = DataLessonsRepo()
        val list = listOf(
            Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterA()),
            Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterB()),
            Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterC()),
            Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterD()),
            Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterE()),
            Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterF()),
            Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterG()),
            Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterH()))
        
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
    
    fun getMktList(){
        val repo = MarketingLessonsRepo()
        val list = listOf(
            Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterA()),
            Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterB()),
            Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterC()),
            Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterD()),
            Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterE()),
            Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterF()),
            Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterG()),
            Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterH()))
        
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
    
    fun getBaList(){
        val repo = BusinessLessonsRepo()
        val list = listOf(
            Semester("Εξάμηνο Α'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterA()),
            Semester("Εξάμηνο Β'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterB()),
            Semester("Εξάμηνο Γ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterC()),
            Semester("Εξάμηνο Δ'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterD()),
            Semester("Εξάμηνο Ε'", context.getString(R.string.all_lessons_mandatory), repo.getSemesterE()),
            Semester("Εξάμηνο ΣΤ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterF()),
            Semester("Εξάμηνο Ζ'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterG()),
            Semester("Εξάμηνο Η'", context.getString(R.string.some_lessons_mandatory), repo.getSemesterH()))
        
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
    
    override fun onItemClick(view: View) {
        when(view.tag){
            is Semester -> callback.onSemesterTap(view.tag as Semester)
        }
    }
}
