package com.stathis.support.ui.applicationforms

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.support.FetchApplicationFormsUseCase
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationFormsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchApplicationFormsUseCase
) : BaseViewModel(app) {

    val applicationForms
        get() = _applicationForms

    private val _applicationForms =
        MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchApplicationForms() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { data ->
                _applicationForms.emit(data)
            }
        }
    }
}