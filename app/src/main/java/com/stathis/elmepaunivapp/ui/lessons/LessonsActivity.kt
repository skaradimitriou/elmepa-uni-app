package com.stathis.elmepaunivapp.ui.lessons

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.databinding.ActivitySyllabusLessonsBinding
import com.stathis.elmepaunivapp.ui.home.syllabus.model.Semester
import com.stathis.elmepaunivapp.util.onMenuItemTap
import com.stathis.elmepaunivapp.util.setupBar
import com.stathis.elmepaunivapp.util.showDialog

class LessonsActivity : ElmepaActivity<ActivitySyllabusLessonsBinding>(R.layout.activity_syllabus_lessons) {

    private val viewModel : LessonsViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun startOps() {
        val semester = intent.getParcelableExtra<Semester>(resources.getString(R.string.syllabus_intent_data))
        semester?.let {
            supportActionBar?.setupBar(it.semester)
            viewModel.bindList(it)
        }
    }

    override fun stopOps() {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lessons_menu, menu)

        val item: MenuItem? = menu?.findItem(R.id.lesson_info)
        item?.onMenuItemTap { openPopUpWindow() }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun openPopUpWindow() {
        MaterialAlertDialogBuilder(this).showDialog(
            title = getString(R.string.info_title),
            desc = getString(R.string.info_body)
        )
    }
}