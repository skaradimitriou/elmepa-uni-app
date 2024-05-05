package com.stathis.elmepaunivapp

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.core.base.BaseActivity
import com.stathis.core.util.onBackButtonClick
import com.stathis.elmepaunivapp.databinding.ActivityMainBinding
import com.stathis.feature.navigation.NavigatorImpl
import com.stathis.feature.ui.MainViewModel
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

        setSupportActionBar(binding.toolbar)

        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            val isAtHomeScreens = navigator.isAtHomeScreens()
            supportActionBar?.setDisplayHomeAsUpEnabled(!isAtHomeScreens)
        }

        onBackButtonClick {
            navigator.goBack()
        }
    }

    override fun startOps() {
        lifecycleScope.launch {
            viewModel.navState.flowWithLifecycle(lifecycle).collect { data ->
                data?.let {
                    navigator.goToScreen(it.action, it.bundle)
                    viewModel.navigateWithAction(null)
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
