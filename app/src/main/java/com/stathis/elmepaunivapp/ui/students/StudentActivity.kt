package com.stathis.elmepaunivapp.ui.students

import android.content.Intent
import androidx.activity.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.StudentsScreenCallback
import com.stathis.elmepaunivapp.databinding.ActivityStudentBinding
import com.stathis.elmepaunivapp.ui.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.setupBar

class StudentActivity : ElmepaActivity<ActivityStudentBinding>(R.layout.activity_student) {

    private val viewModel : StudentsViewModel by viewModels()

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.dashboard_students))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.observe(this)
        viewModel.bindCallbacks(object : StudentsScreenCallback {
            override fun openCarouselItem(item: CarouselItem) = openUrl(item.url)
            override fun openLink(item: LinkItem) = openUrl(item.url)
        })
    }

    override fun stopOps() = viewModel.release(this)

    private fun openUrl(url : String){
        startActivity(Intent(this, WebviewActivity::class.java).putExtra(getString(R.string.url_tag), url))
    }
}