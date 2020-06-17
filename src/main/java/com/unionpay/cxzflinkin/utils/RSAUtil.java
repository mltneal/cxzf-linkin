package com.unionpay.cxzflinkin.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class RSAUtil {
    public RSAUtil() {
    }

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//            int KEY_SIZE = true;
            keyPairGen.initialize(1024, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static void writeKeyPair(KeyPair keyPair, String fileName) {
        try {
            ObjectOutputStream keyFile = new ObjectOutputStream(new FileOutputStream(fileName));
            keyFile.writeObject(keyPair);
            keyFile.close();
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static KeyPair readKeyPair(String fileName) {
        try {
            ObjectInputStream keyFile = new ObjectInputStream(new FileInputStream(fileName));
            KeyPair keyPair = (KeyPair)keyFile.readObject();
            keyFile.close();
            return keyPair;
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static void writePublicKey(PublicKey key, String fileName) {
        writeKey(key, fileName);
    }

    public static PublicKey readPublicKey(String fileName) {
        try {
            ObjectInputStream keyFile = new ObjectInputStream(new FileInputStream(fileName));
            PublicKey key = (PublicKey)keyFile.readObject();
            keyFile.close();
            return key;
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static void writePrivateKey(PrivateKey key, String fileName) {
        writeKey(key, fileName);
    }

    private static void writeKey(Key key, String fileName) {
        try {
            ObjectOutputStream keyFile = new ObjectOutputStream(new FileOutputStream(fileName));
            keyFile.writeObject(key);
            keyFile.close();
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static PrivateKey readPrivateKey(String fileName) {
        try {
            ObjectInputStream keyFile = new ObjectInputStream(new FileInputStream(fileName));
            PrivateKey key = (PrivateKey)keyFile.readObject();
            keyFile.close();
            return key;
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        KeyFactory keyFac = null;

        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));

        try {
            return (RSAPublicKey)keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException var5) {
            throw new RuntimeException(var5.getMessage());
        }
    }

    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        KeyFactory keyFac = null;

        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));

        try {
            return (RSAPrivateKey)keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException var5) {
            throw new RuntimeException(var5.getMessage());
        }
    }

    public static byte[] encrypt(PublicKey pk, byte[] data) {
        try {
            BigInteger b = new BigInteger(data);
            BigInteger e1 = ((RSAPublicKey)pk).getPublicExponent();
            BigInteger m = ((RSAPublicKey)pk).getModulus();
            BigInteger a = b.modPow(e1, m);
            return a.toByteArray();
        } catch (Exception var6) {
            throw new RuntimeException(var6.getMessage());
        }
    }

    public static byte[] decrypt(PrivateKey pk, byte[] raw) {
        try {
            BigInteger a = new BigInteger(raw);
            BigInteger e2 = ((RSAPrivateKey)pk).getPrivateExponent();
            BigInteger m = ((RSAPrivateKey)pk).getModulus();
            BigInteger b = a.modPow(e2, m);
            return b.toByteArray();
        } catch (Exception var6) {
            throw new RuntimeException(var6.getMessage());
        }
    }

    public static RSAPublicKey generateRSAPublicKeyFromHex(String modulus, String publicExponent) {
        KeyFactory keyFac = null;

        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus, 16), new BigInteger(publicExponent, 16));

        try {
            return (RSAPublicKey)keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException var5) {
            throw new RuntimeException(var5.getMessage());
        }
    }

    public static RSAPrivateKey generateRSAPrivateKeyFromHex(String modulus, String privateExponent) {
        KeyFactory keyFac = null;

        try {
            keyFac = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus, 16), new BigInteger(privateExponent, 16));

        try {
            return (RSAPrivateKey)keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException var5) {
            throw new RuntimeException(var5.getMessage());
        }
    }

    public static String encryptToHex(PublicKey pk, String data) {
        return (new BigInteger(encrypt(pk, data.getBytes()))).toString(16);
    }

    public static String decryptFromHex(PrivateKey pk, String data) {
        return new String(decrypt(pk, (new BigInteger(data, 16)).toByteArray()));
    }
}
