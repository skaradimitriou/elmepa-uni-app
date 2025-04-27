package com.elmepa.support.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.support.model.ContactItem
import com.elmepa.support.ui.contact.ContactView.Effect
import com.elmepa.support.ui.contact.ContactView.State
import com.elmepa.support.ui.contact.ContactView.UIAction
import com.elmepa.support.usecase.FetchContactInfoUseCase
import com.stathis.domain.model.DomainResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ContactViewModel @Inject constructor(
    fetchContactInfoUseCase: FetchContactInfoUseCase
) : ViewModel() {

    val state: StateFlow<State> = fetchContactInfoUseCase()
        .map { result -> result.toUiState() }
        .onStart { emit(State.Loading) }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun onAction(action: UIAction) {
        viewModelScope.launch {
            val effect = when (action) {
                is UIAction.Back -> Effect.Back
                is UIAction.CallSecretary -> Effect.OpenDialer(action.telephoneNumber)
                is UIAction.SendEmail -> Effect.OpenEmailProvider(action.email)
                is UIAction.OpenUrl -> Effect.OpenUrl(action.url)
            }

            _effect.emit(effect)
        }
    }

    private fun DomainResult<List<ContactItem>>.toUiState() = when (this) {
        is DomainResult.Loading -> State.Loading
        is DomainResult.Success<List<ContactItem>> -> State.Content(data)
        is DomainResult.Error -> State.Error
    }
}
