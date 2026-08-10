package com.elmepa.students.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.students.presentation.list.StudentsView.Effect
import com.elmepa.students.presentation.list.StudentsView.State
import com.elmepa.students.presentation.list.StudentsView.UIAction
import com.stathis.domain.model.DomainResult
import com.elmepa.students.domain.model.StudentSection
import com.elmepa.students.domain.repository.StudentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StudentsViewModel @Inject constructor(
    private val studentsRepository: StudentsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)

    val state: StateFlow<State> = _state
        .asStateFlow()
        .onStart { getScreenInformation() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    private fun getScreenInformation() {
        _state.update { State.Loading }
        viewModelScope.launch(Dispatchers.IO) {
            studentsRepository.getStudentScreenInfo()
                .onEach { result ->
                    val uiState = result.toUiState()
                    _state.update { uiState }
                }
                .collect()
        }
    }

    fun onAction(action: UIAction) {
        when (action) {
            UIAction.Back -> emitEffect(Effect.Back)
            UIAction.Retry -> getScreenInformation()
            is UIAction.OpenUrlInBrowser -> emitEffect(Effect.OpenUrlInBrowser(action.url))
            is UIAction.OpenUrlInWebView -> emitEffect(Effect.OpenUrlInWebView(action.title, action.url))
            UIAction.OpenAcademicSchedule -> emitEffect(Effect.OpenAcademicSchedule)
        }
    }

    private fun DomainResult<List<StudentSection>>.toUiState() = when (this) {
        is DomainResult.Loading -> State.Loading
        is DomainResult.Success -> State.Content(data)
        is DomainResult.Error -> State.Error
    }

    private fun emitEffect(effect: Effect) {
        viewModelScope.launch(Dispatchers.Default) {
            _effect.emit(effect)
        }
    }
}
