package com.stathis.feature.ui.professors

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.base.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchProfessorsUseCase
import com.stathis.domain.usecase.FilterProfessorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessorViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fetchProfessorsUseCase: FetchProfessorsUseCase,
    private val filterProfessorsUseCase: FilterProfessorsUseCase
) : BaseViewModel(app) {

    val professors: StateFlow<List<UiModel>>
        get() = _professors

    private val _professors = MutableStateFlow<List<UiModel>>(listOf())

    fun fetchProfessors() {
        viewModelScope.launch(dispatcher) {
            fetchProfessorsUseCase.invoke().collect { list ->
                _professors.emit(list)
            }
        }
    }

    fun filterProfessorsByName(name: String) {
        viewModelScope.launch(dispatcher) {
            filterProfessorsUseCase.invoke(name).collect { list ->
                _professors.emit(list)
            }
        }
    }
}
