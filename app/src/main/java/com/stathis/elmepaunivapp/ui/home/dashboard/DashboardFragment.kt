package com.stathis.elmepaunivapp.ui.home.dashboard

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.databinding.FragmentDashboardBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.research.ResearchActivity
import com.stathis.elmepaunivapp.ui.students.StudentActivity
import com.stathis.elmepaunivapp.util.addCallback

class DashboardFragment : ElmepaFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    private val viewModel : DashboardViewModel by viewModels()

    override fun init() {
        activity?.title = getString(R.string.main_screen_title)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        viewModel.addCallback {
            val intent = when(it.drawable){
                R.drawable.ic_announcement -> Intent(requireContext(), AnnouncementsActivity::class.java)
                R.drawable.ic_books -> Intent(requireContext(), DepartmentActivity::class.java)
                R.drawable.ic_student -> Intent(requireContext(), StudentActivity::class.java)
                else ->Intent(requireContext(), ResearchActivity::class.java)
            }
            startActivity(intent)
        }
    }

    override fun stopOps(){}
}