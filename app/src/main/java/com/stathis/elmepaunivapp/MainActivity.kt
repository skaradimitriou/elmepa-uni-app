package com.stathis.elmepaunivapp

import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.common.MainSharedViewModel
import com.stathis.common.MainViewModel
import com.stathis.common.base.BaseActivity
import com.stathis.common.util.buildAndShowSnackBar
import com.stathis.common.util.networkmanager.NetworkStatus
import com.stathis.common.util.onBackButtonClick
import com.stathis.elmepaunivapp.databinding.ActivityMainBinding
import com.stathis.elmepaunivapp.navigation.NavigatorImpl
import com.stathis.model.navigation.NavigationAction
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
        navigator = NavigatorImpl(navController)

        setSupportActionBar(binding.toolbar)

        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isAtHomeScreens = navigator.isAtHomeScreens()
            supportActionBar?.setDisplayHomeAsUpEnabled(!isAtHomeScreens)

            val shouldHideToolbar = listOf(
                R.id.nav_home,
                R.id.aboutAppScreen,
                R.id.nav_syllabus,
                R.id.lessonsFragment,
                R.id.lessonDetailsFragment,
                R.id.applicationFormsFragment,
                R.id.faqFragment,
                R.id.contactFragment,
                R.id.depDetailsFragment,
                R.id.nav_personnel
            ).contains(destination.id)

            binding.toolbar.visibility = if (shouldHideToolbar) {
                View.GONE
            } else {
                View.VISIBLE
            }
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
                            title = getString(com.stathis.common.R.string.no_conn_title),
                            actionText = getString(com.stathis.common.R.string.no_conn_action),
                            callback = {
                                //FIXME: Implement reconnection attempt case
                            }
                        )
                    }

                    is NetworkStatus.Restored -> {
                        buildAndShowSnackBar(
                            view = binding.main,
                            title = getString(com.stathis.common.R.string.conn_available_again_title)
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
