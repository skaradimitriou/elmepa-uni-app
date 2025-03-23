package com.elmepa.personnel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.personnel.list.PersonnelListView.Effect
import com.elmepa.personnel.list.PersonnelListView.State
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.usecase.FetchPersonnelUseCase
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
internal class PersonnelViewModel @Inject constructor(
    private val fetchPersonnelUseCase: FetchPersonnelUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    init {
        getPersonnel()
    }

    private fun getPersonnel(query: String? = null) {
        fetchPersonnelUseCase(query)
            .onEach { result -> _state.update { result.toUiState() } }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun onAction(action: PersonnelListView.UIAction) = when (action) {
        is PersonnelListView.UIAction.SearchPersonByName -> getPersonnel(query = action.query)

        is PersonnelListView.UIAction.PersonTap -> viewModelScope.launch {
            _effect.emit(Effect.OpenBottomSheet(action.person))
        }
    }

    private fun DomainResult<List<Person>>.toUiState() = when (this) {
        is DomainResult.Loading -> State.Loading
        is DomainResult.Success<List<Person>> -> State.Content(data)
        is DomainResult.Error<*> -> State.Error
    }
}
