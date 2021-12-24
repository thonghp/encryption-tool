package edu.nlu.exercise;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EncryptionUtils {
    private static final String[] SIZE_DES = {"56"};
    private static final String[] SIZE_AES = {"128", "192", "256"}; // default 128
    private static final String[] SIZE_BLOWFISH = {"32", "128", "448"}; // bội số của 8 từ 32 - 448, default 128
    private static final String[] SIZE_DESEDE = {"112", "168"}; // default 168
    private static final String[] PADDING = {"NoPadding", "PKCS5Padding", "ISO10126Padding"};

    private static final String[] EMPTY = {""};

    public static void selectFile(JTextField tf) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false); // chỉ cho chọn 1 file không cho chọn nhiều
        int response = fileChooser.showDialog(null, "Select file"); // this

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            tf.setText(file.toString());
        }
    }

    public static String[] getSize(String algorithm) {
        if (algorithm.equals("DES")) {
            return SIZE_DES;
        } else if (algorithm.equals("AES")) {
            return SIZE_AES;
        } else if (algorithm.equals("Blowfish")) {
            return SIZE_BLOWFISH;
        } else if (algorithm.equals("DESede")) {
            return SIZE_DESEDE;
        } else {
            return EMPTY;
        }
    }

    public static String[] getPadding(String mode) {
        if (mode.equals("NONE")) {
            return new String[]{"NONE"};
        } else {
            return PADDING;
        }
    }

    public static String getAlgorithm(String algorithmName, String mode, String padding) {
        String getAlgorithm;
        if (mode.equals("NONE")) {
            getAlgorithm = algorithmName;
        } else {
            getAlgorithm = algorithmName + "/" + mode + "/" + padding;
        }
        return getAlgorithm;
    }

    // tùy chỉnh kích thước hình ảnh trong gui
    public static Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        return originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
    }
}
