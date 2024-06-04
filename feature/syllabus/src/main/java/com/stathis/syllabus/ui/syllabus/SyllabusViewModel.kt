package com.stathis.syllabus.ui.syllabus

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchSemestersUseCase
import com.stathis.model.network.NetworkResult
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    val app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchSemestersUseCase
) : BaseViewModel(app) {

    val semesters: StateFlow<NetworkResult<List<Programme>>>
        get() = _semesters

    private val _semesters =
        MutableStateFlow<NetworkResult<List<Programme>>>(NetworkResult.Loading())

    fun fetchSemestersByProgramme(programme: ProgrammeType?, orientation: OrientationType? = null) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(programme, orientation).collect {
                _semesters.emit(it)
            }
        }
    }
}