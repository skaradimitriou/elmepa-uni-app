package com.elmepa.students.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.students.presentation.list.StudentsView.Effect
import com.elmepa.students.presentation.list.StudentsView.State
import com.elmepa.students.presentation.list.StudentsView.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
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

private const val FAKE_SCREEN_LOADING: Long = 1000L

@HiltViewModel
internal class StudentsViewModel @Inject constructor(
    // will be populated later on
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)

    val state: StateFlow<State> = _state
        .asStateFlow()
        .onStart { getDummyData() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    private fun getDummyData() {
        _state.update { State.Loading }
        viewModelScope.launch {
            delay(FAKE_SCREEN_LOADING)
            _state.update {
                State.Content(
                    // to be populated later on
                    items = listOf()
                )
            }
        }
    }

    fun onAction(action: UIAction) {
        when (action) {
            UIAction.Back -> {
                viewModelScope.launch {
                    _effect.emit(Effect.Back)
                }
            }

            UIAction.Retry -> getDummyData()
        }
    }
}
