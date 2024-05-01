package com.stathis.feature.ui.contact

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchContactDetailsUseCase
import com.stathis.model.contact.ContactItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchContactDetailsUseCase
) : BaseViewModel(app) {

    val contactDetails: SharedFlow<List<ContactItem>>
        get() = _contactDetails

    private val _contactDetails = MutableSharedFlow<List<ContactItem>>()

    fun fetchContactDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { result ->
                _contactDetails.emit(result)
            }
        }
    }
}