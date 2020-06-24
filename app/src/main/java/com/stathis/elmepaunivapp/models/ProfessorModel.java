package com.stathis.elmepaunivapp.models;

public class ProfessorModel {

    String fullName;
    String email;
    String gender;

    public ProfessorModel(String fullName, String email, String gender) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
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

    @Override
    public String toString() {
        return "ProfessorModel{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
