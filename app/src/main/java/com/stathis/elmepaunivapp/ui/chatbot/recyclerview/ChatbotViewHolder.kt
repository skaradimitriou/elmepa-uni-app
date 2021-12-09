package com.stathis.elmepaunivapp.ui.chatbot.recyclerview

import androidx.databinding.ViewDataBinding
import com.stathis.elmepaunivapp.abstraction.AbstractViewHolder
import com.stathis.elmepaunivapp.BR
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader
import com.stathis.elmepaunivapp.ui.chatbot.model.Question

class ChatbotViewHolder(val binding : ViewDataBinding, val callback : ElmepaClickListener) : AbstractViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is Answer -> {
                binding.setVariable(BR.answer,data)
                binding.setVariable(BR.callback,callback)
            }
            is Question -> {
                binding.setVariable(BR.question,data)
                binding.setVariable(BR.callback,callback)
            }
            is ChatbotHeader -> {}
        }
    }
}
