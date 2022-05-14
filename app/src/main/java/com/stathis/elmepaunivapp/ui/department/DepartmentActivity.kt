package com.stathis.elmepaunivapp.ui.department

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.databinding.ActivityDepartmentBinding
import com.stathis.elmepaunivapp.ui.department.model.Programme
import com.stathis.elmepaunivapp.ui.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.research.ResearchActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.*

class DepartmentActivity : ElmepaActivity<ActivityDepartmentBinding>(R.layout.activity_department) {

    private val viewModel: DepartmentViewModel by viewModels()
    private val rotateOpen by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }
    private var clicked = false

    override fun init() {
        supportActionBar?.setupBar(getString(R.string.dashboard_department))
        binding.viewModel = viewModel
    }

    override fun startOps() {
        binding.mainFab.setOnClickListener { onAddButtonClicked() }
        binding.fabCall.setOnClickListener { callSecretary() }
        binding.fabMail.setOnClickListener { sendMail() }

        viewModel.bindCallbacks(object : DepartmentClickListener {
            override fun openCarouselItem(data: CarouselItem) {
                when (data.webTitle) {
                    RESEARCH -> goToResearch()
                    else -> openUrl(data.url, data.webTitle)
                }
            }

            override fun openSocial(data: SocialChannel) = when (data.title) {
                YOUTUBE -> openYoutube(data.url)
                else -> openUrlInBrowser(data.url)
            }

            override fun openProgrammes(data: Programme) = openUrl(data.url, data.title)
        })

        observe()
    }

    private fun observe() {
        viewModel.observe(this)
        viewModel.error.observe(this) { hasError ->
            if (hasError)
                showSnack(binding.deptScreenParent, resources.getString(R.string.error_data))
        }
    }

    override fun stopOps() = viewModel.release(this)

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) = if (clicked) {
        binding.fabCall.startAnimation(toBottom)
        binding.fabMail.startAnimation(toBottom)
        binding.mainFab.startAnimation(rotateClose)
    } else {
        binding.fabCall.startAnimation(fromBottom)
        binding.fabMail.startAnimation(fromBottom)
        binding.mainFab.startAnimation(rotateOpen)
    }

    private fun setVisibility(clicked: Boolean) {
        binding.fabCall.showOrHide(clicked)
        binding.fabMail.showOrHide(clicked)
    }

    private fun setClickable(clicked: Boolean) {
        binding.fabCall.setClickability(clicked)
        binding.fabMail.setClickability(clicked)
    }

    private fun callSecretary() {
        startActivity(Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse(SECRETARY_TEL)
        })
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND).setType(EMAIL_TYPE)
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(SECRETARY_MAIL))

        try {
            startActivity(Intent.createChooser(i, SEND_MAIL))
        } catch (ex: ActivityNotFoundException) {
            showSnack(binding.deptScreenParent, NO_CLIENTS_INSTALLED)
        }
    }

    private fun openUrlInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun openUrl(url: String, title: String? = null) {
        startActivity(Intent(this, WebviewActivity::class.java).apply {
            putExtra(URL, url)
            putExtra(TITLE, title)
        })
    }

    private fun goToResearch() {
        startActivity(Intent(this, ResearchActivity::class.java))
    }

    private fun openYoutube(url: String) {
        try {
            //goes to channel in youtube app
            startActivity(Intent( Intent.ACTION_VIEW, Uri.parse(getString(R.string.yt_app_link).format(url))))
        } catch (e: Exception) {
            //goes to channel in web view (opens browser)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.yt_web_link).format(url))))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}