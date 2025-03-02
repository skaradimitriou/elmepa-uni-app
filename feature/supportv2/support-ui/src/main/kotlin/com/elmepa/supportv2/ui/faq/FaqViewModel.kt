package com.elmepa.supportv2.ui.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.supportv2.usecase.FetchFaqsUseCase
import com.stathis.domain.model.DomainResult
import com.stathis.model.support.Faq
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FaqViewModel @Inject constructor(
    private val fetchFaqUseCase: FetchFaqsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<FaqView.State> = MutableStateFlow(FaqView.State.Loading)

    val state: StateFlow<FaqView.State> = _state.asStateFlow()

    init {
        getFaqs()
    }

    private fun getFaqs() {
        fetchFaqUseCase()
            .onEach { result ->
                _state.update { result.toUiState() }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun DomainResult<List<Faq>>.toUiState() = when (this) {
        is DomainResult.Loading -> FaqView.State.Loading
        is DomainResult.Success<List<Faq>> -> FaqView.State.Content(data)
        is DomainResult.Error -> FaqView.State.Error(exception.message.toString())
    }
}
