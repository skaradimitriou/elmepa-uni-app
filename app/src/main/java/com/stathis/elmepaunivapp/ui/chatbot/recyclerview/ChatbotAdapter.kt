package com.stathis.elmepaunivapp.ui.chatbot.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.DiffItemClass
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.databinding.HolderAnswerItemBinding
import com.stathis.elmepaunivapp.databinding.HolderChatbotHeaderBinding
import com.stathis.elmepaunivapp.databinding.HolderEmptyItemRowBinding
import com.stathis.elmepaunivapp.databinding.HolderQuestionItemBinding
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader
import com.stathis.elmepaunivapp.ui.chatbot.model.Question

class ChatbotAdapter(private val callback : ElmepaClickListener) : ListAdapter<LocalModel, ChatbotViewHolder>(DiffItemClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatbotViewHolder = when(viewType){
        R.layout.holder_question_item -> {
            val view = HolderQuestionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ChatbotViewHolder(view,callback)
        }
        R.layout.holder_answer_item -> {
            val view = HolderAnswerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ChatbotViewHolder(view,callback)
        }
        R.layout.holder_chatbot_header -> {
            val view = HolderChatbotHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ChatbotViewHolder(view,callback)
        }
        else -> {
            val view = HolderEmptyItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ChatbotViewHolder(view,callback)
        }
    }

    override fun onBindViewHolder(holder: ChatbotViewHolder, position: Int) = holder.bindData(getItem(position))

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is Question -> R.layout.holder_question_item
        is Answer -> R.layout.holder_answer_item
        is ChatbotHeader -> R.layout.holder_chatbot_header
        else -> R.layout.holder_empty_item_row
    }
}