package com.stathis.feature.ui.lessons

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.getSerializable
import com.stathis.core.util.inflateCustomMenu
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.showDialog
import com.stathis.core.util.toNotNull
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentLessonsBinding
import com.stathis.feature.ui.lessons.adapter.LessonsAdapter
import com.stathis.feature.util.ORIENTATION
import com.stathis.feature.util.SEMESTER
import com.stathis.model.syllabus.OrientationType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonsFragment : BaseFragment<FragmentLessonsBinding>(R.layout.fragment_lessons) {

    private val viewModel by viewModels<LessonsViewModel>()

    private val adapter = LessonsAdapter()

    override fun init() {
        inflateCustomMenu(
            menuId = R.menu.lesson_menu,
            respondItemId = R.id.lesson_info,
            callback = {
                showDialog(
                    title = getString(com.stathis.core.R.string.info_title),
                    message = getString(com.stathis.core.R.string.info_body)
                )
            })

        val semester = arguments?.getString(SEMESTER).toNotNull()
        setScreenTitle(semester)

        val orientation = arguments?.getSerializable<OrientationType>(ORIENTATION)
            ?: OrientationType.UNDEFINED

        viewModel.fetchLessonsForSemesterAndOrientation(semester, orientation)

        binding.syllabusRecycler.apply {
            setupItemDecoration(top = 30, start = 30, end = 30)
            adapter = this@LessonsFragment.adapter
        }
    }

    override fun startOps() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.lessons.flowWithLifecycle(lifecycle).collect {
                adapter.submitList(it)
            }
        }
    }

    override fun stopOps() {}
}