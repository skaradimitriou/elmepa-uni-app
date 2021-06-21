package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.*
import kotlinx.android.synthetic.main.dep_members_item_row.view.*
import kotlinx.android.synthetic.main.field_item_row.view.*
import kotlinx.android.synthetic.main.programmes_item_row.view.*
import kotlinx.android.synthetic.main.social_item_row.view.*

class DepartmentChildViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {
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

            is Research -> {}
        }
    }
}
