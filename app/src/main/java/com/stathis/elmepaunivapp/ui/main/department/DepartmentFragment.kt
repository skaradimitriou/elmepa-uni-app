package com.stathis.elmepaunivapp.ui.main.department

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
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

    override fun init(view : View) {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        department_recycler.adapter = viewModel.adapter

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