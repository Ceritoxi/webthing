package happy.util;

import happy.domain.user.Credentials;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.UUID;

public class SecurityUtil {

    public static Credentials generateHashWithSalsa(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Credentials credentials = new Credentials();
            credentials.setHash(hash);
            credentials.setSalt(salt);
            return credentials;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new IllegalStateException("Password hashing was unsuccessful!");
        }
    }

    public static boolean isSame(String password, byte[] hashTo, byte[] saltTo) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltTo, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(hash, hashTo);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new IllegalStateException("Password hashing was unsuccessful!");
        }
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
