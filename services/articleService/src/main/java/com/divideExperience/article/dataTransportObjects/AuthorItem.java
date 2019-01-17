package com.divideExperience.article.dataTransportObjects;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public class AuthorItem implements Serializable {

    private String email;

    private String nickName;

    private String firstName;

    private String secondName;

    private String thirdName;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
