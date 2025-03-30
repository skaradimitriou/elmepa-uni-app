package com.elmepa.personnel.depdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.personnel.depdetails.DepDetailsView.Effect
import com.elmepa.personnel.ui.R
import com.stathis.common.util.DEP_MEMBER_INFO
import com.stathis.common.util.getParcelableFromBundle
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startNativeBrowserIntent
import com.stathis.model.department.DepMember
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DepDetailsFragment : Fragment() {

    private val viewModel by viewModels<DepDetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()

                ElmepaAppTheme {
                    DepDetailsScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenTitle(getString(R.string.dep_details_screen_title))

        arguments?.getParcelableFromBundle<DepMember>(DEP_MEMBER_INFO)?.let { model ->
            viewModel.setCurrentDepMember(model)
        }

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is Effect.SendEmail -> startEmailIntent(effect.email)
                    is Effect.OpenBrowser -> startNativeBrowserIntent(effect.url)
                }
            }
        }
    }
}
