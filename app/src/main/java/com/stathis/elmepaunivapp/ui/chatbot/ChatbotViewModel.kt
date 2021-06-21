package com.stathis.elmepaunivapp.ui.chatbot

import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.callbacks.ChatbotClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader
import com.stathis.elmepaunivapp.ui.chatbot.model.Question
import com.stathis.elmepaunivapp.ui.chatbot.recyclerview.ChatbotAdapter

class ChatbotViewModel : ViewModel(), ElmepaClickListener {

    private lateinit var callback: ChatbotClickListener
    private val messagesList = arrayListOf<LocalModel>()
    val adapter = ChatbotAdapter(this)
    private lateinit var answer : String

    init {
        messagesList.add(ChatbotHeader())
        adapter.submitList(messagesList)
    }

    fun bindCallbacks(callback: ChatbotClickListener) {
        this.callback = callback
    }

    fun getResponse(response: String) {
        messagesList.add(Question(response))

        Handler().postDelayed({
            messagesList.add(Answer(getChatbotAnswer(response)))
            adapter.notifyDataSetChanged()
            callback.chatbotReplied()
        }, 1200)
    }

    private fun getChatbotAnswer(response: String): String {
        if (response.contains("πρόγραμμα σπουδών") || response.contains("προγραμμα σπουδών") || response.contains(
                "πρόγραμμα σπουδων") || response.contains("προγραμμα σπουδων") || response.contains("σπουδων")) {
             answer = "Κάνε tap για να δείς το πρόγραμμα σπουδών!"
        } else if (response.contains("ωρολογιο προγραμμα") || response.contains("ωρολόγιο πρόγραμμα") || response.contains(
                "ωρολόγιο") || response.contains("ωρολογιο")) {
             answer = "Κάνε tap για να δείς το πρόγραμμα των μαθημάτων!"
        } else if (response.contains("τηλέφωνο") || response.contains("τηλεφωνο") || response.contains("γραμματεία") || response.contains("γραμματεια")) {
             answer = "Κάνε tap για να καλέσω την Γραμματεία του Τμήματος!"
        } else if (response.contains("email") || response.contains("e-mail")) {
             answer = "Κάνε tap για να στείλεις e-mail στην Γραμματεία!"
        } else if (response.contains("καθηγητ") || response.contains("καθηγητές") || response.contains(
                "προσωπικό")) {
             answer = "Κάνε tap για να αναζητήσεις έναν καθηγητή του Τμήματος!"
        } else if (response.contains("ανακοινώσεις") || response.contains("ανακοιν") || response.contains(
                "ανακοινωση") || response.contains("ανακοίνωση")) {
             answer = "Κάνε tap για να δεις τις ανακοινώσεις του Τμήματος!"
        } else if (response.contains("εικονική περιήγηση") || response.contains("περιήγηση") || response.contains(
                "περιηγηση") || response.contains("εικονικη")) {
             answer = "Κάνε tap για να δείς την εικονική περιήγηση!"
        } else if (response.contains("γεια") || response.contains("γειά")) {
             answer = "Γεία σου και εσένα!"
        } else if (response.contains("ευχαριστώ") || response.contains("ευχαριστω")) {
             answer = "Παρακαλώ!"
        } else {
             answer = "Δεν γνωρίζω την απάντηση ακόμα"
        }

        return answer
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Answer -> checkWhichCallback(view.tag as Answer)
        }
    }

    private fun checkWhichCallback(answer : Answer) {
        when(answer.text){
            "Κάνε tap για να δεις τις ανακοινώσεις του Τμήματος!" -> callback.openAnnouncements()
            "Κάνε tap για να δείς το πρόγραμμα σπουδών!" -> callback.goToSyllabus()
            "Κάνε tap για να δείς το πρόγραμμα των μαθημάτων!" -> callback.openSchedule()
            "Κάνε tap για να καλέσω την Γραμματεία του Τμήματος!" -> callback.callSecretary()
            "Κάνε tap για να στείλεις e-mail στην Γραμματεία!" -> callback.emailToSecretary()
            "Κάνε tap για να δείς την εικονική περιήγηση!" -> callback.virtualTour()
            else -> Unit
        }
    }
}
