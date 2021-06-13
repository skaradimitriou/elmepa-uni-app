package com.stathis.elmepaunivapp.ui.announcements.recyclerviews

import android.view.View
import com.squareup.picasso.Picasso
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.announcements.model.Announcement
import kotlinx.android.synthetic.main.announcement_item_row.view.*

class AnnouncementsViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView,callback) {

    override fun present(data: LocalModel) {
        when(data){
            is Announcement -> {
                itemView.ann_txt.text = data.name
                Picasso.get().load(data.imageResource).into(itemView.ann_img)
            }
        }
    }
}