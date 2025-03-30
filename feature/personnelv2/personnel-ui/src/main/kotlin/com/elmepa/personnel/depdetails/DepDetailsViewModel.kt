package com.elmepa.personnel.depdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmepa.personnel.depdetails.DepDetailsView.Effect
import com.elmepa.personnel.depdetails.DepDetailsView.State
import com.elmepa.personnel.depdetails.DepDetailsView.UIAction
import com.stathis.model.common.Link
import com.stathis.model.common.LinkType
import com.stathis.model.department.DepMember
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class DepDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun setCurrentDepMember(model: DepMember) {
        _state.update { State.Content(model) }
    }

    fun onAction(action: UIAction) {
        when (action) {
            is UIAction.OpenLink -> handleLinkByType(action.link)
        }
    }

    private fun handleLinkByType(link: Link) {
        viewModelScope.launch {
            val effect = when (link.type) {
                LinkType.MAIL -> Effect.SendEmail(link.openUrl)
                else -> Effect.OpenBrowser(link.openUrl)
            }
            _effect.emit(effect)
        }
    }
}
