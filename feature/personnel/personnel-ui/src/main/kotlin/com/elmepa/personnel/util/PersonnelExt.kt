package com.elmepa.personnel.util

import androidx.annotation.DrawableRes
import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.ui.R

@get:DrawableRes
internal val Person.imageByGender: Int
    get() = when (gender) {
        Gender.MALE -> R.drawable.male
        Gender.FEMALE -> R.drawable.female
    }
