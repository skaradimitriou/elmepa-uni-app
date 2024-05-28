package com.stathis.dashboard.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchDashboardDetailsUseCase
import com.stathis.model.UiModel
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
    private val useCase: FetchDashboardDetailsUseCase
) : BaseViewModel(app) {

    val dashboardDetails: SharedFlow<List<UiModel>>
        get() = _dashboardDetails

    private val _dashboardDetails = MutableStateFlow<List<UiModel>>(listOf())

    fun fetchDashboardDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { list ->
                _dashboardDetails.emit(list)
            }
        }
    }
}