package com.elmepa.support.ui.contact

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
import androidx.navigation.fragment.findNavController
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.support.ui.contact.ContactView.Effect
import com.stathis.common.util.startDialIntent
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startNativeBrowserIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        viewModel.effect.flowWithLifecycle(lifecycle).onEach { effect ->
            when (effect) {
                is Effect.Back -> findNavController().popBackStack()
                is Effect.OpenDialer -> startDialIntent(numberToDial = effect.telephoneNumber)
                is Effect.OpenEmailProvider -> startEmailIntent(emailAddress = effect.email)
                is Effect.OpenUrl -> startNativeBrowserIntent(url = effect.url)
            }
        }.launchIn(lifecycleScope)
    }
}
