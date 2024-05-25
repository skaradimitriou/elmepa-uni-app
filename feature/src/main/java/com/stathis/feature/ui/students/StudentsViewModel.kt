package com.stathis.feature.ui.students

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.model.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchStudentsScreenDataUseCase
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
    private val useCase: FetchStudentsScreenDataUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<List<UiModel>>>
        get() = _data

    private val _data = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchStudentInformation() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { information ->
                _data.emit(information)
            }
        }
    }
}