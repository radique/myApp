package model;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class EncryptModel {


    static Cipher getCipher(int mode, String key) {
        try {
            Random random = new Random(101);
            byte[] salt = new byte[8];
            random.nextBytes(salt);

            PBEParameterSpec pspecs = new PBEParameterSpec(salt, 5);
            SecretKey pbeKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(key.toCharArray()));
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(mode, pbeKey, pspecs);
            return cipher;
        } catch (InvalidKeySpecException | InvalidKeyException | InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    static byte[] Encryption(String password) {
        try {
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, "register-key");
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return encrypted;
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String Decryption(byte[] password) {
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE, "register-key");
            byte[] decrypted = cipher.doFinal(password);
            return new String(decrypted);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static boolean CheckHash(String passWithNoEnc, byte[] passWithEnc) {
        if (passWithNoEnc.equals(Decryption(passWithEnc))) {
            return true;
        }
        return false;
    }


}
