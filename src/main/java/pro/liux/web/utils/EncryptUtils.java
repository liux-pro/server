package pro.liux.web.utils;

import org.bouncycastle.util.Strings;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;


public class EncryptUtils {
    public static byte[] sha256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(Strings.toUTF8ByteArray(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha256(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha1(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            return digest.digest(Strings.toUTF8ByteArray(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] sha1(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            return digest.digest(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String hex(byte[] data) {
        StringBuilder result = new StringBuilder(data.length * 2);
        for (byte b : data) {
            result.append(String.format("%02x", b & 0xff));
        }
        return result.toString();
    }

    public static byte[] hmacSHA256(String data, byte[] key) {
        try {
            String algorithm = "HmacSHA256";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(Strings.toUTF8ByteArray(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hmacSHA1(String data, byte[] key) {
        try {
            String algorithm = "HmacSHA1";
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key, algorithm));
            return mac.doFinal(Strings.toUTF8ByteArray(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
