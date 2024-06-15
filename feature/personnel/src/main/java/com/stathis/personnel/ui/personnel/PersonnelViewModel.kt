package com.stathis.personnel.ui.personnel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.personnel.FetchPersonnelUseCase
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonnelViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fetchPersonnelUseCase: FetchPersonnelUseCase,
    private val filterPersonnelUseCase: com.stathis.domain.FilterPersonnelUseCase
) : BaseViewModel(app) {

    val personnel: StateFlow<NetworkResult<List<com.stathis.model.UiModel>>>
        get() = _personnel

    private val _personnel =
        MutableStateFlow<NetworkResult<List<com.stathis.model.UiModel>>>(NetworkResult.Loading())

    fun fetchPersonnel() {
        viewModelScope.launch(dispatcher) {
            fetchPersonnelUseCase.invoke().collect { list ->
                _personnel.emit(list)
            }
        }
    }

    fun filterPersonnelByName(name: String) {
        viewModelScope.launch(dispatcher) {
            filterPersonnelUseCase.invoke(name).collect { list ->
                _personnel.emit(list)
            }
        }
    }
}
