package com.divide.experience.auth.utils;

/**
 * Created by AOleynikov on 28.05.2019.
 */
public interface PasswordHashUtil {
    byte[] encodePassword(String password, byte[] salt);
    byte[] getSalt();
 }
