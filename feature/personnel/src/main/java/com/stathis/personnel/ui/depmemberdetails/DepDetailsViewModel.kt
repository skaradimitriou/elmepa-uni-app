package com.stathis.personnel.ui.depmemberdetails

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.model.UiModel
import com.stathis.model.common.Header
import com.stathis.model.common.Link
import com.stathis.model.common.LinkType
import com.stathis.model.department.DepMember
import com.stathis.personnel.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepDetailsViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel(app) {

    val data: StateFlow<List<UiModel>>
        get() = _data

    private var _data = MutableStateFlow<List<UiModel>>(listOf())

    fun setCurrentDepMember(model: DepMember) {
        viewModelScope.launch(dispatcher) {
            val list = mutableListOf<UiModel>().apply {
                add(model)
                add(Header(getString(R.string.sectors)))
                addAll(model.skills)
                add(Header(getString(R.string.links)))
                add(
                    Link(
                        title = getString(R.string.cv),
                        openUrl = model.linkToResume,
                        type = LinkType.CV
                    )
                )
                addAll(model.links)
            }

            _data.emit(list)
        }
    }
}