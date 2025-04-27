package com.elmepa.homeui.ui

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
import com.elmepa.homeui.ext.toNavigationAction
import com.stathis.common.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.dashboardDetails.collectAsStateWithLifecycle()

                ElmepaAppTheme {
                    HomeScreen(
                        state = state,
                        onAction = { viewModel.onAction(it) }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.effect.flowWithLifecycle(lifecycle).onEach { effect ->
            when (effect) {
                is HomeView.Effect.OpenDashboardOption -> {
                    val navAction = effect.option.type.toNavigationAction()
                    activityVM.navigateWithAction(navAction)
                }
            }
        }.launchIn(lifecycleScope)
    }
}
