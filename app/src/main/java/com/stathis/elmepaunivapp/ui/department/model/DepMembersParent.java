package com.stathis.elmepaunivapp.ui.department.model;

import java.util.List;

public class DepMembersParent {

    String header;
    List<DepMembers> list;

    public DepMembersParent(String header, List<DepMembers> list) {
        this.header = header;
        this.list = list;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<DepMembers> getList() {
        return list;
    }

    public void setList(List<DepMembers> list) {
        this.list = list;
    }
}
