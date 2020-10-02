package com.stathis.elmepaunivapp.ui.chatbot;

import androidx.lifecycle.ViewModel;

import com.stathis.elmepaunivapp.listeners.ChatBotListener;
import com.stathis.elmepaunivapp.recyclerviews.ChatBotAdapter;

public class ChatbotViewModel extends ViewModel {

    String  answer;
    ChatBotAdapter chatBotAdapter;
    ChatBotListener chatBotListener;

    String getChatbotAnswer(String response){
        switch (response) {
            case "γεια":
                answer = "Γεία σου και εσένα!";
                break;
            case "γειά":
                answer = "Γεία σου και εσένα!";
                break;
            case "ευχαριστώ":
                answer = "Παρακαλώ!";
                break;
            case "ευχαριστω":
                answer = "Παρακαλώ!";
                break;
            case "προγραμμα σπουδων":
                answer = "Κάνε tap για να δείς \n το πρόγραμμα σπουδών!";
                break;
            case "πρόγραμμα σπουδών":
                answer = "Κάνε tap για να δείς \n το πρόγραμμα σπουδών!";
                break;
            case "ωρολογιο προγραμμα":
                answer = "Κάνε tap για να δείς το \n πρόγραμμα των μαθημάτων!";
                break;
            case "ωρολόγιο πρόγραμμα":
                answer = "Κάνε tap για να δείς το \n πρόγραμμα των μαθημάτων!";
                break;
            case "τηλέφωνο γραμματείας":
                answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                break;
            case "τηλεφωνο γραμματειας":
                answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                break;
            case "τηλεφωνο γραμματειασ":
                answer = "Κάνε tap για να καλέσω \n την Γραμματεία του Τμήματος!";
                break;
            case "email γραμματειας":
                answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                break;
            case "email γραμματείας":
                answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                break;
            case "email γραμματειασ":
                answer = "Κάνε tap για να στείλεις \n e-mail στην Γραμματεία!";
                break;
            case "καθηγητές":
                answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                break;
            case "προσωπικό":
                answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                break;
            case "καθηγητες":
                answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                break;
            case "προσωπικο":
                answer = "Κάνε tap για να αναζητήσεις \n έναν καθηγητή του Τμήματος!";
                break;
            case "εικονική περιήγηση":
                answer = "Κάνε tap για να δείς \n την εικονική περιήγηση!";
                break;
            case "εικονικη περιηγηση":
                answer = "Κάνε tap για να δείς \n την εικονική περιήγηση!";
                break;
            case "ανακοινώσεις":
                answer = "Κάνε tap για να δεις \n τις τελευταίες ανακοινώσεις!";
                break;
            case "ανακοινωσεις":
                answer = "Κάνε tap για να δεις \n τις τελευταίες ανακοινώσεις!";
                break;
            default:
                answer = "Δεν γνωρίζω την απάντηση ακόμα";
                break;
        }
        return answer;
    }

    String getIntent(String answer){
        switch(answer){
            case "Κάνε tap για να δεις \\n τις τελευταίες ανακοινώσεις!":
                return "ANNOUNCEMENTS";
            case "Κάνε tap για να δείς \n την εικονική περιήγηση!" :
                return "VIRTUAL_TOUR";
        }
        return answer;
    }

}
