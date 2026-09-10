package com.elmepa.departmentv2.presentation.list

import com.elmepa.departmentv2.domain.model.DepartmentResponse

internal object DepartmentView {

    sealed interface State {
        data object Loading : State
        data class Content(val data: DepartmentResponse) : State
        data object Error : State
    }

    sealed interface UiAction {
        // TODO 562 will connect the screen actions
    }

    sealed interface Effect {
        // TODO 562 will connect the screen actions
    }
}
