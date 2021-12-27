package edu.nlu.exercise.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMD_SHA {

    public String hashText(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hash = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String hashFile(String file, String algorithm) throws Exception {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        InputStream is = new FileInputStream(new File(file));
        DigestInputStream dis = new DigestInputStream(is, md);

        byte[] buffer = new byte[1024];

        int read = dis.read(buffer);
        while (read != -1) {
            read = dis.read(buffer);
        }
        BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
        String hashText = number.toString(16);

        return hashText;
    }
}

