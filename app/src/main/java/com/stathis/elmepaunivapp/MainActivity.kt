package com.stathis.elmepaunivapp

import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.stathis.core.base.BaseActivity
import com.stathis.elmepaunivapp.databinding.ActivityMainBinding
import com.stathis.feature.MainViewModel
import com.stathis.feature.navigation.NavigatorImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navigator: NavigatorImpl
    private lateinit var navController: NavController

    private val viewModel by viewModels<MainViewModel>()

    override fun init() {
        navController = findNavController(R.id.navHostFragment)
        navigator = NavigatorImpl(this, navController)
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.navState.flowWithLifecycle(lifecycle).collect { data ->
                data?.let { navigator.goToScreen(it.action, it.bundle) }
            }
        }
    }

    override fun stopOps() {
        //
    }
}