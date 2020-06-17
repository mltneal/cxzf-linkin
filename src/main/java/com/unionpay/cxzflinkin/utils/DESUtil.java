
package com.unionpay.cxzflinkin.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.commons.codec.binary.Base64;

public class DESUtil {
    private static final String DES = "DES";

    public DESUtil() {
    }

    public static Key generateKey() {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom());
            Key key = _generator.generateKey();
            return key;
        } catch (Exception var2) {
            throw new RuntimeException(var2.getMessage());
        }
    }

    public static Key generateKey(byte[] value) {
        SecretKeyFactory keyFac = null;

        try {
            keyFac = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(value);
            return keyFac.generateSecret(keySpec);
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static void writeKey(Key key, String fileName) {
        try {
            ObjectOutputStream keyFile = new ObjectOutputStream(new FileOutputStream(fileName));
            keyFile.writeObject(key);
            keyFile.close();
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static Key readKey(String fileName) {
        try {
            ObjectInputStream keyFile = new ObjectInputStream(new FileInputStream(fileName));
            Key key = (Key)keyFile.readObject();
            keyFile.close();
            return key;
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static byte[] encrypt(Key key, byte[] data) {
        Object var2 = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key);
            byte[] raw = cipher.doFinal(data);
            raw = Base64.encodeBase64URLSafe(raw);
            return raw;
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    public static byte[] decrypt(Key key, byte[] raw) {
        Object var2 = null;

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);
            raw = Base64.decodeBase64(raw);
            byte[] data = cipher.doFinal(raw);
            return data;
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    public static Key generateKeyFromHex(String value) {
        SecretKeyFactory keyFac = null;

        try {
            keyFac = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec((new BigInteger(value, 16)).toByteArray());
            return keyFac.generateSecret(keySpec);
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static String encryptToHex(Key key, String data) {
        byte[] byRaw = encrypt(key, data.getBytes());
        return (new BigInteger(byRaw)).toString(16);
    }

    public static String decryptFromHex(Key key, String data) {
        byte[] byRaw = (new BigInteger(data, 16)).toByteArray();
        return new String(decrypt(key, byRaw));
    }
}
