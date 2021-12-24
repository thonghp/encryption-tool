package edu.nlu.exercise;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class Symmetric {
    public static Base64.Encoder encoder = Base64.getEncoder();
    public static Base64.Decoder decoder = Base64.getDecoder();
    public Cipher cipher;

    public void generateKeySymmetric(String algorithm, int size) throws Exception {
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

    public Cipher doLengthHandling(String original, String inputAlgorithm, String algorithmName) throws Exception {
        // xử lý đội dài đầu vào với NoPadding
        String[] split = inputAlgorithm.split("/");
        String padding = split[1];

        if (inputAlgorithm.contains("NoPadding")) {
            if ((inputAlgorithm.contains("ECB") || inputAlgorithm.contains("CBC") || inputAlgorithm.contains("PCBC"))
                    && original.length() % cipher.getBlockSize() != 0) {
                cipher = Cipher.getInstance(algorithmName + "/" + padding + "/PKCS5Padding");
            }

            if (inputAlgorithm.contains("CTS") && original.length() < cipher.getBlockSize()) {
                cipher = Cipher.getInstance(algorithmName + "/CBC/PKCS5Padding");
            }
        }

        return cipher;
    }

    public String encrypt(String original, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        SecretKey key = readKey(keyPath, algorithmName);

        cipher = Cipher.getInstance(inputAlgorithm); // xài chung cipher thì mới chung getIv dc

        doLengthHandling(original, inputAlgorithm, algorithmName);

        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(original.getBytes());

        return encoder.encodeToString(cipherText);
    }

    public String decrypt(String encrypt, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        SecretKey key = readKey(keyPath, algorithmName);

        if (inputAlgorithm.equals(algorithmName) || inputAlgorithm.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cipher.getIV()));
        }

        byte[] decoted = decoder.decode(encrypt);
        byte[] plainText = cipher.doFinal(decoted);

        return new String(plainText);
    }

    public void fileHandling(String inputFile, String outputFile) throws Exception {
        File file = new File(inputFile);
        long size = 0;

        if (file.isFile()) {
            size = file.length();
        }
        String[] seperateExtension = file.getName().split("[.]");
        String rename = outputFile + "." + seperateExtension[seperateExtension.length - 1];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(rename))) {

            // đọc nhiều lần
//            byte[] buffer = new byte[128];
//            int bytesRead;

//            while ((bytesRead = bis.read(buffer)) != -1) {
//                byte[] output = cipher.update(buffer, 0, bytesRead);
//                if (output != null) {
//                    bos.write(output);
//                }
//            }

            // đọc 1 lần
            byte[] buffer = new byte[(int) size];
            int bytesRead = bis.read(buffer);
            byte[] output = cipher.update(buffer, 0, bytesRead);

            if (output != null) {
                bos.write(output);
            }

            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                bos.write(outputBytes);
            }
        }
    }

    public void encryptFile(String fileName, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        SecretKey key = readKey(keyPath, algorithmName);

        cipher = Cipher.getInstance(inputAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        fileHandling(fileName, "E:\\Encryption Key\\encrypt");
    }

    public void decryptFile(String fileEncrypt, String keyPath, String inputAlgorithm, String algorithmName) throws Exception {
        SecretKey key = readKey(keyPath, algorithmName);

        if (inputAlgorithm.equals(algorithmName) || inputAlgorithm.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(cipher.getIV()));
        }

        // viết thêm để xử lý đọc 1 lần
        fileHandling(fileEncrypt, "E:\\Encryption Key\\decrypt");
    }

    public static void main(String[] args) throws Exception {
        Symmetric ins = new Symmetric();
//        ins.generateKeySymmetric("DES", 56);

        /*
         * nopadding => ecb/cbc/pcbc là 8 - cts > 8 (DES/AES)
         * pkcs5/iso => ctr/cts/gcm(chỉ hỗ trợ cho aes) chỉ chơi dc vs nopadding
         */
        String input = "thong";
        String algorithmName = "DES";
        String inputAlgorithm = "DES/CBC/PKCS5Padding";
//        String inputAlgorithm = "DES";

//        String encrypt = ins.encrypt(input, "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);
//        System.out.println(encrypt);
//        System.out.println(ins.decrypt(encrypt, "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName));

//        ins.encryptFile("E:\\System Security\\slide.zip", "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);
//        ins.decryptFile("E:\\Encryption Key\\encrypt.zip", "E:\\Encryption Key\\symmetric.key", inputAlgorithm, algorithmName);

    }
}
