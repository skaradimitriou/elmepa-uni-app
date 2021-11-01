package com.stathis.elmepaunivapp.ui.research

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.ResearchClickListener
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_research_in_dept.*

class ResearchActivity : ElmepaActivity(R.layout.activity_research_in_dept) {

    private lateinit var viewModel : ResearchViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ResearchViewModel::class.java)
    }

    override fun startOps() {
        viewModel.bindCallbacks(object : ResearchClickListener {
            override fun onItemTap(item : UsefulLinks) {
                startActivity(Intent(this@ResearchActivity,WebviewActivity::class.java)
                    .putExtra("URL",item.url))
            }
        })

        research_activity_recycler.adapter = viewModel.adapter
        viewModel.createLists()
    }

    override fun stopOps() {}
}