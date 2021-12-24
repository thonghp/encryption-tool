package edu.nlu.exercise;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener {

    private JPanel contentPane;

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ButtonGroup buttonGroup1 = new ButtonGroup();

    private JTextField tfOutputText1;
    private JTextField tfInputText1;
    private JTextField tfKey1;
    private JTextField tfInputFile1;
    private JTextField tfOutputFile1;
    private JTextField tfKey4;
    private JTextField tfPub4;
    private JTextField tfPriv4;
    private JTextField tfOutputText3;
    private JTextField tfInputText3;
    private JTextField tfInputFile3;
    private JTextField tfOutputFile3;
    private JTextField tfOutputText2;
    private JTextField tfInputText2;
    private JTextField tfKey2;
    private JTextField tfInputFile2;
    private JTextField tfOutputFile2;
    private JTextField tfHash3;

    private JRadioButton rbEncrypt1;
    private JRadioButton rbDecrypt1;
    private JRadioButton rbEncrypt2;
    private JRadioButton rbDecrypt2;

    private JButton btnSelect1;
    private JButton btnStart1;
    private JButton btnSelectKey1;
    private JButton btnStart2;
    private JButton btnSelect2;
    private JButton btnSelectKey2;
    private JButton btnStart3;
    private JButton btnSelect3;
    private JButton btnCheck3;
    private JButton btnStart4;

    private JCheckBox cb11;
    private JCheckBox cb12;
    private JCheckBox cb21;
    private JCheckBox cb22;
    private JCheckBox cb31;
    private JCheckBox cb32;
    private JCheckBox cb41;
    private JCheckBox cb42;

    private JComboBox cbbAlgorithm1;
    private JComboBox cbbPadding1;
    private JComboBox cbbMode1;
    private JComboBox cbbAlgorithm2;
    private JComboBox cbbMode2;
    private JComboBox cbbPadding2;
    private JComboBox cbbSize3;
    private JComboBox cbbAlgorithm3;
    private JComboBox cbbSize41;
    private JComboBox cbbAlgorithm41;

    private DefaultComboBoxModel cbmSize41;

    private HashMD_SHA hashMD = new HashMD_SHA();
    private Symmetric symmetric = new Symmetric();
    private ASymmetric aSymmetric = new ASymmetric();

    public GUI() {
        setResizable(false);
        setTitle("SISS Tools");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 800, 495);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 784, 22);
        contentPane.add(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mniNew = new JMenuItem("New");
        mnFile.add(mniNew);

        JMenuItem mniOpen = new JMenuItem("Open");
        mnFile.add(mniOpen);

        JMenuItem mniSave = new JMenuItem("Save");
        mnFile.add(mniSave);

        JMenuItem mniExit = new JMenuItem("Exit");
        mnFile.add(mniExit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mniHelp = new JMenuItem("Help");
        mnHelp.add(mniHelp);

        JMenuItem mniAbout = new JMenuItem("About");
        mnHelp.add(mniAbout);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tabbedPane.setBounds(0, 27, 784, 430);
        contentPane.add(tabbedPane);

        JPanel pn1 = new JPanel();
        tabbedPane.addTab("Symmetric", null, pn1, null);
        pn1.setLayout(null);

        rbEncrypt1 = new JRadioButton("Encrypt");
        buttonGroup.add(rbEncrypt1);
        rbEncrypt1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rbEncrypt1.setFocusable(false);
        rbEncrypt1.setBounds(6, 7, 84, 23);
        rbEncrypt1.setSelected(true);
        pn1.add(rbEncrypt1);
        rbEncrypt1.addActionListener(this);

        rbDecrypt1 = new JRadioButton("Decrypt");
        buttonGroup.add(rbDecrypt1);
        rbDecrypt1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rbDecrypt1.setFocusable(false);
        rbDecrypt1.setBounds(102, 7, 84, 23);
        pn1.add(rbDecrypt1);
        rbDecrypt1.addActionListener(this);

        JPanel pn11 = new JPanel();
        pn11.setLayout(null);
        pn11.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Select", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn11.setBounds(6, 55, 660, 60);
        pn1.add(pn11);

        JLabel lb11 = new JLabel("Algorithms");
        lb11.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb11.setBounds(11, 20, 80, 20);
        pn11.add(lb11);

        cbbAlgorithm1 = new JComboBox();
        cbbAlgorithm1
                .setModel(new DefaultComboBoxModel(new String[]{"AES", "ARCFOUR", "Blowfish", "DES", "DESede", "RC2"}));
        cbbAlgorithm1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbAlgorithm1.setBounds(100, 20, 130, 22);
        pn11.add(cbbAlgorithm1);
        cbbAlgorithm1.addActionListener(this);

        JLabel lb13 = new JLabel("Mode");
        lb13.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb13.setBounds(250, 20, 40, 20);
        pn11.add(lb13);

        JLabel lb14 = new JLabel("Padding");
        lb14.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb14.setBounds(450, 20, 55, 20);
        pn11.add(lb14);

        cbbMode1 = new JComboBox();
        cbbMode1.setModel(new DefaultComboBoxModel(new String[]{"NONE", "ECB", "CBC", "PCBC", "CFB",
                "OFB", "CTR", "CTS", "GCM", "CFB8", "CFB64", "OFB8", "OFB64"})); // ccm, ocb
        cbbMode1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbMode1.setBounds(300, 20, 130, 22);
        pn11.add(cbbMode1);
        cbbMode1.addActionListener(this);

        cbbPadding1 = new JComboBox();
        cbbPadding1.setModel(new DefaultComboBoxModel(new String[]{"NONE"}));
        cbbPadding1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbPadding1.setBounds(515, 20, 130, 22);
        pn11.add(cbbPadding1);

        JPanel pn12 = new JPanel();
        pn12.setLayout(null);
        pn12.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Encode", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn12.setBounds(6, 130, 765, 266);
        pn1.add(pn12);

        JLabel lb16 = new JLabel("Input String");
        lb16.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb16.setBounds(11, 70, 80, 20);
        pn12.add(lb16);

        JLabel lb17 = new JLabel("Output String");
        lb17.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb17.setBounds(11, 110, 90, 20);
        pn12.add(lb17);

        JLabel lb18 = new JLabel("Input File");
        lb18.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb18.setBounds(11, 150, 80, 20);
        pn12.add(lb18);

        JLabel lb19 = new JLabel("Output File");
        lb19.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb19.setBounds(11, 190, 80, 20);
        pn12.add(lb19);

        JLabel lb15 = new JLabel("Key");
        lb15.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb15.setBounds(11, 30, 80, 20);
        pn12.add(lb15);

        tfOutputText1 = new JTextField();
        tfOutputText1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfOutputText1.setColumns(10);
        tfOutputText1.setBounds(130, 110, 515, 22);
        pn12.add(tfOutputText1);

        tfInputText1 = new JTextField();
        tfInputText1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfInputText1.setColumns(10);
        tfInputText1.setBounds(130, 70, 515, 22);
        pn12.add(tfInputText1);

        tfKey1 = new JTextField();
        tfKey1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfKey1.setColumns(10);
        tfKey1.setBounds(130, 30, 515, 22);
        pn12.add(tfKey1);

        tfInputFile1 = new JTextField();
        tfInputFile1.setColumns(10);
        tfInputFile1.setBounds(130, 150, 515, 22);
        pn12.add(tfInputFile1);

        tfOutputFile1 = new JTextField();
        tfOutputFile1.setColumns(10);
        tfOutputFile1.setBounds(130, 190, 515, 22);
        pn12.add(tfOutputFile1);

        btnSelect1 = new JButton("Select File");
        btnSelect1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSelect1.setFocusable(false);
        btnSelect1.setBounds(655, 149, 100, 25);
        pn12.add(btnSelect1);
        btnSelect1.addActionListener(this);

        btnStart1 = new JButton("Start");
        btnStart1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnStart1.setFocusable(false);
        btnStart1.setBounds(310, 230, 89, 25);
        pn12.add(btnStart1);
        btnStart1.addActionListener(this);

        cb11 = new JCheckBox("Text");
        cb11.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb11.setBounds(11, 230, 60, 23);
        pn12.add(cb11);

        cb12 = new JCheckBox("File");
        cb12.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb12.setBounds(87, 230, 60, 23);
        pn12.add(cb12);

        btnSelectKey1 = new JButton("Select");
        btnSelectKey1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSelectKey1.setFocusable(false);
        btnSelectKey1.setBounds(655, 29, 100, 25);
        pn12.add(btnSelectKey1);
        btnSelectKey1.addActionListener(this);

        JLabel lbImg1 = new JLabel(new ImageIcon(EncryptionUtils.resizeImage(
                new ImageIcon("assets/symmetric.png").getImage(), 100, 100)));
        lbImg1.setBackground(Color.WHITE);
        lbImg1.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg1.setBounds(675, 12, 100, 100);
        pn1.add(lbImg1);
        lbImg1.setOpaque(true);

        JPanel pn2 = new JPanel();
        tabbedPane.addTab("Asymmetric", null, pn2, null);
        pn2.setLayout(null);

        rbEncrypt2 = new JRadioButton("Encrypt");
        buttonGroup1.add(rbEncrypt2);
        rbEncrypt2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rbEncrypt2.setFocusable(false);
        rbEncrypt2.setBounds(6, 7, 84, 23);
        pn2.add(rbEncrypt2);
        rbEncrypt2.setSelected(true);

        rbDecrypt2 = new JRadioButton("Decrypt");
        buttonGroup1.add(rbDecrypt2);
        rbDecrypt2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rbDecrypt2.setFocusable(false);
        rbDecrypt2.setBounds(102, 7, 84, 23);
        pn2.add(rbDecrypt2);

        JPanel pn21 = new JPanel();
        pn21.setLayout(null);
        pn21.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Select", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn21.setBounds(6, 55, 660, 60);
        pn2.add(pn21);

        JLabel lb21 = new JLabel("Algorithms");
        lb21.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb21.setBounds(11, 20, 80, 20);
        pn21.add(lb21);

        cbbAlgorithm2 = new JComboBox();
        cbbAlgorithm2.setModel(new DefaultComboBoxModel(new String[]{"RSA"}));
        cbbAlgorithm2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbAlgorithm2.setBounds(100, 20, 130, 22);
        pn21.add(cbbAlgorithm2);

        JLabel lb23 = new JLabel("Mode");
        lb23.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb23.setBounds(250, 20, 40, 20);
        pn21.add(lb23);

        JLabel lb24 = new JLabel("Padding");
        lb24.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb24.setBounds(450, 20, 55, 20);
        pn21.add(lb24);

        cbbMode2 = new JComboBox();
        cbbMode2.setModel(new DefaultComboBoxModel(new String[]{"NONE", "ECB", "CBC", "CFB", "OFB", "PCBC", "CTR"}));
        cbbMode2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbMode2.setBounds(300, 20, 130, 22);
        pn21.add(cbbMode2);

        cbbPadding2 = new JComboBox();
        cbbPadding2.setModel(new DefaultComboBoxModel(new String[]{"NoPadding", "PKCS5Padding", "ISO10126Padding"}));
        cbbPadding2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbPadding2.setBounds(515, 20, 130, 22);
        pn21.add(cbbPadding2);

        JPanel pn22 = new JPanel();
        pn22.setLayout(null);
        pn22.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Encode", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn22.setBounds(6, 130, 765, 266);
        pn2.add(pn22);

        JLabel lb26 = new JLabel("Input String");
        lb26.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb26.setBounds(11, 70, 80, 20);
        pn22.add(lb26);

        JLabel lb27 = new JLabel("Output String");
        lb27.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb27.setBounds(11, 110, 90, 20);
        pn22.add(lb27);

        JLabel lb28 = new JLabel("Input File");
        lb28.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb28.setBounds(11, 150, 80, 20);
        pn22.add(lb28);

        JLabel lb29 = new JLabel("Output File");
        lb29.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb29.setBounds(11, 190, 80, 20);
        pn22.add(lb29);

        JLabel lb25 = new JLabel("Key");
        lb25.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb25.setBounds(11, 30, 80, 20);
        pn22.add(lb25);

        tfOutputText2 = new JTextField();
        tfOutputText2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfOutputText2.setColumns(10);
        tfOutputText2.setBounds(130, 110, 515, 22);
        pn22.add(tfOutputText2);

        tfInputText2 = new JTextField();
        tfInputText2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfInputText2.setColumns(10);
        tfInputText2.setBounds(130, 70, 515, 22);
        pn22.add(tfInputText2);

        tfKey2 = new JTextField();
        tfKey2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfKey2.setColumns(10);
        tfKey2.setBounds(130, 30, 515, 22);
        pn22.add(tfKey2);

        tfInputFile2 = new JTextField();
        tfInputFile2.setColumns(10);
        tfInputFile2.setBounds(130, 150, 515, 22);
        pn22.add(tfInputFile2);

        tfOutputFile2 = new JTextField();
        tfOutputFile2.setColumns(10);
        tfOutputFile2.setBounds(130, 190, 515, 22);
        pn22.add(tfOutputFile2);

        btnSelect2 = new JButton("Select File");
        btnSelect2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSelect2.setFocusable(false);
        btnSelect2.setBounds(655, 149, 100, 25);
        pn22.add(btnSelect2);

        btnStart2 = new JButton("Start");
        btnStart2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnStart2.setFocusable(false);
        btnStart2.setBounds(310, 230, 89, 25);
        pn22.add(btnStart2);

        cb21 = new JCheckBox("Text");
        cb21.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb21.setBounds(11, 230, 60, 23);
        pn22.add(cb21);

        cb22 = new JCheckBox("File");
        cb22.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb22.setBounds(87, 230, 60, 23);
        pn22.add(cb22);

        btnSelectKey2 = new JButton("Select");
        btnSelectKey2.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSelectKey2.setFocusable(false);
        btnSelectKey2.setBounds(655, 29, 100, 25);
        pn22.add(btnSelectKey2);

        JLabel lbImg2 = new JLabel(new ImageIcon(EncryptionUtils.resizeImage(
                new ImageIcon("assets/asymmetric.jpg").getImage(), 100, 100)));
        lbImg2.setOpaque(true);
        lbImg2.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg2.setBackground(Color.WHITE);
        lbImg2.setBounds(675, 12, 100, 100);
        pn2.add(lbImg2);

        JPanel pn3 = new JPanel();
        tabbedPane.addTab("Hash", null, pn3, null);
        pn3.setLayout(null);

        JPanel pn31 = new JPanel();
        pn31.setLayout(null);
        pn31.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Select", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn31.setBounds(6, 27, 615, 70);
        pn3.add(pn31);

        JLabel lb31 = new JLabel("Algorithms");
        lb31.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb31.setBounds(10, 30, 75, 20);
        pn31.add(lb31);

        JLabel lb32 = new JLabel("Key size");
        lb32.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb32.setBounds(345, 30, 75, 20);
        pn31.add(lb32);

        cbbAlgorithm3 = new JComboBox();
        cbbAlgorithm3.setModel(new DefaultComboBoxModel(new String[]{"MD2", "MD5", "SHA-1", "SHA-224", "SHA-256",
                "SHA-384", "SHA-512", "SHA-512/224", "SHA-512/256", "SHA3-224", "SHA3-256", "SHA3-384", "SHA3-512"}));
        cbbAlgorithm3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbAlgorithm3.setBounds(110, 30, 120, 22);
        pn31.add(cbbAlgorithm3);

        cbbSize3 = new JComboBox();
        cbbSize3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbSize3.setBounds(465, 30, 120, 22);
        pn31.add(cbbSize3);

        JLabel lbImg3 = new JLabel(new ImageIcon(EncryptionUtils.resizeImage(
                new ImageIcon("assets/hash.png").getImage(), 120, 100)));
        lbImg3.setOpaque(true);
        lbImg3.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg3.setBackground(Color.WHITE);
        lbImg3.setBounds(649, 20, 120, 100);
        pn3.add(lbImg3);

        JPanel pn32 = new JPanel();
        pn32.setLayout(null);
        pn32.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Encode", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn32.setBounds(6, 130, 765, 266);
        pn3.add(pn32);

        JLabel lb33 = new JLabel("Input String");
        lb33.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb33.setBounds(11, 30, 80, 20);
        pn32.add(lb33);

        JLabel lb34 = new JLabel("Output String");
        lb34.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb34.setBounds(11, 70, 90, 20);
        pn32.add(lb34);

        JLabel lb35 = new JLabel("Input File");
        lb35.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb35.setBounds(11, 110, 80, 20);
        pn32.add(lb35);

        JLabel lb36 = new JLabel("Output File");
        lb36.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb36.setBounds(11, 150, 80, 20);
        pn32.add(lb36);

        tfOutputText3 = new JTextField();
        tfOutputText3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfOutputText3.setColumns(10);
        tfOutputText3.setBounds(130, 70, 515, 22);
        pn32.add(tfOutputText3);

        tfInputText3 = new JTextField();
        tfInputText3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfInputText3.setColumns(10);
        tfInputText3.setBounds(130, 30, 515, 22);
        pn32.add(tfInputText3);

        tfInputFile3 = new JTextField();
        tfInputFile3.setColumns(10);
        tfInputFile3.setBounds(130, 110, 515, 22);
        pn32.add(tfInputFile3);

        tfOutputFile3 = new JTextField();
        tfOutputFile3.setColumns(10);
        tfOutputFile3.setBounds(130, 150, 515, 22);
        pn32.add(tfOutputFile3);

        btnSelect3 = new JButton("Select File");
        btnSelect3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSelect3.setFocusable(false);
        btnSelect3.setBounds(655, 109, 100, 25);
        pn32.add(btnSelect3);
        btnSelect3.addActionListener(this);

        btnStart3 = new JButton("Start");
        btnStart3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnStart3.setFocusable(false);
        btnStart3.setBounds(310, 230, 89, 25);
        pn32.add(btnStart3);
        btnStart3.addActionListener(this);

        cb31 = new JCheckBox("Text");
        cb31.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb31.setBounds(11, 234, 60, 23);
        pn32.add(cb31);

        cb32 = new JCheckBox("File");
        cb32.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb32.setBounds(87, 234, 60, 23);
        pn32.add(cb32);

        JLabel lb37 = new JLabel("Original Hash");
        lb37.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb37.setBounds(11, 190, 80, 20);
        pn32.add(lb37);

        tfHash3 = new JTextField();
        tfHash3.setColumns(10);
        tfHash3.setBounds(130, 190, 515, 22);
        pn32.add(tfHash3);

        btnCheck3 = new JButton("Check");
        btnCheck3.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCheck3.setFocusable(false);
        btnCheck3.setBounds(655, 189, 100, 25);
        pn32.add(btnCheck3);
        btnCheck3.addActionListener(this);

        JPanel pn4 = new JPanel();
        tabbedPane.addTab("Key", null, pn4, null);
        pn4.setLayout(null);

        JPanel pn41 = new JPanel();
        pn41.setLayout(null);
        pn41.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Symmetric", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn41.setBounds(11, 11, 608, 152);
        pn4.add(pn41);

        JLabel lb41 = new JLabel("Algorithms");
        lb41.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb41.setBounds(10, 30, 75, 20);
        pn41.add(lb41);

        JLabel lb42 = new JLabel("Key size");
        lb42.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb42.setBounds(10, 70, 75, 20);
        pn41.add(lb42);

        JLabel lb43 = new JLabel("Key");
        lb43.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb43.setBounds(10, 110, 75, 20);
        pn41.add(lb43);

        cbbAlgorithm41 = new JComboBox();
        cbbAlgorithm41.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbAlgorithm41.setBounds(110, 30, 100, 22);
        cbbAlgorithm41
                .setModel(new DefaultComboBoxModel(new String[]{"AES", "ARCFOUR", "Blowfish", "DES", "DESede", "RC2"}));
        pn41.add(cbbAlgorithm41);
        cbbAlgorithm41.addActionListener(this);

        cbbSize41 = new JComboBox();
        cbbSize41.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbSize41.setBounds(110, 70, 100, 22);
        String size = cbbAlgorithm41.getSelectedItem().toString();
        cbbSize41.setModel(new DefaultComboBoxModel(new String[]{"128", "192", "256"}));
        pn41.add(cbbSize41);

        tfKey4 = new JTextField();
        tfKey4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfKey4.setColumns(10);
        tfKey4.setBounds(110, 110, 488, 22);
        pn41.add(tfKey4);

        JLabel lbImg41 = new JLabel(new ImageIcon(EncryptionUtils.resizeImage(
                new ImageIcon("assets/symmetric.png").getImage(), 140, 140)));
        lbImg41.setBackground(Color.WHITE);
        lbImg41.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg41.setBounds(629, 20, 140, 140);
        pn4.add(lbImg41);
        lbImg41.setOpaque(true);

        JPanel pn42 = new JPanel();
        pn42.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Asymmetric", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        pn42.setBounds(11, 200, 608, 145);
        pn4.add(pn42);
        pn42.setLayout(null);

        JLabel lb44 = new JLabel("Algorithms");
        lb44.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb44.setBounds(10, 35, 75, 20);
        pn42.add(lb44);

        JComboBox cbbAlgorithm42 = new JComboBox();
        cbbAlgorithm42.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbAlgorithm42.setBounds(110, 35, 100, 22);
        pn42.add(cbbAlgorithm42);

        JLabel lb45 = new JLabel("Key size");
        lb45.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb45.setBounds(10, 75, 75, 20);
        pn42.add(lb45);

        JComboBox cbbSize42 = new JComboBox();
        cbbSize42.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbSize42.setBounds(110, 75, 100, 22);
        pn42.add(cbbSize42);

        JLabel lb46 = new JLabel("Public key");
        lb46.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb46.setBounds(235, 35, 75, 20);
        pn42.add(lb46);

        tfPub4 = new JTextField();
        tfPub4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfPub4.setColumns(10);
        tfPub4.setBounds(309, 35, 289, 22);
        pn42.add(tfPub4);

        JLabel lb47 = new JLabel("Private key");
        lb47.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lb47.setBounds(235, 75, 75, 20);
        pn42.add(lb47);

        tfPriv4 = new JTextField();
        tfPriv4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tfPriv4.setColumns(10);
        tfPriv4.setBounds(309, 75, 289, 22);
        pn42.add(tfPriv4);

        btnStart4 = new JButton("Start");
        btnStart4.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnStart4.setBounds(310, 365, 90, 25);
        pn4.add(btnStart4);
        btnStart4.addActionListener(this);

        JLabel lbImg42 = new JLabel(new ImageIcon(EncryptionUtils.resizeImage(
                new ImageIcon("assets/asymmetric.jpg").getImage(), 140, 140)));
        lbImg42.setOpaque(true);
        lbImg42.setHorizontalAlignment(SwingConstants.CENTER);
        lbImg42.setBackground(Color.WHITE);
        lbImg42.setBounds(629, 205, 140, 140);
        pn4.add(lbImg42);

        cb41 = new JCheckBox("Symmetric");
        cb41.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb41.setBounds(11, 365, 100, 23);
        pn4.add(cb41);

        cb42 = new JCheckBox("Asymmetric");
        cb42.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cb42.setBounds(120, 365, 100, 23);
        pn4.add(cb42);

    }

    public void resetText() {
        tfInputText1.setText("");
        tfOutputText1.setText("");
        tfInputFile1.setText("");
        tfOutputFile1.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // symmetric

//        xử lý phần select mode và padding
        if (e.getSource() == cbbMode1) {
            cbbPadding1.setModel(new DefaultComboBoxModel(EncryptionUtils.getPaddingSymmetric(cbbMode1.getSelectedItem().toString())));
        }

//        xử lý phần chọn key
        if (e.getSource() == btnSelectKey1) {
            EncryptionUtils.selectFile(tfKey1);
        }

//         xử lý chọn file
        if (e.getSource() == btnSelect1) {
            EncryptionUtils.selectFile(tfInputFile1);
        }

//        xử lý mỗi khi chọn radio button khác thì phải reset lại cho người dùng nhập
        if (e.getSource() == rbEncrypt1 || e.getSource() == rbDecrypt1) {
            resetText();
        }

        if (e.getSource() == btnStart1) {
            // xử lý nếu key chưa được chọn
            if (tfKey1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "request to choose algorithm key !!!");
                return;
            }

            // xử lý nếu chọn sai key
            if (!tfKey1.getText().equals("E:\\Encryption Key\\symmetric.key")) {
                JOptionPane.showMessageDialog(null, "Invalid key !!!");
                return;
            }

            if (cb12.isSelected() && tfInputFile1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "file selection request !!!");
                return;
            }

            // xử lý nếu chưa chọn chế độ encrypt hay decrypt
            if (!cb11.isSelected() && !cb12.isSelected()) {
                JOptionPane.showMessageDialog(null, "request to choose encryption type !!!");
            } else {
                String algorithmName = cbbAlgorithm1.getSelectedItem().toString();
                String mode = cbbMode1.getSelectedItem().toString();
                String padding = cbbPadding1.getSelectedItem().toString();
                String message = tfInputText1.getText();
                String keyPath = tfKey1.getText();
                String inputAlgorithm = EncryptionUtils.getAlgorithm(algorithmName, mode, padding);

                String fileName = tfInputFile1.getText();

                // xử lý chỉ được xài với NoPadding
                if ((inputAlgorithm.contains("CTR") || inputAlgorithm.contains("CTS") || inputAlgorithm.contains("GCM"))
                        && !inputAlgorithm.contains("NoPadding")) {
                    JOptionPane.showMessageDialog(null, "This mode must be used with NoPadding");
                    return;
                }

                // xử lý arcfour
                if (algorithmName.equals("ARCFOUR") && !inputAlgorithm.equals("ARCFOUR/ECB/NoPadding")
                        && !inputAlgorithm.equals("ARCFOUR")) {
                    JOptionPane.showMessageDialog(null,
                            "ARCFOUR algorithm can only combine with ECB mode and padding is NoPadding !!!");
                    return;
                }

                // xử lý gcm
                if (mode.equals("GCM") && !algorithmName.equals("AES")) {
                    JOptionPane.showMessageDialog(null,
                            "GCM mode can only be combined with AES algorithm !!!");
                    return;
                }

                // encrypt text
                if (cb11.isSelected() && rbEncrypt1.isSelected()) {
                    try {
                        tfOutputText1.setText(symmetric.encrypt(message, keyPath, inputAlgorithm, algorithmName));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                // decrypt text
                if (cb11.isSelected() && rbDecrypt1.isSelected()) {
                    try {
                        tfOutputText1.setText(symmetric.decrypt(message, keyPath, inputAlgorithm, algorithmName));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                // encrypt file
                if (cb12.isSelected() && rbEncrypt1.isSelected()) {
                    try {
                        JOptionPane.showMessageDialog(null, "successful encryption !!!");
                        tfOutputFile1.setText(symmetric.encryptFile(fileName, keyPath, inputAlgorithm, algorithmName));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                // decrypt file
                if (cb12.isSelected() && rbDecrypt1.isSelected()) {
                    try {
                        JOptionPane.showMessageDialog(null, "successful decryption !!!");
                        tfOutputFile1.setText(symmetric.decryptFile(fileName, keyPath, inputAlgorithm, algorithmName));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        // hash
        if (e.getSource() == btnSelect3) {
            EncryptionUtils.selectFile(tfInputFile3);
        }

        if (e.getSource() == btnCheck3) {
            if (tfHash3.getText().toLowerCase().equals(tfOutputFile3.getText())
                    || tfHash3.getText().toLowerCase().equals(tfOutputText3.getText())) {
                JOptionPane.showMessageDialog(null, "match !!!");
            } else {
                JOptionPane.showMessageDialog(null, "does not match !!!");
            }
        }

        if (e.getSource() == btnStart3) {
            String algorithm = cbbAlgorithm3.getSelectedItem().toString();

            if (cb31.isSelected()) {
                tfOutputText3.setText(hashMD.hashText(tfInputText3.getText(), algorithm));
            }

            if (cb32.isSelected()) {
                try {
                    if (tfInputFile3.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "file selection request !!!");
                    } else {
                        tfOutputFile3.setText(hashMD.hashFile(tfInputFile3.getText(), algorithm));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            if (!cb32.isSelected() && !cb31.isSelected()) {
                JOptionPane.showMessageDialog(null, "request to choose encryption type !!!");
            }
        }

        // key
        if (e.getSource() == cbbAlgorithm41) {
            cbbSize41.setModel(
                    new DefaultComboBoxModel(EncryptionUtils.getSize(cbbAlgorithm41.getSelectedItem().toString())));
        }

        if (e.getSource() == btnStart4) {
            if (!cb41.isSelected() && !cb42.isSelected()) {
                JOptionPane.showMessageDialog(null, "request to choose encryption type !!!");
            }

            // symmetric
            if (cb41.isSelected()) {
                try {
                    symmetric.generateKeySymmetric(cbbAlgorithm41.getSelectedItem().toString(),
                            Integer.parseInt(cbbSize41.getSelectedItem().toString()));
                    JOptionPane.showMessageDialog(null, "successful key generation !!!");
                    tfKey4.setText("E:\\Encryption Key\\symmetric.txt");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            // asymmetric
            if (cb42.isSelected()) {

            }
        }
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
