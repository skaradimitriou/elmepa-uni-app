package com.stathis.elmepaunivapp.ui.students.recycler

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.students.model.UsefulLinksKt
import kotlinx.android.synthetic.main.useful_links_item_row.view.*

class UsefulLinksViewHolderKt(itemView : View,callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is UsefulLinksKt -> {
                itemView.links_holder_txt.text = data.name
                itemView.links_img.setImageResource(data.imageResource)
            }
        }
    }
}
