package com.elmepa.syllabus.lessondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.syllabus.const.LESSON
import com.stathis.common.MainSharedViewModel
import com.stathis.common.util.toNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonDetailsFragment : Fragment() {

    private val viewModel by viewModels<LessonDetailsViewModel>()
    private val sharedVM by activityViewModels<MainSharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    LessonDetailsScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lessonName = arguments?.getString(LESSON).toNotNull()
        val programme = sharedVM.selectedProgrammeType
        viewModel.fetchLessonDetails(programmeType = programme, lessonName = lessonName)

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is LessonDetailsView.Effect.GoBack -> findNavController().navigateUp()
                }
            }
        }
    }
}
