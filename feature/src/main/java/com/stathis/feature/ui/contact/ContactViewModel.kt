package com.stathis.feature.ui.contact

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchContactDetailsUseCase
import com.stathis.model.contact.ContactItem
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

    val contactDetails: StateFlow<List<ContactItem>>
        get() = _contactDetails

    private val _contactDetails = MutableStateFlow<List<ContactItem>>(listOf())

    fun fetchContactDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { result ->
                _contactDetails.emit(result)
            }
        }
    }
}