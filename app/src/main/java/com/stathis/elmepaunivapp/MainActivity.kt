package com.stathis.elmepaunivapp

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.core.base.BaseActivity
import com.stathis.core.util.buildAndShowSnackBar
import com.stathis.core.util.networkmanager.NetworkStatus
import com.stathis.core.util.onBackButtonClick
import com.stathis.elmepaunivapp.databinding.ActivityMainBinding
import com.stathis.feature.navigation.NavigationAction
import com.stathis.feature.navigation.NavigatorImpl
import com.stathis.feature.ui.MainSharedViewModel
import com.stathis.feature.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navigator: NavigatorImpl
    private lateinit var navController: NavController

    private val viewModel by viewModels<MainViewModel>()
    private val sharedViewModel by viewModels<MainSharedViewModel>()

    override fun init() {
        navController = findNavController(R.id.navHostFragment)
        navigator = NavigatorImpl(this, navController)

        setSupportActionBar(binding.toolbar)

        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            val isAtHomeScreens = navigator.isAtHomeScreens()
            supportActionBar?.setDisplayHomeAsUpEnabled(!isAtHomeScreens)
        }

        val cameFromWidget = intent.getBooleanExtra(getString(R.string.open_personnel), false)
        if (cameFromWidget) {
            navigator.goToScreen(NavigationAction.PERSONNEL)
            viewModel.navigateWithAction(null)
        }

        onBackButtonClick {
            navigator.goBack()
            viewModel.navigateWithAction(null)
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.navState.flowWithLifecycle(lifecycle).collect { model ->
                model?.let { data ->
                    data.action?.let { action ->
                        navigator.goToScreen(action, data.bundle)
                        viewModel.navigateWithAction(null)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.networkStatus.flowWithLifecycle(lifecycle).collect { status ->
                when (status) {
                    is NetworkStatus.Unavailable -> {
                        buildAndShowSnackBar(
                            view = binding.main,
                            title = getString(com.stathis.core.R.string.no_conn_title),
                            actionText = getString(com.stathis.core.R.string.no_conn_action),
                            callback = {
                                viewModel.tryReconnecting()
                            }
                        )
                    }

                    is NetworkStatus.Restored -> {
                        buildAndShowSnackBar(
                            view = binding.main,
                            title = getString(com.stathis.core.R.string.conn_available_again_title)
                        )
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            navigator.goBack()
            viewModel.navigateWithAction(null)
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
