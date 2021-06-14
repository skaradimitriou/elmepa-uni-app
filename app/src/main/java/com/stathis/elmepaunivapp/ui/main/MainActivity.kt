package com.stathis.elmepaunivapp.ui.main

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity

class MainActivity : ElmepaActivity(R.layout.activity_main) {

    override fun init() {
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_menu)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun startOps() {}

    override fun stopOps() {}
}