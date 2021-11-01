package com.stathis.elmepaunivapp.ui.chatbot

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaActivity
import com.stathis.elmepaunivapp.callbacks.ChatbotClickListener
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatbotActivity : ElmepaActivity(R.layout.activity_chat_bot) {

    private lateinit var viewModel : ChatbotViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ChatbotViewModel::class.java)
    }

    override fun startOps() {
        user_messagesRecView.adapter = viewModel.adapter

        viewModel.bindCallbacks(object : ChatbotClickListener {
            override fun goToSyllabus() {
                startActivity(Intent(this@ChatbotActivity, SyllabusActivity::class.java))
            }

            override fun openSchedule() {
                startActivity(Intent(this@ChatbotActivity, WebviewActivity::class.java)
                    .putExtra("URL", "https://mst.hmu.gr/proptyxiako/orologio-programma-mathimaton/"))

            }

            override fun callSecretary() {
                callSecretaryOffice()
            }

            override fun emailToSecretary() {
                emailSecretaryOffice()
            }

            override fun virtualTour() {
                startActivity(Intent(this@ChatbotActivity,WebviewActivity::class.java)
                    .putExtra("URL","https://mst.hmu.gr/hmutour/"))
            }

            override fun openAnnouncements() {
                startActivity(Intent(this@ChatbotActivity, AnnouncementsActivity::class.java))
            }

            override fun chatbotReplied() {
                //this line of code scrolls to the last item of the list that is passed to the adapter
                // it creates an illusion of the chat interactiveness

                user_messagesRecView.smoothScrollToPosition(user_messagesRecView.bottom)
                viewModel.adapter.notifyDataSetChanged()
            }
        })

        ask_questions.setOnEditorActionListener { view, actionId, event ->
            when (actionId == EditorInfo.IME_ACTION_DONE) {
                true -> {
                    val response = ask_questions.text.toString().toLowerCase()
                    Log.d("RESPONSE", response)
                    ask_questions.text?.clear()
                    hideKeyboard(view)

                    viewModel.getResponse(response)

                    // this line of code scrolls to the last item of the list that is passed to the adapter
                    // it creates an illusion of the chat interactiveness
                    when (viewModel.adapter.currentList.size > 1) {
                        true -> {
                            user_messagesRecView.smoothScrollToPosition(user_messagesRecView.bottom)
                            viewModel.adapter.notifyDataSetChanged()
                        }
                    }

                    true
                }
                else -> false
            }
        }
    }

    private fun emailSecretaryOffice() {
        val i = Intent(Intent.ACTION_SEND)
            .setType("message/rfc822")
            .putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("kalarhaki@hmu.gr"))

        try {
            startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this,"There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun callSecretaryOffice() {
        startActivity(Intent(Intent.ACTION_DIAL).also {
            it.data = Uri.parse("tel:2841091103")
        })
    }

    private fun hideKeyboard(view : View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun stopOps() {}
}