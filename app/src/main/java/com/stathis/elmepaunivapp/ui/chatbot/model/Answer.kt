package com.stathis.elmepaunivapp.ui.chatbot.model

import com.stathis.elmepaunivapp.model.LocalModel

data class Answer(val text : String) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = false
}
