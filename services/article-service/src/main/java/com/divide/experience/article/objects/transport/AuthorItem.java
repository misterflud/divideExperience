package com.divide.experience.article.objects.transport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@ApiModel(description = "This is a DTO for the author and other users. Sometimes, some fields can be null.")
public class AuthorItem implements Serializable {

    private static final long serialVersionUID = 2108155263999767301L;

    @ApiModelProperty("Email")
    private String email;

    @ApiModelProperty("Nick name")
    private String nickName;

    @ApiModelProperty("First name")
    private String firstName;

    @ApiModelProperty("Second name")
    private String secondName;

    @ApiModelProperty("Third name")
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
