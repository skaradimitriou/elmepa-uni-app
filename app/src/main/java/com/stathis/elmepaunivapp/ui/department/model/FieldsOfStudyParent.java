package com.stathis.elmepaunivapp.ui.department.model;

import com.stathis.elmepaunivapp.ui.students.model.UsefulLinks;

import java.util.List;

public class FieldsOfStudyParent {

    String headerTxt;
    List<DeptFieldsOfStudy> list;

    public FieldsOfStudyParent(String headerTxt, List<DeptFieldsOfStudy> list) {
        this.headerTxt = headerTxt;
        this.list = list;
    }

    public String getHeaderTxt() {
        return headerTxt;
    }

    public void setHeaderTxt(String headerTxt) {
        this.headerTxt = headerTxt;
    }

    public List<DeptFieldsOfStudy> getList() {
        return list;
    }

    public void setList(List<DeptFieldsOfStudy> list) {
        this.list = list;
    }
}
