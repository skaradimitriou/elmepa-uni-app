package com.stathis.elmepaunivapp.ui.chatbot.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader
import com.stathis.elmepaunivapp.ui.chatbot.model.Question

class ChatbotAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ChatbotViewHolder>(DiffItemClass<LocalModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatbotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType,parent,false)
        return ChatbotViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ChatbotViewHolder, position: Int) = holder.bindData(getItem(position))

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Question -> R.layout.holder_question_item
            is Answer -> R.layout.holder_answer_item
            is ChatbotHeader -> R.layout.holder_chatbot_header
            else -> R.layout.holder_empty_view
        }
    }
}