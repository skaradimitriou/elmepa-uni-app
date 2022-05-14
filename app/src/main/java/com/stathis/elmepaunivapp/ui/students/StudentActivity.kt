package com.stathis.elmepaunivapp.ui.students

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.databinding.ActivityStudentBinding
import com.stathis.elmepaunivapp.ui.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.*
import com.stathis.elmepaunivapp.util.setupBar

class StudentActivity : ElmepaActivity<ActivityStudentBinding>(R.layout.activity_student) {

    private val viewModel: StudentsViewModel by viewModels()

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.dashboard_students))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(this)
        viewModel.bindCallbacks(object : StudentsScreenCallback {
            override fun openCarouselItem(item: CarouselItem) = openUrl(item.url, item.webTitle)
            override fun openLink(item: LinkItem) = when (item.title) {
                SECRETARY, STUDENTS_PLATFORM, STUD_FB_PG, EDU_MAIL -> openBrowser(item.url)
                else -> openUrl(item.url, item.title)
            }
        })
    }

    override fun stopOps() = viewModel.release(this)

    private fun openBrowser(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    private fun openUrl(url: String, title: String? = null) {
        startActivity(Intent(this, WebviewActivity::class.java).apply {
            putExtra(URL, url)
            putExtra(TITLE, title)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}