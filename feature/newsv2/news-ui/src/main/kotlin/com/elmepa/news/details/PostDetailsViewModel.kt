package com.elmepa.news.details

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.elmepa.news.details.PostDetailsView.Effect
import com.elmepa.news.details.PostDetailsView.State
import com.elmepa.news.details.PostDetailsView.UIAction
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.news.FetchPostDetailsUseCase
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PostDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fetchPostDetailsContentUseCase: FetchPostDetailsUseCase
) : BaseViewModel(app) {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

    fun fetchPostDetails(
        title: String,
        imageUrl: String,
        pubDate: String,
        scrapeUrl: String
    ) {
        viewModelScope.launch(dispatcher) {
            fetchPostDetailsContentUseCase(scrapeUrl)
                .catch { error -> _state.update { State.Error } }
                .onEach { htmlContentResult ->
                    _state.update { htmlContentResult.toUiState(title, imageUrl, pubDate, scrapeUrl) }
                }
                .flowOn(dispatcher)
                .launchIn(viewModelScope)
        }
    }

    fun onAction(action: UIAction) {
        val effect = when (action) {
            is UIAction.Back -> Effect.GoBack
            is UIAction.Share -> with(state.value as State.Content) {
                Effect.SharePost(title, openUrl)
            }
        }

        viewModelScope.launch(dispatcher) {
            _effect.emit(effect)
        }
    }

    private fun NetworkResult<String>.toUiState(title: String, imageUrl: String, pubDate: String, openUrl: String): State =
        when (this) {
            is NetworkResult.Loading -> State.Loading
            is NetworkResult.Success -> State.Content(
                title = title,
                image = imageUrl,
                pubDate = pubDate,
                openUrl = openUrl,
                htmlContent = data.orEmpty()
            )

            is NetworkResult.Failure -> State.Error
        }
}
