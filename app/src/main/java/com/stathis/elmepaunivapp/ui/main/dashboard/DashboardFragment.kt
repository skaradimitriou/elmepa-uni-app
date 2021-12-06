package com.stathis.elmepaunivapp.ui.main.dashboard

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaBindingFragment
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.databinding.FragmentDashboardBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.chatbot.ChatbotActivity
import com.stathis.elmepaunivapp.ui.main.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity

class DashboardFragment : ElmepaBindingFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    private lateinit var viewModel : DashboardFragmentViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DashboardFragmentViewModel::class.java)
    }

    override fun startOps() {
        binding.dashboardOptionsRecview.adapter = viewModel.adapter

        viewModel.bindList(object : DashboardClickListener {
            override fun dashboardItemClicked(option: DashboardOption) {
                when(option.drawable){
                    R.drawable.ic_announcement -> startActivity(Intent(requireContext(),AnnouncementsActivity::class.java))
                    R.drawable.ic_books -> goToDestination(R.id.nav_uni)
                    R.drawable.ic_student -> goToDestination(R.id.nav_students)
                    R.drawable.ic_teacher -> goToDestination(R.id.nav_search)
                    else -> Unit
                }
            }

            override fun learnMore() {
                startActivity(Intent(requireContext(), WebviewActivity::class.java)
                    .putExtra(resources.getString(R.string.url_tag),resources.getString(R.string.learn_more_url)))
            }
        })

        binding.fabChatbot.setOnClickListener{
            startActivity(Intent(requireContext(), ChatbotActivity::class.java))
        }

        binding.about.setOnClickListener{
            viewModel.showDialog(requireContext())
        }
    }

    override fun stopOps(){}

    private fun goToDestination(navId : Int){
        Navigation.findNavController(requireView()).navigate(navId)
    }
}