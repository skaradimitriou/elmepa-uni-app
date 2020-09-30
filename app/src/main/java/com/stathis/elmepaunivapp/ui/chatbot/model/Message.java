package com.stathis.elmepaunivapp.ui.chatbot.model;

import com.stathis.elmepaunivapp.abstraction.LocalModel;

public class Message implements LocalModel {

    String question;
    String answer;

    public Message(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Message{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
