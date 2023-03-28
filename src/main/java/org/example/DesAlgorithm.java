package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
public class DesAlgorithm
{
    private static final String ALGORITHM = "DES";
    public static String encrypt(String plaintext, String key, String mode) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/" + mode + "/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        if (mode.equals("ECB")) {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        } else {
            // Generate a random initialization vector (IV) for CBC and CFB modes
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        }

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, String key, String mode) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/" + mode + "/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        if (mode.equals("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
        } else {
            // Get the initialization vector (IV) from the ciphertext for CBC and CFB modes
            byte[] iv = Base64.getDecoder().decode(ciphertext.substring(0, cipher.getBlockSize()));
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            ciphertext = ciphertext.substring(cipher.getBlockSize());
        }

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {

        GetInput myString = new GetInput();


        System.out.println("Text for encryption \n");
        String text = myString.returnMyString();

        System.out.println("ECB(8 symbols), CBC(8), CFB \n");
        String mode = myString.returnMyString();

        System.out.println("Key string \n");
        String key = myString.returnMyString();



        String encryptedText = encrypt(text, key, mode);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key, mode);
        System.out.println("Decrypted text: " + decryptedText);

        }
    }
