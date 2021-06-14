package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import kotlinx.android.synthetic.main.useful_links_item_row.view.*

class UsefulLinksViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is UsefulLinks -> {
                itemView.links_holder_txt.text = data.name
                itemView.links_img.setImageResource(data.imageResource)
            }
        }
    }
}
