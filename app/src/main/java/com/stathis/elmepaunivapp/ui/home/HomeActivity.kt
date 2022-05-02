package com.stathis.elmepaunivapp.ui.home

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivityHomeBinding
import com.stathis.elmepaunivapp.ui.about.AboutActivity
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.department.DepartmentActivity
import com.stathis.elmepaunivapp.ui.students.StudentActivity
import com.stathis.elmepaunivapp.util.closeMyDrawer
import com.stathis.elmepaunivapp.util.openOrClose

class HomeActivity : ElmepaActivity<ActivityHomeBinding>(R.layout.activity_home),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun startOps() {
        val cameFromWidget = intent.getBooleanExtra(resources.getString(R.string.widget_professor_intent), false)

        when (cameFromWidget) {
            true -> navController.navigate(R.id.nav_search)
            else -> Unit
        }

        binding.bottomNavigationMenu.setupWithNavController(navController)
        binding.drawerMenu.setNavigationItemSelectedListener(this)
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = binding.drawerLayout.openOrClose()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent: Intent = when (item.itemId) {
            R.id.announcements -> Intent(this, AnnouncementsActivity::class.java)
            R.id.department -> Intent(this, DepartmentActivity::class.java)
            R.id.students -> Intent(this, StudentActivity::class.java)
            else -> Intent(this, AboutActivity::class.java)
        }
        startActivity(intent)
        binding.drawerLayout.closeMyDrawer()
        return true
    }
}