package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.research.model.ResearchItem

interface ResearchClickListener {
    fun onItemTap(item: ResearchItem)
}