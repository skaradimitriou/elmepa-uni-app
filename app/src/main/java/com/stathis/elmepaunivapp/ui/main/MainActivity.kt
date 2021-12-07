package com.stathis.elmepaunivapp.ui.main

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaBindingActivity
import com.stathis.elmepaunivapp.databinding.ActivityMainBinding

class MainActivity : ElmepaBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

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