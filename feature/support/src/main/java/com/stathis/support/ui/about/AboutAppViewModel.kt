package com.stathis.support.ui.about

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutAppViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: com.stathis.domain.about.FetchAboutAppInfoUseCase
) : BaseViewModel(app) {

    val aboutApp: StateFlow<List<com.stathis.model.UiModel>>
        get() = _aboutApp

    private val _aboutApp = MutableStateFlow<List<com.stathis.model.UiModel>>(listOf())

    fun fetchAboutAppInfo() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { info ->
                _aboutApp.emit(info)
            }
        }
    }
}