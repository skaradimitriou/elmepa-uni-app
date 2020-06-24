package com.stathis.elmepaunivapp.models;

public class ProfessorModel {

    private String fullName;
    private String email;
    private String gender;
    private String vocative;

    public ProfessorModel(String fullName, String email, String gender, String vocative) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.vocative = vocative; //vocative case of a professor
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVocative() {
        return vocative;
    }

    public void setVocative(String vocative) {
        this.vocative = vocative;
    }

    @Override
    public String toString() {
        return "ProfessorModel{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", vocative='" + vocative + '\'' +
                '}';
    }
}
