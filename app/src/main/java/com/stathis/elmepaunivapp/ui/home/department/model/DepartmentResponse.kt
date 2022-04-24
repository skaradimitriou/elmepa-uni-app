package com.stathis.elmepaunivapp.ui.home.department.model

import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.home.students.model.CarouselItem

data class DepartmentResponse(

    val carouselItems : List<CarouselItem>,
    val syllabusItems : List<FieldOfStudy>,
    val programmes : List<Programme>,
    val depMembers : List<DepMember>,
    val links : List<SocialChannel>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is DepartmentResponse -> carouselItems == obj.carouselItems && syllabusItems == obj.syllabusItems && programmes == obj.programmes && depMembers == obj.depMembers && links == obj.links
        else -> false
    }
}
