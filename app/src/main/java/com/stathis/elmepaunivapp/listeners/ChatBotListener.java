package com.stathis.elmepaunivapp.listeners;

import com.stathis.elmepaunivapp.ui.chatbot.model.Answer;

public interface ChatBotListener {

    void goToSyllabus(Answer answer);
    void doNothing(Answer answer);
    void openSchedule(Answer answer);
    void callSecretary(Answer answer);
    void emailToSecretary(Answer answer);
    void virtualTour(Answer answer);
    void openAnnouncements(Answer answer);
}
