package com.stathis.elmepaunivapp.ui.about

import android.content.Intent
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityAboutBinding
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.*

class AboutActivity : ElmepaActivity<ActivityAboutBinding>(R.layout.activity_about) {

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.about_app_title))
    }

    override fun startOps() {
        binding.aboutApp.alignText()
        binding.aboutData.alignText()
        binding.learnMore.setOnClickListener {
            startActivity(Intent(this, WebviewActivity::class.java).apply {
                putExtra(URL, ABOUT_MOBILE_APP_URL)
                putExtra(TITLE, ELMEPA_MOBILE_APP)
            })
        }
    }

    override fun stopOps() {}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}