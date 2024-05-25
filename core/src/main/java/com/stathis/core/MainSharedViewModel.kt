package com.stathis.core

import android.app.Application
import com.stathis.core.base.BaseViewModel
import com.stathis.model.syllabus.OrientationType
import javax.inject.Inject

class MainSharedViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    var selectedOrientation: OrientationType? = null
}