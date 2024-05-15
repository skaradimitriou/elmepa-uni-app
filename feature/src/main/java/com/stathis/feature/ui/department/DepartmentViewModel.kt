package com.stathis.feature.ui.department

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.base.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchDepartmentInfoUseCase
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchDepartmentInfoUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<List<UiModel>>>
        get() = _data

    private val _data = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchScreenDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { list ->
                _data.emit(list)
            }
        }
    }
}