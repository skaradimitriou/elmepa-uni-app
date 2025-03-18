package com.elmepa.personnel.list

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
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.stathis.common.R
import com.stathis.common.util.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonnelFragment : Fragment() {

    private val viewModel by viewModels<PersonnelViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    PersonnelScreen(state)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenTitle(getString(R.string.personnel))
    }
}
