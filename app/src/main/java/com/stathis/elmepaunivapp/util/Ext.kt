package com.stathis.elmepaunivapp.util

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.stathis.elmepaunivapp.model.professor.Professor

fun SwipeRefreshLayout.stopRefresh() {
    this.isRefreshing = false
}

fun Application.readLocalJson(jsonName: String): String {
    return this.assets.open(jsonName).bufferedReader().use { it.readText() }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }
    })
}

fun Professor.equalsName(name: String): Boolean {
    return this.fullName.lowercase().contains(name.lowercase())
}

fun List<Professor>.sortedAlphabetically(): List<Professor> {
    return this.sortedWith(compareBy { it.fullName })
}

fun TabLayout.onTabSelected(selectedTab: (Int) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let { selectedTab.invoke(it) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}

fun MenuItem?.onMenuItemTap(callback : (MenuItem) -> Unit){
    this?.setOnMenuItemClickListener {
        callback.invoke(it)
        true
    }
}

fun MaterialAlertDialogBuilder.showDialog(title: String, desc : String){
    this.apply {
        this.setTitle(title)
        this.setMessage(desc)
    }.show()
}