package com.stathis.elmepaunivapp.ui.main.department

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.ui.main.department.model.*
import com.stathis.elmepaunivapp.ui.main.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.main.department.model.Programme
import com.stathis.elmepaunivapp.ui.main.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem
import com.stathis.elmepaunivapp.ui.research.ResearchActivity
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.fragment_department.*

class DepartmentFragment : ElmepaFragment(R.layout.fragment_department) {

    private lateinit var viewModel : DepartmentViewModel
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open_anim) }
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close_anim) }
    private val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_anim) }
    private val toBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_anim) }
    private var clicked = false

    override fun init(view : View) {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        department_recycler.adapter = viewModel.adapter

        main_fab.setOnClickListener {
            onAddButtonClicked()
        }

        viewModel.bindCallbacks(object : DepartmentClickListener {
            override fun openCarouselItem(data: CarouselItem) {
                when(data.imageResource){
                    resources.getString(R.string.virtual_tour) -> openUrl(data.url)
                    resources.getString(R.string.research_img) -> goToResearch()
                }
            }

            override fun openSyllabus(data: FieldOfStudy) = goToDirection(data.tabNo)
            override fun openSocial(data: SocialChannel) = when(data.title){
                    resources.getString(R.string.youtube) -> openYoutube(data.url)
                    else -> openUrl(data.url)
            }

            override fun openProgrammes(data: Programme) = openUrl(data.url)
        })

        fab_call.setOnClickListener{
            callSecretary()
        }

        fab_mail.setOnClickListener {
            sendMail()
        }

        viewModel.observeData(this)

    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked : Boolean) {
        when(clicked){
            true -> {
                fab_call.startAnimation(toBottom)
                fab_mail.startAnimation(toBottom)
                main_fab.startAnimation(rotateClose)
            }
            false -> {
                fab_call.startAnimation(fromBottom)
                fab_mail.startAnimation(fromBottom)
                main_fab.startAnimation(rotateOpen)
            }
        }
    }

    private fun setVisibility(clicked : Boolean) {
        when(clicked){
            true -> {
                fab_call.visibility = View.INVISIBLE
                fab_mail.visibility = View.INVISIBLE
            }
            false -> {
                fab_call.visibility = View.VISIBLE
                fab_mail.visibility = View.VISIBLE
            }
        }
    }

    private fun setClickable(clicked: Boolean){
        when(clicked){
            true -> {
                fab_call.isClickable = false
                fab_mail.isClickable = false
            }
            false -> {
                fab_call.isClickable = true
                fab_mail.isClickable = true
            }
        }
    }

    override fun stopOps() = viewModel.removeObserver(this)

    private fun callSecretary() {
        startActivity(Intent(Intent.ACTION_DIAL).also {
            it.data = Uri.parse(resources.getString(R.string.secretary_tel))
        })
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND)
            .setType(resources.getString(R.string.email_type))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf<String>(resources.getString(R.string.secretary_mail)))

        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(),resources.getString(R.string.no_clients_installed), Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToDirection(value : Int){
        startActivity(Intent(requireContext(), SyllabusActivity::class.java)
            .putExtra(resources.getString(R.string.userTabChoice), value))
    }

    private fun openUrl(url : String){
        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))
    }

    private fun goToResearch() {
        startActivity(Intent(requireContext(), ResearchActivity::class.java))
    }

    private fun openYoutube(url : String){
        try {
            //goes to channel in youtube app
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.yt_app_link).format(url))))
        } catch (e: Exception) {
            //goes to channel in web view (opens browser)
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(getString(R.string.yt_web_link).format(url))))
        }
    }
}