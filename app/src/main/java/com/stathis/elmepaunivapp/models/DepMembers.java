package com.stathis.elmepaunivapp.models;

public class DepMembers {

    private String name;
    private String title;
    private String description;
    private String linkedin;
    private String researchGate;
    private String googleScholar;
    private String skill_one;
    private String skill_two;
    private String skill_three;
    private Integer img;

    public DepMembers(String name, String title, String description, String linkedin, String researchGate, String googleScholar, String skill_one, String skill_two, String skill_three, Integer img) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.linkedin = linkedin;
        this.researchGate = researchGate;
        this.googleScholar = googleScholar;
        this.skill_one = skill_one;
        this.skill_two = skill_two;
        this.skill_three = skill_three;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getResearchGate() {
        return researchGate;
    }

    public void setResearchGate(String researchGate) {
        this.researchGate = researchGate;
    }

    public String getGoogleScholar() {
        return googleScholar;
    }

    public void setGoogleScholar(String googleScholar) {
        this.googleScholar = googleScholar;
    }

    public String getSkill_one() {
        return skill_one;
    }

    public void setSkill_one(String skill_one) {
        this.skill_one = skill_one;
    }

    public String getSkill_two() {
        return skill_two;
    }

    public void setSkill_two(String skill_two) {
        this.skill_two = skill_two;
    }

    public String getSkill_three() {
        return skill_three;
    }

    public void setSkill_three(String skill_three) {
        this.skill_three = skill_three;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "DepMembers{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", researchGate='" + researchGate + '\'' +
                ", googleScholar='" + googleScholar + '\'' +
                ", skill_one='" + skill_one + '\'' +
                ", skill_two='" + skill_two + '\'' +
                ", skill_three='" + skill_three + '\'' +
                ", img=" + img +
                '}';
    }
}
