package com.stathis.announcements.details

import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.stathis.announcements.R
import com.stathis.announcements.databinding.FragmentPostDetailsBinding
import com.stathis.announcements.details.adapter.PostDetailsAdapter
import com.stathis.announcements.details.generator.PostDetailsGenerator
import com.stathis.core.base.BaseFragment
import com.stathis.core.util.IMAGE
import com.stathis.core.util.PUB_DATE
import com.stathis.core.util.TITLE
import com.stathis.core.util.URL
import com.stathis.core.util.setScreenTitle
import com.stathis.core.util.setupItemDecoration
import com.stathis.core.util.toNotNull
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding>(R.layout.fragment_post_details) {

    private val viewModel by viewModels<PostDetailsViewModel>()

    private val adapter = PostDetailsAdapter()

    override fun init() {
        setScreenTitle(getString(com.stathis.core.R.string.post_details_title))

        binding.detailsRecycler.apply {
            setupItemDecoration(top = 0, start = 0, end = 0)
            adapter = this@PostDetailsFragment.adapter
        }

        val title = arguments?.getString(TITLE).toNotNull()
        val imageUrl = arguments?.getString(IMAGE).toNotNull()
        val pubDate = arguments?.getString(PUB_DATE).toNotNull()
        val openUrl = arguments?.getString(URL).toNotNull()

        viewModel.fetchPostDetails(
            title = title,
            imageUrl = imageUrl,
            pubDate = pubDate,
            scrapeUrl = openUrl
        )
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle).collect { state ->
                when (state) {
                    is NetworkResult.Loading -> {
                        binding.isLoading = true
                    }

                    is NetworkResult.Success -> {
                        binding.isLoading = false
                        val data = PostDetailsGenerator.toUiModel(state.data)
                        adapter.submitList(data)
                    }

                    else -> {
                        binding.isLoading = false
                    }
                }
            }
        }
    }

    override fun stopOps() {}
}