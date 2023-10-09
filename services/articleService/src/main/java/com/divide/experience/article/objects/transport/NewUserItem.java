package com.divide.experience.article.objects.transport;

import java.util.Date;

/**
 * Created by AOleynikov on 27.05.2019.
 */
public class NewUserItem extends UserItem {

    private Date dateOfRegistration;

    private String password;

    private String role;

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
