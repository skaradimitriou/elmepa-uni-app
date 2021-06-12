package com.stathis.elmepaunivapp.ui.dashboard.recyclerview

import android.view.View
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.listeners.latest.ElmepaClickListener
import com.stathis.elmepaunivapp.model.LocalModel
import com.stathis.elmepaunivapp.ui.dashboard.model.DashboardOption
import kotlinx.android.synthetic.main.holder_dashboard_option.view.*

class DashboardScreenViewHolder(itemView : View, callback : ElmepaClickListener) : AbstractViewHolder(itemView, callback) {

    override fun present(data: LocalModel) {
        when(data){
            is DashboardOption -> {
                itemView.dashboard_option_img.setImageResource(data.drawable)
                itemView.dashboard_option_text.text = data.title
            }
        }
    }
}
