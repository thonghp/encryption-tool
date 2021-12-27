package edu.nlu.exercise.utils;

import javax.crypto.Cipher;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    // chọn file trên gui
    public static void selectFile(JTextField tf) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false); // chỉ cho chọn 1 file không cho chọn nhiều
        int response = fileChooser.showDialog(null, "Select file"); // this

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            tf.setText(file.toString());
        }
    }

    // xử lý đọc file
    public static String fileHandling(String inputFile, String outputFile, String mode, Cipher cipher) throws Exception {
        File file = new File(inputFile);
        long size = 0;

        if (file.isFile()) {
            size = file.length();
        }
        String[] seperateExtension = file.getName().split("[.]");
        String rename = outputFile + seperateExtension[0] + mode + "." + seperateExtension[seperateExtension.length - 1];
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
        return rename;
    }

    public static byte[] combineBytes(byte[] a, byte[] b) {
        byte[] combined = new byte[a.length + b.length];
        System.arraycopy(a, 0, combined, 0, a.length);
        System.arraycopy(b, 0, combined, a.length, b.length);

        return combined;
    }

    public static void writeToFile(File file, byte[] fileContent) {
        System.out.println("Writing bytes to file...");
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.write(path, fileContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Done writing bytes to file...");
    }

    public static byte[] readBytesFromFile(File file) {
        System.out.println("Reading bytes from file...");
        Path path = Paths.get(file.getAbsolutePath());
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Done reading from file...");
        return data;
    }
}
