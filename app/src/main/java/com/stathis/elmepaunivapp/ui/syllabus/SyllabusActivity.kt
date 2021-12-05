package com.stathis.elmepaunivapp.ui.syllabus

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.syllabus_lessons.SyllabusLessonsActivity
import kotlinx.android.synthetic.main.activity_syllabus.*

class SyllabusActivity : ElmepaActivity(R.layout.activity_syllabus) {

    private lateinit var viewModel: SyllabusViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusViewModel::class.java)
    }

    override fun startOps() {
        syllabus_recycler.adapter = viewModel.adapter

        val userChoice = intent.getIntExtra(resources.getString(R.string.userTabChoice), 0)

        viewModel.observe(this)
        viewModel.error.observe(this, Observer{
            when(it){
                true -> Snackbar.make(findViewById(R.id.syllabus_screen_parent),resources.getString(R.string.announcements_get_error), Snackbar.LENGTH_LONG).show()
                false -> Unit
            }
        })

        tabLayout.getTabAt(userChoice)?.select()

        viewModel.bindCallback(object : SyllabusClickListener {
            override fun onSemesterTap(syllabus: Semester) {
                startActivity(Intent(this@SyllabusActivity, SyllabusLessonsActivity::class.java).putExtra(getString(R.string.syllabus_intent_data), syllabus))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> viewModel.getDataList()
                    1 -> viewModel.getBaList()
                    2 -> viewModel.getMktList()
                    else -> Unit
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun stopOps() {}
}