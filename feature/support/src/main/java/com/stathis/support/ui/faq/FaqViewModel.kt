package com.stathis.support.ui.faq

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: com.stathis.domain.FetchFaqsUseCase
) : BaseViewModel(app) {

    val faq: StateFlow<NetworkResult<List<com.stathis.model.UiModel>>>
        get() = _faqs

    private val _faqs = MutableStateFlow<NetworkResult<List<com.stathis.model.UiModel>>>(NetworkResult.Loading())

    fun fetchFaqs() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { data ->
                _faqs.emit(data)
            }
        }
    }
}