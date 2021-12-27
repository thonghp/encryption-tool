package edu.nlu.exercise.algorithm;

import edu.nlu.exercise.utils.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Security;
import java.util.Base64;

public class Symmetric {
    public static Base64.Encoder encoder = Base64.getEncoder();
    public static Base64.Decoder decoder = Base64.getDecoder();
    public Cipher cipher;

    public void generateKey(String algorithm, int size) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(size); // độ dài key
        SecretKey secretKey = keyGenerator.generateKey();

        File file = new File("E:\\Encryption Key");

        if (!file.exists()) {
            file.mkdir();
        }

        try (FileOutputStream fos = new FileOutputStream(file + "/symmetric.key")) {
            fos.write(secretKey.getEncoded());
        }
    }

    public SecretKey readKey(String keyPath, String algorithmName) throws IOException {
        File file = new File(keyPath);
        byte[] keyBytes = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(keyBytes);
        }

        return new SecretKeySpec(keyBytes, algorithmName);
    }

    // ctr/cts/gcm(chỉ hỗ trợ cho aes) chỉ chơi dc vs nopadding khi chưa thêm provider bên ngoài
    public String encryptText(String original, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey key = readKey(keyPath, algorithmName);

        byte[] cipherText;

        try {
            cipher = Cipher.getInstance(inputAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(original.getBytes());
        } catch (Exception e) {
            if (inputAlgorithm.contains("CTS")) {
                // cts + nopadding có length < bội số mặc định của khối
                cipher = Cipher.getInstance(algorithmName + "/CBC/PKCS5Padding");
            } else {
                // ecb/cbc/pcbc + nopadding có length khác bội số của khối
                if (!inputAlgorithm.equals(algorithmName)&&!inputAlgorithm.equals("ARCFOUR/ECB/NoPadding")) {
                    String[] split = inputAlgorithm.split("/");
                    String padding = split[1];
                    cipher = Cipher.getInstance(algorithmName + "/" + padding + "/PKCS5Padding");
                }
            }
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(original.getBytes());
        }

        return encoder.encodeToString(cipherText);
    }

    public String decryptText(String encrypt, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey key = readKey(keyPath, algorithmName);

        if (inputAlgorithm.equals(algorithmName) || inputAlgorithm.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else if (inputAlgorithm.equals("AES/GCM/NoPadding")) {
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, cipher.getIV()));
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cipher.getIV()));
        }

        byte[] decoted = decoder.decode(encrypt);
        byte[] plainText = cipher.doFinal(decoted);

        return new String(plainText);
    }

    public String encryptFile(String fileName, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey key = readKey(keyPath, algorithmName);

        cipher = Cipher.getInstance(inputAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return FileUtils.fileHandling(fileName, "E:\\Encryption Key\\", "_encrypt", cipher);
    }

    public String decryptFile(String fileEncrypt, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey key = readKey(keyPath, algorithmName);

        if (inputAlgorithm.equals(algorithmName) || inputAlgorithm.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else if (inputAlgorithm.equals("AES/GCM/NoPadding")) {
            cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(128, cipher.getIV()));
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cipher.getIV()));
        }

        // viết thêm để xử lý đọc 1 lần
        return FileUtils.fileHandling(fileEncrypt, "E:\\Encryption Key\\", "_decrypt", cipher);
    }

    public static void main(String[] args) throws Exception {
        Symmetric ins = new Symmetric();
        ins.generateKey("ARCFOUR", 128);

        String input = "thongABV";
        String algorithmName = "ARCFOUR";
        // ctr/ cts/ gcm
        String inputAlgorithm = "ARCFOUR/ECB/NoPadding";

        String encrypt = ins.encryptText(input, "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);
        System.out.println(encrypt);
//        System.out.println(ins.decryptText(encrypt, "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName));

//        ins.encryptFile("E:\\Encryption Key\\slide.zip", "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);
//        ins.decryptFile("E:\\Encryption Key\\slide_encrypt.zip", "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);
    }
}
