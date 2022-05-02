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
import com.stathis.elmepaunivapp.ui.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.department.model.Programme
import com.stathis.elmepaunivapp.ui.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.research.ResearchActivity
import com.stathis.elmepaunivapp.util.setClickability
import com.stathis.elmepaunivapp.util.setupBar
import com.stathis.elmepaunivapp.util.showOrHide
import com.stathis.elmepaunivapp.util.showSnack

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
                when (data.imageResource) {
                    resources.getString(R.string.virtual_tour) -> openUrl(data.url)
                    resources.getString(R.string.research_img) -> goToResearch()
                }
            }

            override fun openSyllabus(data: FieldOfStudy) {}
            override fun openSocial(data: SocialChannel) = when (data.title) {
                resources.getString(R.string.youtube) -> openYoutube(data.url)
                else -> openUrl(data.url)
            }

            override fun openProgrammes(data: Programme) = openUrl(data.url)
        })

        observe()
    }

    private fun observe() {
        viewModel.observe(this)
        viewModel.error.observe(this) { hasError ->
            if(hasError) showSnack(binding.deptScreenParent,resources.getString(R.string.error_data))
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
            data = Uri.parse(resources.getString(R.string.secretary_tel))
        })
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND).setType(resources.getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.secretary_mail)))

        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            showSnack(binding.deptScreenParent,resources.getString(R.string.no_clients_installed))
        }
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun goToResearch() {
        startActivity(Intent(this, ResearchActivity::class.java))
    }

    private fun openYoutube(url: String) {
        try {
            //goes to channel in youtube app
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.yt_app_link).format(url))))
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