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
            override fun openSyllabus(data: FieldOfStudy) {
                goToSyllabus(data)
            }

            override fun openVirtualTour(data: VirtualTourModel) {
                startActivity(Intent(requireContext(),WebviewActivity::class.java)
                    .putExtra("URL","https://mst.hmu.gr/hmutour/"))
            }

            override fun openSocial(data: SocialChannel) {
                if (data.image == R.drawable.youtube) {
                    try {
                        //goes to channel in youtube app
                        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("vnd.youtube.com/channel/" + data.url)))
                    } catch (e: Exception) {
                        //goes to channel in web view (opens browser)
                        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/channel/" + data.url)))
                    }
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.url)))
                }
            }

            override fun openProgrammes(data: Programme) {
                startActivity(Intent(requireContext(), WebviewActivity::class.java)
                    .putExtra("URL", data.url))
            }

            override fun openResearch(data: Research) {
                startActivity(Intent(requireContext(), ResearchActivity::class.java))
            }
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
            it.data = Uri.parse("tel:2841091103")
        })
    }

    private fun sendMail() {
        val i = Intent(Intent.ACTION_SEND)
            .setType("message/rfc822")
            .putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("kalarhaki@hmu.gr"))

        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(),"There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToSyllabus(data: FieldOfStudy) {
        when(data.name){
            "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής" -> goToDirection(0)
            "Διοίκηση Επιχειρήσεων & Οργανισμών" -> goToDirection(1)
            "Ψηφιακό Μάρκετινγκ και Επικοινωνία" -> goToDirection(2)
        }
    }

    private fun goToDirection(value : Int){
        startActivity(Intent(requireContext(), SyllabusActivity::class.java)
            .putExtra("userTabChoice", value))
    }
}