package com.elmepa.supportv2.ui.applicationforms

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
import com.stathis.common.util.inflateCustomMenu
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.showDialog
import com.stathis.common.util.startNativeBrowserIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ApplicationFormsFragment : Fragment() {

    private val viewModel by viewModels<ApplicationFormsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()

                ElmepaAppTheme {
                    ApplicationFormsScreen(
                        state = state,
                        onClick = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenTitle(getString(R.string.application_forms_title))
        inflateCustomMenu(
            menuId = com.elmepa.support.ui.R.menu.application_forms_menu,
            respondItemId = com.elmepa.support.ui.R.id.general_info,
            callback = {
                showDialog(
                    title = getString(R.string.application_form_info_title),
                    message = getString(R.string.application_form_info_body)
                )
            })

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is ApplicationFormsView.Effect.OpenBrowser -> {
                        startNativeBrowserIntent(effect.url)
                    }
                }
            }
        }
    }
}
