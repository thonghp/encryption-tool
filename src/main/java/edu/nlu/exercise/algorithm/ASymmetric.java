package edu.nlu.exercise.algorithm;

import edu.nlu.exercise.utils.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ASymmetric {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Cipher cipher;

    private static SecureRandom secureRandom = new SecureRandom();

    private static Base64.Encoder encoder = Base64.getEncoder();
    private static Base64.Decoder decoder = Base64.getDecoder();

    // sử dụng public để encrypt và private để decrypt
    public void generateKey(int size) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(size);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate(); // used to decrypt
        publicKey = pair.getPublic(); // used to encrypt

        File folderName = new File("E:\\Encryption Key");
        if (!folderName.exists()) {
            folderName.mkdir();
        }

        // save key in a file
        try (FileOutputStream fos = new FileOutputStream(folderName + "/public.key")) {
            fos.write(publicKey.getEncoded());
        }

        try (FileOutputStream fos = new FileOutputStream(folderName + "/private.key")) {
            fos.write(privateKey.getEncoded());
        }
    }

    public PublicKey readPublicKey(String path) throws Exception {
        byte[] encodedpublic = Files.readAllBytes(Paths.get(path));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedpublic);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        publicKey = kf.generatePublic(keySpec);

        return publicKey;
    }

    public PrivateKey readPrivateKey(String path) throws Exception {
        byte[] encodedprivate = Files.readAllBytes(Paths.get(path));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedprivate);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        privateKey = kf.generatePrivate(keySpec);

        return privateKey;
    }

    public String encryptText(String text, String inputAlgorithm, String keyPath) throws Exception {
        cipher = Cipher.getInstance(inputAlgorithm);
        PublicKey pubKey = readPublicKey(keyPath);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(text.getBytes("UTF-8"));

        return encoder.encodeToString(cipherText);
    }

    public String decryptText(String encrypt, String inputAlgorithm, String keyPath) throws Exception {
        cipher = Cipher.getInstance(inputAlgorithm);
        PrivateKey privKey = readPrivateKey(keyPath);
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] decoted = decoder.decode(encrypt);
        byte[] plainText = cipher.doFinal(decoted);

        return new String(plainText, "UTF-8");
    }


//    public String encryptFile(String fileName, String keyPath, String inputAlgorithm) throws Exception {
//        cipher = Cipher.getInstance(inputAlgorithm);
//        PublicKey pubKey = readPublicKey(keyPath);
//        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
//
//        return FileUtils.fileHandling(fileName, "E:\\Encryption Key\\", "_encrypt", cipher);
//    }
//
//    public String decryptFile(String fileEncrypt, String keyPath, String inputAlgorithm) throws Exception {
//        cipher = Cipher.getInstance(inputAlgorithm);
//        PrivateKey privKey = readPrivateKey(keyPath);
//        cipher.init(Cipher.DECRYPT_MODE, privKey);
//
//
//        return FileUtils.fileHandling(fileEncrypt, "E:\\Encryption Key\\", "_decrypt", cipher);
//    }

    public static void main(String[] args) throws Exception {
        ASymmetric aSymmetric = new ASymmetric();

//        aSymmetric.generateKey(1024);

        String message = "thong!@#$%^&*";
        String inputAlgorithm = "RSA";
        String encrypt = aSymmetric.encryptText(message, inputAlgorithm, "E:\\Encryption Key\\public.key");
        System.out.println(encrypt);
        System.out.println(aSymmetric.decryptText(encrypt, inputAlgorithm, "E:\\Encryption Key\\private.key"));

//        aSymmetric.encryptFile("E:\\Encryption Key\\a.png", "E:\\Encryption Key\\public.key", inputAlgorithm);
//        aSymmetric.decryptFile("","E:\\Encryption Key\\private.key", inputAlgorithm);


    }
}
