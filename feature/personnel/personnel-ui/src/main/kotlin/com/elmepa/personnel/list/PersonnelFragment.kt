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
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.personnel.list.PersonnelListView.Effect
import com.elmepa.personnel.list.PersonnelListView.UIAction.SearchPersonByName
import com.elmepa.personnel.ui.R
import com.stathis.common.util.inflateCustomMenu
import com.stathis.common.util.respondToQuery
import com.stathis.common.util.setScreenTitle
import com.stathis.common.util.startEmailIntent
import com.stathis.common.util.startShareIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonnelFragment : Fragment() {

    private val viewModel by viewModels<PersonnelViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    PersonnelScreen(state, onAction = viewModel::onAction)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenTitle(getString(com.stathis.common.R.string.personnel))
        inflateCustomMenu(
            menuId = R.menu.personnel_menu,
            respondItemId = R.id.action_search,
            callback = { menuItem ->
                menuItem.respondToQuery(
                    queryHint = getString(com.stathis.common.R.string.search_in_personnel)
                ) { query -> viewModel.onAction(SearchPersonByName(query)) }
            }
        )

        lifecycleScope.launch {
            viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
                when (effect) {
                    is Effect.SendEmail -> startEmailIntent(effect.email)
                    is Effect.ShareInfo -> startShareIntent(
                        subject = getString(R.string.share_personnel_data),
                        body = effect.dataToShare
                    )
                }
            }
        }
    }
}
