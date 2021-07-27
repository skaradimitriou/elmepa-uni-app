package com.stathis.elmepaunivapp.ui.main

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity

class MainActivity : ElmepaActivity(R.layout.activity_main) {

    private lateinit var navView : BottomNavigationView
    private lateinit var navController : NavController


    override fun init() {
        navView = findViewById(R.id.bottom_navigation_menu)
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun startOps() {
        val cameFromWidget = intent.getBooleanExtra("OPEN_PROFESSORS",false)

        when(cameFromWidget){
            true -> navController.navigate(R.id.nav_search)
            else -> Unit
        }

        navView.setupWithNavController(navController)
    }

    override fun stopOps() {}
}