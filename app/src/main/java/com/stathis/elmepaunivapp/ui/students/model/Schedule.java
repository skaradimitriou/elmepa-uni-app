package com.stathis.elmepaunivapp.ui.students.model;

public class Schedule extends Object {

    String mainTxt;
    String buttonTxt;

    public Schedule(String mainTxt, String buttonTxt) {
        this.mainTxt = mainTxt;
        this.buttonTxt = buttonTxt;
    }

    public String getMainTxt() {
        return mainTxt;
    }

    public void setMainTxt(String mainTxt) {
        this.mainTxt = mainTxt;
    }

    public String getButtonTxt() {
        return buttonTxt;
    }

    public void setButtonTxt(String buttonTxt) {
        this.buttonTxt = buttonTxt;
    }
}
