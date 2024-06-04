package com.stathis.core

import android.app.Application
import com.stathis.core.base.BaseViewModel
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.ProgrammeType
import javax.inject.Inject

class MainSharedViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    var selectedProgrammeType: ProgrammeType = ProgrammeType.UNDERGRADUATE

    var selectedOrientation: OrientationType = OrientationType.DATA
}