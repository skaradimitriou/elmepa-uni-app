package com.stathis.elmepaunivapp.ui.home.dashboard

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.databinding.FragmentDashboardBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.home.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.students.StudentActivity
import kotlin.reflect.KClass

class DashboardFragment : ElmepaFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    private val viewModel : DashboardViewModel by viewModels()

    override fun init() {
        activity?.title = getString(R.string.main_screen_title)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.addCallback(object : DashboardClickListener {
            override fun dashboardItemClicked(option: DashboardOption) {
                when(option.drawable){
                    R.drawable.ic_announcement -> startActivity(Intent(requireContext(), AnnouncementsActivity::class.java))
                    R.drawable.ic_books -> startActivity(Intent(requireContext(), DepartmentActivity::class.java))
                    R.drawable.ic_student -> startActivity(Intent(requireContext(), StudentActivity::class.java))
                    R.drawable.ic_teacher -> findNavController().navigate(R.id.nav_search)
                    else -> Unit
                }
            }
        })
    }

    override fun stopOps(){}
}