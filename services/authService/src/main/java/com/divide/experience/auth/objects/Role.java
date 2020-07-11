package com.divide.experience.auth.objects;

/**
 * Created by AOleynikov on 21.05.2019.
 */
public enum Role {

    ANON("ANON", 0),
    USER("USER", 1),
    AUTHOR("AUTHOR", 2),
    MODERATOR("MODERATOR", 3),
    ADMINISTRATOR("ADMINISTRATOR", 4);

    private final String name;
    private final int code;

    Role(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
