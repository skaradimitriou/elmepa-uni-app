package com.elmepa.personnel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.personnel.list.PersonnelListView.State
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.usecase.FetchPersonnelUseCase
import com.stathis.domain.model.DomainResult
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
internal class PersonnelViewModel @Inject constructor(
    private val fetchPersonnelUseCase: FetchPersonnelUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        getPersonnel()
    }

    private fun getPersonnel() {
        fetchPersonnelUseCase()
            .onEach { result -> _state.update { result.toUiState() } }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun DomainResult<List<Person>>.toUiState() = when (this) {
        is DomainResult.Loading -> State.Loading
        is DomainResult.Success<List<Person>> -> State.Content(data)
        is DomainResult.Error<*> -> State.Error
    }
}
