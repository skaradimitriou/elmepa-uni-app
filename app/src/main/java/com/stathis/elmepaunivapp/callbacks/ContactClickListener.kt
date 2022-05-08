package com.stathis.elmepaunivapp.callbacks

import com.stathis.elmepaunivapp.ui.contact.model.ContactItem

interface ContactClickListener {
    fun onItemTap(item : ContactItem)
}