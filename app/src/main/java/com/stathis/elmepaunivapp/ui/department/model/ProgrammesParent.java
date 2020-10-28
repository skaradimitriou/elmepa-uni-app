package com.stathis.elmepaunivapp.ui.department.model;

import java.util.List;

public class ProgrammesParent {

    String header;
    List<Programmes> list;

    public ProgrammesParent(String header, List<Programmes> list) {
        this.header = header;
        this.list = list;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Programmes> getList() {
        return list;
    }

    public void setList(List<Programmes> list) {
        this.list = list;
    }
}
