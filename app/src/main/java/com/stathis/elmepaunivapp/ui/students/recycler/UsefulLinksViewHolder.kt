package com.stathis.elmepaunivapp.ui.students.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks
import kotlinx.android.synthetic.main.useful_links_item_row.view.*

class UsefulLinksViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is UsefulLinks -> {
                itemView.links_holder_txt.text = data.name
                itemView.links_img.setImageResource(data.imageResource)
            }
        }
    }
}
