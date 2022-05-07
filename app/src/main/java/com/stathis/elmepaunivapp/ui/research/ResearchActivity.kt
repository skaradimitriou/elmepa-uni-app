package com.stathis.elmepaunivapp.ui.research

import android.content.Intent
import androidx.activity.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.ResearchClickListener
import com.stathis.elmepaunivapp.databinding.ActivityResearchInDeptBinding
import com.stathis.elmepaunivapp.ui.research.model.ResearchItem
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.TITLE
import com.stathis.elmepaunivapp.util.URL
import com.stathis.elmepaunivapp.util.setupBar
import kotlinx.android.synthetic.main.activity_research_in_dept.*

class ResearchActivity : ElmepaActivity<ActivityResearchInDeptBinding>(R.layout.activity_research_in_dept) {

    private val viewModel: ResearchViewModel by viewModels()

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.research_title))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(this, object : ResearchClickListener {
            override fun onItemTap(item: ResearchItem) {
                startActivity(Intent(this@ResearchActivity, WebviewActivity::class.java).apply {
                    putExtra(URL, item.url)
                    putExtra(TITLE, item.name)
                })
            }
        })
    }

    override fun stopOps() = viewModel.release(this)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}