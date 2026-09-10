package com.elmepa.departmentv2.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.departmentv2.domain.model.DepartmentResponse
import com.elmepa.departmentv2.domain.repository.DepartmentRepository
import com.elmepa.departmentv2.presentation.list.DepartmentView.Effect
import com.elmepa.departmentv2.presentation.list.DepartmentView.State
import com.elmepa.departmentv2.presentation.list.DepartmentView.UiAction
import com.stathis.domain.model.DomainResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
internal class DepartmentViewModel @Inject constructor(
    private val repository: DepartmentRepository
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    val state: StateFlow<State> = _state.asStateFlow()
        .onStart { getDepartmentInformation() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    private fun getDepartmentInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L.milliseconds)
            val result = repository.getDepartmentScreenInfo()
            _state.update { result.toUiState() }
        }
    }

    // TODO 562 will connect the screen actions
    fun onAction(action: UiAction) {
        when (action) {
            else -> Unit
        }
    }

    private fun DomainResult<DepartmentResponse>.toUiState(): State = when (this) {
        is DomainResult.Loading<*> -> State.Loading
        is DomainResult.Error<*> -> State.Error
        is DomainResult.Success<DepartmentResponse> -> State.Content(data)
    }
}
