package com.stathis.students

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: com.stathis.domain.FetchStudentsScreenDataUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<List<com.stathis.model.UiModel>>>
        get() = _data

    private val _data = MutableStateFlow<NetworkResult<List<com.stathis.model.UiModel>>>(NetworkResult.Loading())

    fun fetchStudentInformation() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { information ->
                _data.emit(information)
            }
        }
    }
}