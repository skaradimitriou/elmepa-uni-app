package com.stathis.news.details

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.announcements.details.PostDetailsRequest
import com.stathis.model.announcements.details.PostDetailsResponse
import com.stathis.model.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val postDetailsUseCase: com.stathis.domain.news.FetchPostDetailsUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<PostDetailsResponse>>
        get() = _data

    private val _data =
        MutableStateFlow<NetworkResult<PostDetailsResponse>>(NetworkResult.Loading())

    fun fetchPostDetails(
        title: String,
        imageUrl: String,
        pubDate: String,
        scrapeUrl: String
    ) {
        viewModelScope.launch(dispatcher) {
            val request = PostDetailsRequest(title, imageUrl, pubDate, scrapeUrl)
//            postDetailsUseCase.invoke(request).collect { data ->
//                _data.emit(data)
//            }
        }
    }
}
