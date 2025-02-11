package com.example.app.model;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Entity
@Table(name = "app_user")
public class User {

    public User() {

    }

    public User(String username, String password) {
        this.id = generateUUID(username, password);
        this.username = username;
        this.hashedPassword = hashPassword(password);
    }

    @Id
    private String id;
    private String username;
    private String hashedPassword;
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }


    private String generateUUID(String username, String password) {
        try {
            long timestamp = System.currentTimeMillis();
            String input = username + password + timestamp;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // convert hash to UUID format
            long mostSigBits = 0;
            long leastSigBits = 0;
            for (int i = 0; i < 8; i++) {
                mostSigBits = (mostSigBits << 8) | (hash[i] & 0xff);
            }
            for (int i = 8; i < 16; i++) {
                leastSigBits = (leastSigBits << 8) | (hash[i] & 0xff);
            }
            return new UUID(mostSigBits, leastSigBits).toString();
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating UUID", e);
        }
    }
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.hashedPassword);
    }
}
