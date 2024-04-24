package com.stathis.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    fun getString(id: Int) = getApplication<Application>().resources.getString(id)
}