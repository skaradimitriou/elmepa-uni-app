package com.stathis.elmepaunivapp.ui.department.model;

import java.util.List;

public class SocialChannelsParent {

    String header;
    List<SocialChannels> list;

    public SocialChannelsParent(String header, List<SocialChannels> list) {
        this.header = header;
        this.list = list;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<SocialChannels> getList() {
        return list;
    }

    public void setList(List<SocialChannels> list) {
        this.list = list;
    }
}
