package com.elmepa.news.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.news.events.EventsView.Effect
import com.stathis.common.MainViewModel
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.model.navigation.NavigationAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private val viewModel by viewModels<EventsViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val events = viewModel.events.collectAsLazyPagingItems()
                ElmepaAppTheme {
                    EventsScreen(
                        events = events,
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
                is Effect.NavigateToDetails -> {
                    val args = Bundle().apply {
                        putString(TITLE, effect.event.name)
                        putString(IMAGE, effect.event.imageResource)
                        putString(URL, effect.event.url)
                        putString(PUB_DATE, effect.event.pubDate)
                    }
                    activityVM.navigateWithAction(NavigationAction.POST_DETAILS, args)
                }

                is Effect.Back -> {
                    findNavController().popBackStack()
                }
            }
        }.launchIn(lifecycleScope)
    }
}
