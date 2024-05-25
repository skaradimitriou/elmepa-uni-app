package com.stathis.feature.ui.personnel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.model.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchPersonnelUseCase
import com.stathis.domain.usecase.FilterPersonnelUseCase
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
    private val filterPersonnelUseCase: FilterPersonnelUseCase
) : BaseViewModel(app) {

    val personnel: StateFlow<NetworkResult<List<UiModel>>>
        get() = _personnel

    private val _personnel = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

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
