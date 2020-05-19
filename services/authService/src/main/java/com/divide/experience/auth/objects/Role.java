package com.divide.experience.auth.objects;

/**
 * Created by AOleynikov on 21.05.2019.
 */
public enum Role {

    ANON("ANON"),
    USER("USER"),
    AUTHOR("AUTHOR"),
    MODERATOR("MODERATOR"),
    ADMINISTRATOR("ADMINISTRATOR");

    String codeName;

    Role(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeName() {
        return codeName;
    }
}
