package com.stathis.elmepaunivapp.ui.chatbot;

import android.os.Handler;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.listeners.activity_listeners.ChatBotListener;
import com.stathis.elmepaunivapp.listeners.ItemClickListener;
import com.stathis.elmepaunivapp.ui.chatbot.model.ChatbotHeader;
import com.stathis.elmepaunivapp.ui.chatbot.model.Question;
import com.stathis.elmepaunivapp.ui.chatbot.recyclerview.ChatBotAdapter;
import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;

import java.util.ArrayList;


public class ChatbotViewModel extends ViewModel implements ItemClickListener {

    String answer;
    ChatBotAdapter chatBotAdapter = new ChatBotAdapter(this);
    ChatBotListener chatBotListener;
    private ArrayList<Object> messagesList = new ArrayList<>();

    void setUpListener(ChatBotListener chatBotListener) {
        this.chatBotListener = chatBotListener;
    }

    void initAdapter(){
        messagesList.add(new ChatbotHeader());
        chatBotAdapter.submitList(messagesList);
    }

    void getResponse(final String response) {
        messagesList.add(new Question(response));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                messagesList.add(new Answer(getChatAnswer(response)));
                chatBotAdapter.notifyDataSetChanged();
            }
        }, 1200);
    }

    String getChatAnswer(String response) {
        if (response.contains("πρόγραμμα σπουδών") || response.contains("προγραμμα σπουδών") || response.contains("πρόγραμμα σπουδων") || response.contains("προγραμμα σπουδων") || response.contains("σπουδων")) {
            answer = "Κάνε tap για να δείς το πρόγραμμα σπουδών!";
        } else if (response.contains("ωρολογιο προγραμμα") || response.contains("ωρολόγιο πρόγραμμα") || response.contains("ωρολόγιο") || response.contains("ωρολογιο")) {
            answer = "Κάνε tap για να δείς το πρόγραμμα των μαθημάτων!";
        } else if (response.contains("τηλέφωνο") || response.contains("τηλεφωνο") || response.contains("γραμματεία") || response.contains("γραμματεια")) {
            answer = "Κάνε tap για να καλέσω την Γραμματεία του Τμήματος!";
        } else if (response.contains("email") || response.contains("e-mail")) {
            answer = "Κάνε tap για να στείλεις e-mail στην Γραμματεία!";
        } else if (response.contains("καθηγητ") || response.contains("καθηγητές") || response.contains("προσωπικό")) {
            answer = "Κάνε tap για να αναζητήσεις έναν καθηγητή του Τμήματος!";
        } else if (response.contains("ανακοινώσεις") || response.contains("ανακοιν") || response.contains("ανακοινωση") || response.contains("ανακοίνωση")) {
            answer = "Κάνε tap για να δεις τις ανακοινώσεις του Τμήματος!";
        } else if (response.contains("εικονική περιήγηση") || response.contains("περιήγηση") || response.contains("περιηγηση") || response.contains("εικονικη")) {
            answer = "Κάνε tap για να δείς την εικονική περιήγηση!";
        } else if (response.contains("γεια") || (response.contains("γειά"))) {
            answer = "Γεία σου και εσένα!";
        } else if (response.contains("ευχαριστώ") || response.contains("ευχαριστω")) {
            answer = "Παρακαλώ!";
        } else {
            answer = "Δεν γνωρίζω την απάντηση ακόμα";
        }

        return answer;
    }

    void whichIntent(Answer answer) {
        switch (answer.getText()) {
            case "Δεν γνωρίζω την απάντηση ακόμα": {
                chatBotListener.doNothing(answer);
                break;
            }

            case "Κάνε tap για να δεις τις ανακοινώσεις του Τμήματος!": {
                chatBotListener.openAnnouncements(answer);
                break;
            }

            case "Κάνε tap για να δείς το πρόγραμμα σπουδών!": {
                chatBotListener.goToSyllabus(answer);
                break;
            }
            case "Κάνε tap για να δείς το πρόγραμμα των μαθημάτων!": {
                chatBotListener.openSchedule(answer);
                break;
            }

            case "Κάνε tap για να καλέσω την Γραμματεία του Τμήματος!": {
                chatBotListener.callSecretary(answer);
                break;
            }

            case "Κάνε tap για να στείλεις e-mail στην Γραμματεία!": {
                chatBotListener.emailToSecretary(answer);
                break;
            }

            case "Κάνε tap για να δείς την εικονική περιήγηση!": {
                chatBotListener.virtualTour(answer);
                break;
            }
        }
    }

    @Override
    public void onAnswerClick(Answer answer) {
        whichIntent(answer);
    }

}
