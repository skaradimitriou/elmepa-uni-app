package com.stathis.support.ui.about

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.about.FetchAboutAppInfoUseCase
import com.stathis.model.UiModel
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
    private val useCase: FetchAboutAppInfoUseCase
) : BaseViewModel(app) {

    val aboutApp: StateFlow<List<UiModel>>
        get() = _aboutApp

    private val _aboutApp = MutableStateFlow<List<UiModel>>(listOf())

    fun fetchAboutAppInfo() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { info ->
                _aboutApp.emit(info)
            }
        }
    }
}