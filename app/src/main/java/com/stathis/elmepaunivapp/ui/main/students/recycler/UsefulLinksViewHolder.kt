package com.stathis.elmepaunivapp.ui.main.students.recycler

import android.util.Log
import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.main.students.model.UsefulLinks
import com.stathis.elmepaunivapp.ui.main.students.model.LinkItem
import com.stathis.elmepaunivapp.ui.main.students.model.SyllabusItem
import kotlinx.android.synthetic.main.useful_links_item_row.view.*
import java.lang.Exception

class UsefulLinksViewHolder(itemView: View, callback: ElmepaClickListener) :
    ElmepaViewHolder(itemView, callback) {

    override fun present(data: LocalModel) {
        when (data) {
            is LinkItem -> {
                itemView.links_holder_txt.text = data.title
                loadImg(data.imageResource)
            }

            is SyllabusItem -> {
                itemView.links_holder_txt.text = data.title
                loadImg(data.imageResource)
            }

            is UsefulLinks -> {
                itemView.links_holder_txt.text = data.name
                loadImg(data.imageResource)
            }
        }
    }

    private fun loadImg(img: String) {
        try {
            val myImage =
                itemView.resources.getIdentifier(img, "drawable", "com.stathis.elmepaunivapp")
            itemView.links_img.setImageResource(myImage)
        } catch (e: Exception) {
            Log.d("", "")
        }
    }
}
