package com.stathis.feature.ui.syllabus

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.stathis.core.base.BaseViewModel
import com.stathis.core.di.IoDispatcher
import com.stathis.domain.usecase.FetchSemestersUseCase
import com.stathis.model.syllabus.Semester
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SyllabusViewModel @Inject constructor(
    val app: Application,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchSemestersUseCase
) : BaseViewModel(app) {

    val semesters: SharedFlow<List<Semester>>
        get() = _semesters

    private val _semesters = MutableSharedFlow<List<Semester>>()

    fun fetchSemesters() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect {
                _semesters.emit(it)
            }
        }
    }
}