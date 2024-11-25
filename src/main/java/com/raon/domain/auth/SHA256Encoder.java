package com.raon.domain.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Slf4j
@Component
public class SHA256Encoder implements PasswordEncoder {
    private static final String ENCRYPTION_KEY = "SHA-256";

    @Override
    public String encode(String password) {
        String SHA = null;

        MessageDigest sh;
        try {
            sh = MessageDigest.getInstance(ENCRYPTION_KEY);
            sh.update(password.getBytes());
            byte[] byteDate = sh.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : byteDate) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        } catch (Exception e) {
            log.error("encrypt error: {}", e.getMessage());
            sh = null;
        }

        return SHA;
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return encode(password).equals(encodedPassword);
    }
}
