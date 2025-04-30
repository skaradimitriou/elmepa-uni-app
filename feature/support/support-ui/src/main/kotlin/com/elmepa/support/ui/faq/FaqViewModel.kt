package com.elmepa.support.ui.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.support.model.Faq
import com.elmepa.support.ui.faq.FaqView.Effect
import com.elmepa.support.ui.faq.FaqView.UIAction
import com.elmepa.support.usecase.FetchFaqsUseCase
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
internal class FaqViewModel @Inject constructor(
    private val fetchFaqUseCase: FetchFaqsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<FaqView.State> = MutableStateFlow(FaqView.State.Loading)
    val state: StateFlow<FaqView.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        getFaqs()
    }

    private fun getFaqs() {
        fetchFaqUseCase()
            .onEach { result -> _state.update { result.toUiState() } }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onAction(action: UIAction) {
        val effect = when (action) {
            is UIAction.Back -> Effect.Back
        }

        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    private fun DomainResult<List<Faq>>.toUiState() = when (this) {
        is DomainResult.Loading -> FaqView.State.Loading
        is DomainResult.Success<List<Faq>> -> FaqView.State.Content(data)
        is DomainResult.Error -> FaqView.State.Error(exception.message.toString())
    }
}
