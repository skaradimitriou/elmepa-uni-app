package com.stathis.elmepaunivapp.ui.research

import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import kotlinx.android.synthetic.main.activity_research_in_dept.*

class ResearchActivity : ElmepaActivity(R.layout.activity_research_in_dept) {

    private lateinit var viewModel : ResearchViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ResearchViewModel::class.java)
    }

    override fun startOps() {
        research_activity_recycler.adapter = viewModel.adapter
        viewModel.createLists()
    }

    override fun stopOps() {}
}