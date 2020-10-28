package com.stathis.elmepaunivapp.ui.students.model;

import java.util.List;

public class StudentItem extends Object {

    String headerTxt;
    List<UsefulLinks> list;

    public StudentItem(String headerTxt, List<UsefulLinks> list) {
        this.headerTxt = headerTxt;
        this.list = list;
    }

    public String getHeaderTxt() {
        return headerTxt;
    }

    public void setHeaderTxt(String headerTxt) {
        this.headerTxt = headerTxt;
    }

    public List<UsefulLinks> getList() {
        return list;
    }

    public void setList(List<UsefulLinks> list) {
        this.list = list;
    }
}
