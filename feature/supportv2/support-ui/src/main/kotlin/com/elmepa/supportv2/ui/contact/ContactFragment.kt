package com.elmepa.supportv2.ui.contact

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
import com.stathis.common.R
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.startDialIntent
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startNativeBrowserIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactFragment : Fragment() {

    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()

                ElmepaAppTheme {
                    ContactScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenTitle(getString(R.string.contact))

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is ContactView.Effect.OpenDialer -> startDialIntent(numberToDial = effect.telephoneNumber)
                    is ContactView.Effect.OpenEmailProvider -> startEmailIntent(emailAddress = effect.email)
                    is ContactView.Effect.OpenUrl -> startNativeBrowserIntent(url = effect.url)
                }
            }
        }
    }
}
