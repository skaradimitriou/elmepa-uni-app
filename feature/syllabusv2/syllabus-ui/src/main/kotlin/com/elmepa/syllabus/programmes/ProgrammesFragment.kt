package com.elmepa.syllabus.programmes

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
import com.elmepa.syllabus.const.ORIENTATION
import com.elmepa.syllabus.const.PROGRAMME
import com.elmepa.syllabus.const.SEMESTER
import com.stathis.common.MainSharedViewModel
import com.stathis.common.MainViewModel
import com.stathis.model.navigation.NavigationAction
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProgrammesFragment : Fragment() {

    private val viewModel by viewModels<ProgrammesViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()
    private val sharedVM by activityViewModels<MainSharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    ProgrammesScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchSemestersByProgramme(
            programme = sharedVM.selectedProgrammeType,
            orientation = sharedVM.selectedOrientation
        )

        viewModel.effect.flowWithLifecycle(lifecycle).onEach { effect ->
            when (effect) {
                is ProgrammesView.Effect.NavigateToLessonList -> goToLessonListScreen(
                    programmeType = sharedVM.selectedProgrammeType,
                    orientationType = sharedVM.selectedOrientation,
                    semester = effect.semester
                )

                is ProgrammesView.Effect.ChangeSelectedTab -> {
                    sharedVM.selectedProgrammeType = effect.toProgrammeType().also { programmeType ->
                        viewModel.fetchSemestersByProgramme(
                            programme = programmeType,
                            orientation = sharedVM.selectedOrientation
                        )
                    }
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun goToLessonListScreen(programmeType: ProgrammeType, orientationType: OrientationType, semester: String) {
        val args = Bundle().apply {
            putSerializable(PROGRAMME, programmeType)
            putSerializable(ORIENTATION, orientationType)
            putString(SEMESTER, semester)
        }

        sharedVM.apply {
            selectedProgrammeType = programmeType
            selectedOrientation = orientationType
        }

        activityVM.navigateWithAction(NavigationAction.LESSONS, args)
    }
}
