package com.stathis.elmepaunivapp.ui.home

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityHomeBinding

class HomeActivity : ElmepaActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var navController : NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun startOps() {
        val cameFromWidget = intent.getBooleanExtra(resources.getString(R.string.widget_professor_intent),false)

        when(cameFromWidget){
            true -> navController.navigate(R.id.nav_search)
            else -> Unit
        }

        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    override fun stopOps() {}
}