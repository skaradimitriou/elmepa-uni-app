package com.stathis.elmepaunivapp.ui.main.dashboard.recyclerview

import android.view.View
import com.stathis.elmepaunivapp.abstraction.ElmepaViewHolder
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.main.dashboard.model.DashboardOption
import kotlinx.android.synthetic.main.holder_dashboard_option.view.*

class DashboardScreenViewHolder(itemView : View, callback : ElmepaClickListener) : ElmepaViewHolder(itemView, callback) {

    override fun present(data: LocalModel) {
        when(data){
            is DashboardOption -> {
                itemView.dashboard_option_img.setImageResource(data.drawable)
                itemView.dashboard_option_text.text = data.title
            }
        }
    }
}
