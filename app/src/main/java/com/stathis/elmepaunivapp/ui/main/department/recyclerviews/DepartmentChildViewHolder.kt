package com.stathis.elmepaunivapp.ui.main.department.recyclerviews

import android.view.View
import android.widget.ImageView
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.department.model.DepMember
import com.stathis.elmepaunivapp.ui.main.department.model.FieldOfStudy
import com.stathis.elmepaunivapp.ui.main.department.model.Programme
import com.stathis.elmepaunivapp.ui.main.department.model.SocialChannel
import kotlinx.android.synthetic.main.dep_members_item_row.view.*
import kotlinx.android.synthetic.main.field_item_row.view.*
import kotlinx.android.synthetic.main.programmes_item_row.view.*
import kotlinx.android.synthetic.main.social_item_row.view.*
import java.lang.Exception

class DepartmentChildViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {
    override fun present(data: LocalModel) {
        when(data){
            is FieldOfStudy -> {
                itemView.fields_holder_txt.text = data.title
                loadImg(data.imageResource,itemView.fields_img)
            }

            is Programme -> {
                itemView.programmes_holder_txt.text = data.title
                itemView.programmes_description.text = data.description
                loadImg(data.imageResource,itemView.programmes_img)
            }
            is SocialChannel -> {
                itemView.social_txt.text = data.title
                loadImg(data.imageResource,itemView.social_circle_img)
            }
            is DepMember -> {
                itemView.dep_name.text = data.name
                loadImg(data.img,itemView.dep_img)
            }
        }
    }

    private fun loadImg(img : String,imgView : ImageView){
        try {
            val myImage = itemView.resources.getIdentifier(img, "drawable", "com.stathis.elmepaunivapp")
            imgView.setImageResource(myImage)
        }catch (e : Exception){
            //FIXME: Add default image
        }
    }
}
