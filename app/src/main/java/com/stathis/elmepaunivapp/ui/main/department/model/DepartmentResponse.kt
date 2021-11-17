package com.stathis.elmepaunivapp.ui.main.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.refactor.CarouselItem

data class DepartmentResponse(

    val carouselItems : List<CarouselItem>,
    val syllabusItems : List<FieldOfStudy>,
    val programmes : List<Programme>,
    val depMembers : List<DepMember>,
    val links : List<SocialChannel>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
