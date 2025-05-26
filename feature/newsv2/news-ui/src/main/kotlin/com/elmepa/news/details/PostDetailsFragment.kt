package com.elmepa.news.details

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
import com.elmepa.news.details.PostDetailsView.Effect
import com.stathis.common.R
import com.stathis.common.util.IMAGE
import com.stathis.common.util.PUB_DATE
import com.stathis.common.util.TITLE
import com.stathis.common.util.URL
import com.stathis.common.util.startShareIntent
import com.stathis.common.util.toNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private val viewModel by viewModels<PostDetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val state by viewModel.state.collectAsStateWithLifecycle()
                ElmepaAppTheme {
                    PostDetailsScreen(
                        state = state,
                        onAction = viewModel::onAction
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPostDetails(
            title = arguments?.getString(TITLE).toNotNull(),
            imageUrl = arguments?.getString(IMAGE).toNotNull(),
            pubDate = arguments?.getString(PUB_DATE).toNotNull(),
            scrapeUrl = arguments?.getString(URL).toNotNull()
        )

        viewModel.effect.flowWithLifecycle(lifecycle).onEach { effect ->
            when (effect) {
                is Effect.GoBack -> {
                    findNavController().popBackStack()
                }

                is Effect.SharePost -> {
                    val textBody = String.format(
                        getString(R.string.announcement_share_body),
                        effect.title,
                        effect.openUrl
                    )
                    startShareIntent(subject = effect.title, body = textBody)
                }

            }
        }.launchIn(lifecycleScope)
    }
}
