package com.stathis.elmepaunivapp.ui.chatbot

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.stathis.elmepaunivapp.R
import com.stathis.elmepaunivapp.abstraction.ElmepaBindingActivity
import com.stathis.elmepaunivapp.callbacks.ChatbotClickListener
import com.stathis.elmepaunivapp.databinding.ActivityChatBotBinding
import com.stathis.elmepaunivapp.ui.announcements.AnnouncementsActivity
import com.stathis.elmepaunivapp.ui.syllabus.SyllabusActivity
import com.stathis.elmepaunivapp.ui.webview.WebviewActivity
import com.stathis.elmepaunivapp.util.SCHEDULE_URL
import com.stathis.elmepaunivapp.util.SECRETARY_MAIL
import com.stathis.elmepaunivapp.util.SECRETARY_TEL
import com.stathis.elmepaunivapp.util.VIRTUAL_TOUR_URL
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatbotActivity : ElmepaBindingActivity<ActivityChatBotBinding>(R.layout.activity_chat_bot) {

    private lateinit var viewModel : ChatbotViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ChatbotViewModel::class.java)
    }

    override fun startOps() {
        binding.userMessagesRecView.adapter = viewModel.adapter

        viewModel.bindCallbacks(object : ChatbotClickListener {
            override fun openSchedule()  = openUrl(SCHEDULE_URL)
            override fun callSecretary() = callSecretaryOffice()
            override fun emailToSecretary() = emailSecretaryOffice()
            override fun virtualTour() = openUrl(VIRTUAL_TOUR_URL)
            override fun goToSyllabus() {
                startActivity(Intent(this@ChatbotActivity, SyllabusActivity::class.java))
            }

            override fun openAnnouncements() {
                startActivity(Intent(this@ChatbotActivity, AnnouncementsActivity::class.java))
            }

            override fun chatbotReplied() {
                /* this line of code scrolls to the last item of the list that is passed to the adapter
                 * it creates an illusion of the chat interactiveness */

                binding.userMessagesRecView.smoothScrollToPosition(user_messagesRecView.bottom)
                viewModel.adapter.notifyDataSetChanged()
            }
        })

        binding.askQuestions.setOnEditorActionListener { view, actionId, event ->
            when (actionId == EditorInfo.IME_ACTION_DONE) {
                true -> {
                    val response = binding.askQuestions.text.toString().lowercase()
                    binding.askQuestions.text?.clear()
                    hideKeyboard(view)

                    viewModel.getResponse(response)

                    // this line of code scrolls to the last item of the list that is passed to the adapter
                    // it creates an illusion of the chat interactiveness
                    when (viewModel.adapter.currentList.size > 1) {
                        true -> {
                            binding.userMessagesRecView.smoothScrollToPosition(user_messagesRecView.bottom)
                            viewModel.adapter.notifyDataSetChanged()
                        }
                    }

                    true
                }
                else -> false
            }
        }
    }

    private fun openUrl(url : String){
        startActivity(Intent(this@ChatbotActivity,WebviewActivity::class.java)
            .putExtra(resources.getString(R.string.url_tag), url))
    }

    private fun emailSecretaryOffice() {
        val i = Intent(Intent.ACTION_SEND).also{
            it.type = resources.getString(R.string.email_type)
            it.putExtra(Intent.EXTRA_EMAIL, arrayOf(SECRETARY_MAIL))
        }

        try {
            startActivity(Intent.createChooser(i, resources.getString(R.string.sending_email)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this,resources.getString(R.string.no_clients_installed), Toast.LENGTH_SHORT).show()
        }
    }

    private fun callSecretaryOffice() {
        startActivity(Intent(Intent.ACTION_DIAL).also {
            it.data = Uri.parse(SECRETARY_TEL)
        })
    }

    private fun hideKeyboard(view : View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun stopOps() {}
}