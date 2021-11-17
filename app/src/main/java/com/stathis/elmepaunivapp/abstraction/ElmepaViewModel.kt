package com.stathis.elmepaunivapp.abstraction

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class ElmepaViewModel(app : Application) : AndroidViewModel(app) {

    fun getString(id : Int) = getApplication<Application>().resources.getString(id)
}