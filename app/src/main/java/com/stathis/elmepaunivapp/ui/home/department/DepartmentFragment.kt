package com.stathis.elmepaunivapp.ui.home.department

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.DepartmentClickListener
import com.stathis.elmepaunivapp.databinding.FragmentDepartmentBinding
import com.stathis.elmepaunivapp.ui.home.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.home.department.model.Programme
import com.stathis.elmepaunivapp.ui.home.department.model.SocialChannel
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselItem
import com.stathis.elmepaunivapp.ui.research.ResearchActivity

class DepartmentFragment : ElmepaFragment<FragmentDepartmentBinding>(R.layout.fragment_department) {

    private lateinit var viewModel : DepartmentViewModel
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open_anim) }
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close_anim) }
    private val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_anim) }
    private val toBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_anim) }
    private var clicked = false

    override fun init() {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        binding.departmentRecycler.adapter = viewModel.adapter

        binding.mainFab.setOnClickListener {
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

        binding.fabCall.setOnClickListener{
            callSecretary()
        }

        binding.fabMail.setOnClickListener {
            sendMail()
        }

        observe()
    }

    private fun observe(){
        viewModel.observeData(this)
        viewModel.error.observe(this,Observer{
            when(it){
                true -> Snackbar.make(binding.deptScreenParent,resources.getString(R.string.error_data), Snackbar.LENGTH_LONG).show()
                false -> Unit
            }
        })
    }

    override fun stopOps() = viewModel.removeObserver(this)

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked : Boolean) {
        when(clicked){
            true -> {
                binding.fabCall.startAnimation(toBottom)
                binding.fabMail.startAnimation(toBottom)
                binding.mainFab.startAnimation(rotateClose)
            }
            false -> {
                binding.fabCall.startAnimation(fromBottom)
                binding.fabMail.startAnimation(fromBottom)
                binding.mainFab.startAnimation(rotateOpen)
            }
        }
    }

    private fun setVisibility(clicked : Boolean) {
        when(clicked){
            true -> {
                binding.fabCall.visibility = View.INVISIBLE
                binding.fabMail.visibility = View.INVISIBLE
            }
            false -> {
                binding.fabCall.visibility = View.VISIBLE
                binding.fabMail.visibility = View.VISIBLE
            }
        }
    }

    private fun setClickable(clicked: Boolean){
        when(clicked){
            true -> {
                binding.fabCall.isClickable = false
                binding.fabMail.isClickable = false
            }
            false -> {
                binding.fabCall.isClickable = true
                binding.fabMail.isClickable = true
            }
        }
    }

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
        //startActivity(Intent(requireContext(), SyllabusActivity::class.java)
            //.putExtra(resources.getString(R.string.userTabChoice), value))
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