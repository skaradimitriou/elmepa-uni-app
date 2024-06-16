package com.stathis.personnel.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.common.R
import com.stathis.model.common.LinkType

@BindingAdapter("setPersonnelImage", "personnelGender")
fun ImageView.setPersonnelImage(url: String, gender: String) {
    val genderImg = when (gender) {
        resources.getString(R.string.male) -> R.drawable.male
        else -> R.drawable.female
    }

    Glide.with(context).load(url).placeholder(R.drawable.placeholder).error(genderImg).into(this)
}

@BindingAdapter("setPersonnelDescription")
fun TextView.setPersonnelDescription(description: String) {
    visibility = if (description.isEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }

    text = description
}

@BindingAdapter("setPersonnelProfession")
fun TextView.setPersonnelProfession(description: String) {
    text = description.lowercase().uppercase()
}

@BindingAdapter("setSkillPercent")
fun TextView.setSkillPercent(value: Int) {
    text = "$value%"
}

@BindingAdapter("setLinkTitle")
fun TextView.setLinkTitle(type: LinkType) {
    val title = when (type) {
        LinkType.CV -> resources.getString(com.stathis.personnel.R.string.cv)
        LinkType.LINKEDIN -> resources.getString(com.stathis.personnel.R.string.linkedin)
        LinkType.RESEARCH_GATE -> resources.getString(com.stathis.personnel.R.string.research_gate)
        LinkType.MAIL -> resources.getString(com.stathis.personnel.R.string.mail)
        LinkType.GOOGLE_SCHOLAR -> resources.getString(com.stathis.personnel.R.string.google_scholar)
        else -> ""
    }

    text = title
}

