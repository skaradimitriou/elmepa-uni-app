package com.stathis.elmepaunivapp.ui.chatbot

import android.app.Application
import android.os.Handler
import android.view.View
import androidx.lifecycle.ViewModel
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaViewModel
import com.stathis.elmepaunivapp.callbacks.ChatbotClickListener
import com.stathis.elmepaunivapp.callbacks.ElmepaClickListener
import com.stathis.elmepaunivapp.abstraction.LocalModel
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader
import com.stathis.elmepaunivapp.ui.chatbot.model.Question
import com.stathis.elmepaunivapp.ui.chatbot.recyclerview.ChatbotAdapter

class ChatbotViewModel(app: Application) : ElmepaViewModel(app), ElmepaClickListener {

    private lateinit var callback: ChatbotClickListener
    private val messagesList = arrayListOf<LocalModel>()
    val adapter = ChatbotAdapter(this)
    private lateinit var answer: String

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
                "πρόγραμμα σπουδων"
            ) || response.contains("προγραμμα σπουδων") || response.contains("σπουδων")
        ) {
            answer = getString(R.string.chatbot_syllabus)
        } else if (response.contains("ωρολογιο προγραμμα") || response.contains("ωρολόγιο πρόγραμμα") || response.contains(
                "ωρολόγιο"
            ) || response.contains("ωρολογιο")
        ) {
            answer = getString(R.string.chatbot_schedule)
        } else if (response.contains("τηλέφωνο") || response.contains("τηλεφωνο") || response.contains(
                "γραμματεία"
            ) || response.contains("γραμματεια")
        ) {
            answer = getString(R.string.chatbot_call_secretary)
        } else if (response.contains("email") || response.contains("e-mail")) {
            answer = getString(R.string.chatbot_email_secretary)
        } else if (response.contains("καθηγητ") || response.contains("καθηγητές") || response.contains(
                "προσωπικό"
            )
        ) {
            answer = getString(R.string.chatbot_search_professor)
        } else if (response.contains("ανακοινώσεις") || response.contains("ανακοιν") || response.contains(
                "ανακοινωση"
            ) || response.contains("ανακοίνωση")
        ) {
            answer = getString(R.string.chatbot_announcements)
        } else if (response.contains("εικονική περιήγηση") || response.contains("περιήγηση") || response.contains(
                "περιηγηση"
            ) || response.contains("εικονικη")
        ) {
            answer = getString(R.string.chatbot_virtual_tour)
        } else if (response.contains("γεια") || response.contains("γειά")) {
            answer = getString(R.string.chatbot_hello)
        } else if (response.contains("ευχαριστώ") || response.contains("ευχαριστω")) {
            answer = getString(R.string.chatbot_you_re_welcome)
        } else {
            answer = getString(R.string.chatbot_dont_know_yet)
        }

        return answer
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is Answer -> checkWhichCallback(view.tag as Answer)
        }
    }

    private fun checkWhichCallback(answer: Answer) = when (answer.text) {
        getString(R.string.chatbot_announcements) -> callback.openAnnouncements()
        getString(R.string.chatbot_syllabus) -> callback.goToSyllabus()
        getString(R.string.chatbot_schedule) -> callback.openSchedule()
        getString(R.string.chatbot_call_secretary) -> callback.callSecretary()
        getString(R.string.chatbot_email_secretary) -> callback.emailToSecretary()
        getString(R.string.chatbot_virtual_tour) -> callback.virtualTour()
        else -> Unit
    }
}
