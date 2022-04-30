package com.stathis.elmepaunivapp.ui.home.syllabus

import android.content.Intent
import androidx.fragment.app.viewModels
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaFragment
import com.stathis.elmepaunivapp.callbacks.SyllabusClickListener
import com.stathis.elmepaunivapp.databinding.FragmentSyllabusBinding
import com.stathis.elmepaunivapp.ui.home.syllabus.model.Semester
import com.stathis.elmepaunivapp.ui.lessons.LessonsActivity
import com.stathis.elmepaunivapp.util.onTabSelected


class SyllabusFragment : ElmepaFragment<FragmentSyllabusBinding>(R.layout.fragment_syllabus) {

    private val viewModel : SyllabusViewModel by viewModels()

    override fun init() {
        requireActivity().title = getString(R.string.student_syllabus)
        binding.viewModel = viewModel
    }

    override fun startOps() {
        binding.tabLayout.onTabSelected { tabPosition ->
            viewModel.getData(tabPosition)
        }

        viewModel.observe(viewLifecycleOwner, object : SyllabusClickListener {
            override fun onSemesterTap(syllabus: Semester) {
                startActivity(Intent(requireContext(), LessonsActivity::class.java).also{
                    it.putExtra(getString(R.string.syllabus_intent_data), syllabus)
                })
            }
        })
    }

    override fun stopOps() {}
}