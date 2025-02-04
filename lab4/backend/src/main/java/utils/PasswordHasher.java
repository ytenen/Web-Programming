package utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password) {
        int cost = 12;
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray());
    }
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.getBytes(), hashedPassword.getBytes()).verified;
    }
}
