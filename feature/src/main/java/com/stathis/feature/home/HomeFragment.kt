package com.stathis.feature.home

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.stathis.core.base.BaseFragment
import com.stathis.feature.MainViewModel
import com.stathis.feature.R
import com.stathis.feature.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val activityVM by activityViewModels<MainViewModel>()

    override fun init() {

    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}