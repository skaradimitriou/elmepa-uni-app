package com.stathis.support.ui.contact

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchContactDetailsUseCase
import com.stathis.model.UiModel
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchContactDetailsUseCase
) : BaseViewModel(app) {

    val contactDetails: StateFlow<NetworkResult<List<UiModel>>>
        get() = _contactDetails

    private val _contactDetails =
        MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchContactDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { result ->
                _contactDetails.emit(result)
            }
        }
    }
}