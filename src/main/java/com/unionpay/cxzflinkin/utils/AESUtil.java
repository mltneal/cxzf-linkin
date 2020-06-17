

package com.unionpay.cxzflinkin.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

public class AESUtil {
    private static final Logger log = LoggerFactory.getLogger(AESUtil.class);

    public AESUtil() {
    }

    public static byte[] encrypt(String content, String password, int bits) {
        try {
            if (bits % 8 != 0) {
                throw new IllegalArgumentException("bits must be a multiple of 8");
            }

            byte[] newkey = new byte[bits / 8];

            for(int i = 0; i < newkey.length && i < password.getBytes().length; ++i) {
                newkey[i] = password.getBytes()[i];
            }

            SecretKeySpec key = new SecretKeySpec(newkey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException var8) {
            log.warn("", var8);
        } catch (NoSuchPaddingException var9) {
            log.warn("", var9);
        } catch (InvalidKeyException var10) {
            log.warn("", var10);
        } catch (UnsupportedEncodingException var11) {
            log.warn("", var11);
        } catch (IllegalBlockSizeException var12) {
            log.warn("", var12);
        } catch (BadPaddingException var13) {
            log.warn("", var13);
        }

        return null;
    }

    public static byte[] encrypt(String content, String password) {
        return encrypt(content, password, 128);
    }

    public static String encrypt256ToString(String content, String password) {
        return Base64Utils.encodeToString(encrypt(content, password, 256));
    }

    public static String encryp256tToHex(String content, String password) {
        return parseByte2HexStr(encrypt(content, password, 256));
    }

    public static String encryptToString(String content, String password) {
        return Base64Utils.encodeToString(encrypt(content, password, 128));
    }

    public static String encryptToHex(String content, String password) {
        return parseByte2HexStr(encrypt(content, password, 128));
    }

    public static String decrypt(byte[] content, String password, int bits) {
        try {
            if (bits % 8 != 0) {
                throw new IllegalArgumentException("bits must be a multiple of 8");
            }

            byte[] newkey = new byte[bits / 8];

            for(int i = 0; i < newkey.length && i < password.getBytes().length; ++i) {
                newkey[i] = password.getBytes()[i];
            }

            SecretKeySpec key = new SecretKeySpec(newkey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, key);
            byte[] result = cipher.doFinal(content);
            return new String(result);
        } catch (NoSuchAlgorithmException var7) {
            log.warn("", var7);
        } catch (NoSuchPaddingException var8) {
            log.warn("", var8);
        } catch (InvalidKeyException var9) {
            log.warn("", var9);
        } catch (IllegalBlockSizeException var10) {
            log.warn("", var10);
        } catch (BadPaddingException var11) {
            log.warn("", var11);
        }

        return null;
    }

    public static String decrypt(byte[] content, String password) {
        return decrypt(content, password, 128);
    }

    public static String decrypt256FromString(String content, String password) {
        return decrypt(Base64Utils.decodeFromString(content), password, 256);
    }

    public static String decrypt256FromHex(String hexContent, String password) {
        return decrypt(parseHexStr2Byte(hexContent), password, 256);
    }

    public static String decryptFromString(String content, String password) {
        return decrypt(Base64Utils.decodeFromString(content), password, 128);
    }

    public static String decryptFromHex(String hexContent, String password) {
        return decrypt(parseHexStr2Byte(hexContent), password, 128);
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }
}
