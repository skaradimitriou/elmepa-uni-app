package com.stathis.elmepaunivapp.ui.home.dashboard

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.databinding.FragmentDashboardBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.home.dashboard.model.DashboardOption

class DashboardFragment : ElmepaFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    private val viewModel : DashboardViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun startOps() {
        activity?.title = getString(R.string.main_screen_title)

        viewModel.addCallback(object : DashboardClickListener {
            override fun dashboardItemClicked(option: DashboardOption) {
                when(option.drawable){
                    R.drawable.ic_announcement -> startActivity(Intent(requireContext(),AnnouncementsActivity::class.java))
                    //R.drawable.ic_books -> goToDestination(R.id.nav_uni)
                    //R.drawable.ic_student -> goToDestination(R.id.nav_students)
                    //R.drawable.ic_teacher -> goToDestination(R.id.nav_search)
                    else -> Unit
                }
            }
        })

//        binding.fabChatbot.setOnClickListener{
//            startActivity(Intent(requireContext(), ChatbotActivity::class.java))
//        }
    }

    override fun stopOps(){}

    private fun goToDestination(navId : Int){
        Navigation.findNavController(requireView()).navigate(navId)
    }
}