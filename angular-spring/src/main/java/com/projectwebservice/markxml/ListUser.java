package com.projectwebservice.markxml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "", propOrder = {
})

@XmlRootElement(name = "list_user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListUser {

    @XmlElement(name = "user")
    private List<UserMark> userMarkList;

    public List<UserMark> getUserMarkList() {
        return userMarkList;
    }

    public void setUserMarkList(List<UserMark> userMarkList) {
        this.userMarkList = userMarkList;
    }

    @Override
    public String toString() {
        return "ListUser{" +
                "userMarkList=" + userMarkList +
                '}';
    }
}
