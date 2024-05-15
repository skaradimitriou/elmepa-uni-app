package com.stathis.feature.ui.faq

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.base.UiModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchFaqsUseCase
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
    private val useCase: FetchFaqsUseCase
) : BaseViewModel(app) {

    val faq: StateFlow<NetworkResult<List<UiModel>>>
        get() = _faqs

    private val _faqs = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading())

    fun fetchFaqs() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { data ->
                _faqs.emit(data)
            }
        }
    }
}