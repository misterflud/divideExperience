package com.divide.experience.auth.utils.impl;

import com.divide.experience.auth.utils.PasswordHashUtil;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Created by AOleynikov on 28.05.2019.
 */
@Component
public class PasswordHashUtilImpl implements PasswordHashUtil {

    @Override
    public byte[] encodePassword(String password, byte[] salt) {
        byte[] hash = null;
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            //TODO:add to log, and throw passwordException
        }
        return hash;
    }

    @Override
    public byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
