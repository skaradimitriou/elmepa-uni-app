package com.stathis.department.research

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchResearchInDeptUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.research.ResearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResearchViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchResearchInDeptUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<List<ResearchResponse>>>
        get() = _data

    private val _data =
        MutableStateFlow<NetworkResult<List<ResearchResponse>>>(NetworkResult.Loading())

    fun fetchResearchInformation() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect {
                _data.emit(it)
            }
        }
    }
}
