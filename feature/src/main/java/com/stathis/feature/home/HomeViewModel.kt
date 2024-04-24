package com.stathis.feature.home

import android.app.Application
import com.stathis.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

}