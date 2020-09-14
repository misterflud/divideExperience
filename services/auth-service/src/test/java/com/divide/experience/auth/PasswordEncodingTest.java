package com.divide.experience.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@SpringBootTest
public class PasswordEncodingTest {

    private static final String oldEncodeString = "$2a$10$MdzgMgpYti9a4PhnKbO8yOlgHMujk1NEFB5VvdEAxIgmgUySbP5ei";
    private static final String password = "qwerty";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Test of new password encoding, decoding")
    void test1() {
        String result = passwordEncoder.encode(password);
        assertTrue(passwordEncoder.matches(password, result));
    }

    @Test
    @DisplayName("Test of old password encoding, decoding")
    void test2() {
        assertTrue(passwordEncoder.matches(password, oldEncodeString));
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
