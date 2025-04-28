package com.elmepa.support.ui.applicationforms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.support.model.ApplicationForm
import com.elmepa.support.ui.applicationforms.ApplicationFormsView.Effect
import com.elmepa.support.ui.applicationforms.ApplicationFormsView.State
import com.elmepa.support.ui.applicationforms.ApplicationFormsView.UIAction
import com.elmepa.support.usecase.FetchApplicationFormsUseCase
import com.stathis.domain.model.DomainResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ApplicationFormsViewModel @Inject constructor(
    private val fetchApplicationFormsUseCase: FetchApplicationFormsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        getApplicationForms()
    }

    private fun getApplicationForms() {
        fetchApplicationFormsUseCase()
            .onEach { result -> _state.update { result.toUiState() } }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onAction(action: UIAction) {
        viewModelScope.launch {
            val effect = when (action) {
                is UIAction.Back -> Effect.Back
                is UIAction.OpenForm -> Effect.OpenBrowser(action.url)
            }
            _effect.emit(effect)
        }
    }

    private fun DomainResult<List<ApplicationForm>>.toUiState() = when (this) {
        is DomainResult.Loading -> State.Loading
        is DomainResult.Success<List<ApplicationForm>> -> State.Content(data)
        is DomainResult.Error -> State.Error
    }
}
