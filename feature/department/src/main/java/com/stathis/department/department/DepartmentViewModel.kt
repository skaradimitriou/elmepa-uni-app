package com.stathis.department.department

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.common.base.BaseViewModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.department.FetchDepartmentInfoUseCase
import com.stathis.model.UiModel
import com.stathis.model.department.DepartmentPersonnelItem
import com.stathis.model.department.DepartmentProgrammeItem
import com.stathis.model.department.DepartmentSocialItem
import com.stathis.model.department.FieldOfStudyParent
import com.stathis.model.general.carousel.CarouselParent
import com.stathis.model.network.NetworkResult
import com.stathis.model.util.ShimmerGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchDepartmentInfoUseCase
) : BaseViewModel(app) {

    val data: StateFlow<NetworkResult<List<UiModel>>>
        get() = _data

    private val _data = MutableStateFlow<NetworkResult<List<UiModel>>>(NetworkResult.Loading(list))

    fun fetchScreenDetails() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { list ->
                _data.emit(list)
            }
        }
    }

    override fun onCleared() {
        useCase.cancelJob()
        super.onCleared()
    }

    companion object {
        val tempList = ShimmerGenerator.list.take(4)
        private val list = listOf(
            CarouselParent(tempList),
            FieldOfStudyParent(tempList),
            DepartmentProgrammeItem(tempList),
            DepartmentPersonnelItem(tempList),
            DepartmentSocialItem(tempList)
        )
    }
}