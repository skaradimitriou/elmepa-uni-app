package com.stathis.personnel.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.stathis.model.common.LinkType

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

