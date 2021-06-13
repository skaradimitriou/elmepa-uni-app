package com.stathis.elmepaunivapp.ui.syllabus

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.ui.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.syllabus_lessons.SyllabusLessonsActivity
import kotlinx.android.synthetic.main.activity_syllabus.*

class SyllabusActivity : ElmepaActivity(R.layout.activity_syllabus) {

    private lateinit var viewModel : SyllabusViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusViewModel::class.java)
    }

    override fun startOps() {
        syllabus_recycler.adapter = viewModel.adapter

        val userChoice = intent.getIntExtra("userTabChoice", 0)

        tabLayout.getTabAt(userChoice)

        viewModel.bindCallback(object : SyllabusClickListener {
            override fun onSemesterTap(syllabus: Semester) {
                val jsonArray = Gson().toJson(syllabus.lessons)
                Log.d("JSON_ARRAY", jsonArray)
                startActivity(Intent(this@SyllabusActivity, SyllabusLessonsActivity::class.java)
                        .putExtra("ARRAY", jsonArray)
                        .putExtra("LESSONS_INFO", syllabus.lessonInfo)
                )
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
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