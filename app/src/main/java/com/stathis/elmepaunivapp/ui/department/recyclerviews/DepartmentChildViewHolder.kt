package com.stathis.elmepaunivapp.ui.department.recyclerviews

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.department.model.*
import kotlinx.android.synthetic.main.dep_members_item_row.view.*
import kotlinx.android.synthetic.main.field_item_row.view.*
import kotlinx.android.synthetic.main.programmes_item_row.view.*
import kotlinx.android.synthetic.main.social_item_row.view.*

class DepartmentChildViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {
    override fun present(data: LocalModel) {
        when(data){
            is FieldOfStudy -> {
                itemView.fields_holder_txt.text = data.name
                itemView.fields_img.setImageResource(data.image)
            }

            is Programme -> {
                itemView.programmes_holder_txt.text = data.name
                itemView.programmes_description.text = data.description
                itemView.programmes_img.setImageResource(data.image)
            }
            is SocialChannel -> {
                itemView.social_txt.text = data.name
                itemView.social_circle_img.setImageResource(data.image)
            }
            is DepMember -> {
                itemView.dep_name.text = data.name
                itemView.dep_img.setImageResource(data.image)
            }

            is ResearchKt -> {}
        }
    }
}
