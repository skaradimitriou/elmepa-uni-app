package com.stathis.elmepaunivapp.ui.main.dashboard

import android.app.Application
import android.content.Context
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.DashboardClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.ui.main.dashboard.model.DashboardOption
import com.stathis.elmepaunivapp.ui.main.dashboard.recyclerview.DashboardAdapter

class DashboardViewModel(val app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    val adapter = DashboardAdapter(this)
    private lateinit var callback: DashboardClickListener

    fun bindList(callback: DashboardClickListener) {
        this.callback = callback

        adapter.submitList(
            listOf(
                DashboardOption(getString(R.string.dashboard_announcements), R.drawable.ic_announcement),
                DashboardOption(getString(R.string.dashboard_department), R.drawable.ic_books),
                DashboardOption(getString(R.string.dashboard_students), R.drawable.ic_student),
                DashboardOption(getString(R.string.dashboard_professors), R.drawable.ic_teacher)
            )
        )
    }

    fun showDialog(context: Context) {
        val aboutText = getString(R.string.about_app_text)
        val builder = MaterialAlertDialogBuilder(context)
        builder.setTitle(getString(R.string.about_app_title)).also {
            it.setMessage(aboutText)
            it.setPositiveButton(getString(R.string.about_app_learnMore)) { dialog, which ->
                callback.learnMore()
            }
            it.setNegativeButton(getString(R.string.about_app_cancel)) { dialog, which ->
                dialog.dismiss()
            }
        }.show()
    }

    override fun onItemClick(view: View) = when (view.tag) {
       is DashboardOption -> callback.dashboardItemClicked(view.tag as DashboardOption)
       else -> Unit
    }
}