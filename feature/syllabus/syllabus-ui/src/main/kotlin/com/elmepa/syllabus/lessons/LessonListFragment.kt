package com.elmepa.syllabus.lessons

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
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.syllabus.const.LESSON
import com.elmepa.syllabus.const.ORIENTATION
import com.elmepa.syllabus.const.PROGRAMME
import com.elmepa.syllabus.const.SEMESTER
import com.stathis.common.MainViewModel
import com.stathis.common.util.getSerializableFromBundle
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LessonListFragment : Fragment() {

    private val viewModel by viewModels<LessonListViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    LessonsScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programmeType = arguments?.getSerializableFromBundle<ProgrammeType>(PROGRAMME) ?: ProgrammeType.UNDEFINED
        val orientation = arguments?.getSerializableFromBundle<OrientationType>(ORIENTATION) ?: OrientationType.UNDEFINED
        val semester = arguments?.getString(SEMESTER).orEmpty()

        viewModel.fetchLessonsByFields(
            programme = programmeType,
            orientation = orientation,
            semesterName = semester
        )

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is LessonsView.Effect.NavigateToLessonDetails -> {
                        val args = Bundle().apply { putString(LESSON, effect.lessonName) }
                        activityVM.navigateWithAction(NavigationAction.LESSON_DETAILS, args)
                    }
                }
            }
        }
    }
}
