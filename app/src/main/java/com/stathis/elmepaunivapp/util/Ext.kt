package com.stathis.elmepaunivapp.util

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stathis.elmepaunivapp.ui.home.professors.model.Professor

fun SwipeRefreshLayout.stopRefresh() {
    this.isRefreshing = false
}

fun Application.readLocalJson(jsonName : String) : String {
    return this.assets.open(jsonName).bufferedReader().use { it.readText() }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit){
    this.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged.invoke(p0.toString())
        }
    })
}

fun Professor.equalsName(name : String): Boolean {
    return this.fullName.lowercase().contains(name.lowercase())
}

fun List<Professor>.sortedAlphabetically() : List<Professor> {
    return this.sortedWith(compareBy { it.fullName })
}