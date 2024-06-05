package com.stathis.dashboard.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: com.stathis.domain.FetchDashboardDetailsUseCase
) : BaseViewModel(app) {

    val dashboardDetails: SharedFlow<List<com.stathis.model.UiModel>>
        get() = _dashboardDetails

    private val _dashboardDetails = MutableStateFlow<List<com.stathis.model.UiModel>>(listOf())

    fun fetchDashboardDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { list ->
                _dashboardDetails.emit(list)
            }
        }
    }
}