package com.stathis.elmepaunivapp.ui.home

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityHomeBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity

class HomeActivity : ElmepaActivity<ActivityHomeBinding>(R.layout.activity_home),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController : NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun startOps() {
        val cameFromWidget = intent.getBooleanExtra(resources.getString(R.string.widget_professor_intent),false)

        when(cameFromWidget){
            true -> navController.navigate(R.id.nav_search)
            else -> Unit
        }

        binding.bottomNavigationMenu.setupWithNavController(navController)
        binding.drawerMenu.setNavigationItemSelectedListener(this)
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (binding.drawerLayout.isOpen) {
            true -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            false -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.announcements -> {
                startActivity(Intent(this, AnnouncementsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.department -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.students -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.professors -> {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        return true
    }
}