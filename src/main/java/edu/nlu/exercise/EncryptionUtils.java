package edu.nlu.exercise;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EncryptionUtils {
    private static final String[] SIZE_AES = {"128", "192", "256"}; // default 128
    private static final String[] SIZE_ARCFOUR = {"56", "112", "128", "168", "192", "224", "256", "384", "448", "512"}; // từ 40 -> 1024 default là 128
    private static final String[] SIZE_BLOWFISH = {"32", "56", "112", "128", "168", "192", "224", "256", "384", "448"}; // bội số của 8 từ 32 - 448, default 128
    private static final String[] SIZE_DES = {"56"};
    private static final String[] SIZE_DESEDE = {"112", "168"}; // default 168
    private static final String[] SIZE_RC2 = {"56", "112", "128", "168", "192", "224", "256", "384", "448", "512"}; // từ 40 -> 1024 default là 128
    private static final String[] SIZE_TWOFISH = {"128", "192", "256"};
    private static final String[] SIZE_IDEA = {"128"};

    private static final String[] PADDING_SYMMETRIC = {"NoPadding", "PKCS5Padding", "PKCS7Padding", "ISO10126Padding",
            "ISO7816-4Padding"};

    private static final String[] PADDING_ASYMMETRIC = {"NoPadding", "PKCS1Padding", "OAEPPadding",
            "OAEPWithMD5AndMGF1Padding", "OAEPWithSHA1AndMGF1Padding", "OAEPWithSHA-1AndMGF1Padding",
            "OAEPWithSHA-224AndMGF1Padding", "OAEPWithSHA-256AndMGF1Padding", "OAEPWithSHA-384AndMGF1Padding",
            "OAEPWithSHA-512AndMGF1Padding", "OAEPWITHSHA-512/224ANDMGF1PADDING", "OAEPWITHSHA-512/256ANDMGF1PADDING"};

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
        switch (algorithm) {
            case "AES":
                return SIZE_AES;
            case "ARCFOUR":
                return SIZE_ARCFOUR;
            case "Blowfish":
                return SIZE_BLOWFISH;
            case "DES":
                return SIZE_DES;
            case "DESede":
                return SIZE_DESEDE;
            case "RC2":
                return SIZE_RC2;
            case "Twofish":
                return SIZE_TWOFISH;
            case "IDEA":
                return SIZE_IDEA;
            default:
                return null;
        }
    }

    public static String[] getPaddingSymmetric(String mode, String option) {
        if (mode.equals("NONE")) {
            return new String[]{"NONE"};
        } else if (option.equals("symmetric")) {
            return PADDING_SYMMETRIC;
        } else {
            return PADDING_ASYMMETRIC;
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
