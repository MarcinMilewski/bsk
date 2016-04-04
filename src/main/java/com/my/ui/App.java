package com.my.ui;

import com.my.core.cryptography.enums.Algorithm;
import com.my.core.cryptography.factory.DecryptorFactory;
import com.my.core.cryptography.factory.EncryptorFactory;
import com.my.ui.creator.factory.AlgorithmFieldsCreatorFactory;
import com.my.ui.reader.factory.AlgorithmFieldsReaderFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Properties;

public class App extends JFrame {

    private JLabel label;
    private JList list;
    private TextArea input, output;
    private Button encryptButton, decryptButton, decryptOutput;
    private JPanel panel;
    private JPanel northPanel, southPanel, centerPanel, northWestPanel;
    private List<TextField> algorithmTextFields;
    private Algorithm algorithm;

    public App() throws HeadlessException {
        initUi();
    }

    private void initUi() {
        createWindow();
        createMenuBar();
        createPanel();
        createNorthPanel();
        createAlgorithmSelect();
        createNorthWestPanel();
        createCenterPanel();
        createSouthPanel();
        createCipherDecipherButtons();
        createInputOutputTextAreas();

        pack();
    }

    private void createNorthWestPanel() {
        northWestPanel = new JPanel();
        northWestPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        northPanel.add(northWestPanel);
    }

    private void createCipherDecipherButtons() {
        encryptButton = new Button("Encrypt");
        decryptButton = new Button("Decrypt");
        decryptOutput = new Button("Decrypt output");
        centerPanel.add(encryptButton);
        centerPanel.add(decryptButton);
        centerPanel.add(decryptOutput);

        encryptButton.addActionListener(e -> {
            Properties properties = AlgorithmFieldsReaderFactory.getAlgorithmFieldsReader(algorithm).read(algorithmTextFields);
            String encrypted = EncryptorFactory.getEncryptor(algorithm).encrypt(input.getText(), properties);
            output.setText(encrypted);
        });

        decryptButton.addActionListener(e -> {
            Properties properties = AlgorithmFieldsReaderFactory.getAlgorithmFieldsReader(algorithm).read(algorithmTextFields);
            String encrypted = DecryptorFactory.getDecryptor(algorithm).decrypt(input.getText(), properties);
            output.setText(encrypted);
        });

        decryptOutput.addActionListener(e -> {
            Properties properties = AlgorithmFieldsReaderFactory.getAlgorithmFieldsReader(algorithm).read(algorithmTextFields);
            String encrypted = DecryptorFactory.getDecryptor(algorithm).decrypt(output.getText(), properties);
            output.setText(encrypted);
        });
    }

    private void createCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 2;
        c.weightx = 0.0;
        panel.add(centerPanel, c);
    }


    private void createInputOutputTextAreas() {
        input = new TextArea();
        output = new TextArea("Output");
        output.setEditable(false);

        GridBagConstraints inputConstraint = new GridBagConstraints();
        inputConstraint.fill = GridBagConstraints.HORIZONTAL;
        inputConstraint.gridx =0;
        inputConstraint.gridy =0;
        inputConstraint.gridwidth = 3;
        inputConstraint.weightx =0.0;

        GridBagConstraints outputConstraint = new GridBagConstraints();
        outputConstraint.fill = GridBagConstraints.HORIZONTAL;
        outputConstraint.gridx =0;
        outputConstraint.gridy =1;
        outputConstraint.gridwidth = 3;
        outputConstraint.weightx = 0.0;
        southPanel.add(input, inputConstraint);
        southPanel.add(output, outputConstraint);

    }

    private void createSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 3;
        c.weightx = 0.0;
        panel.add(southPanel, c);
    }

    private void createNorthPanel() {
        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 0;
        c.weightx = 0.0;
        panel.add(northPanel, c);
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(panel);
    }

    private void createAlgorithmSelect() {
        list = new JList(Algorithm.values());
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                northWestPanel.removeAll();
                algorithm = (Algorithm) list.getSelectedValue();
                algorithmTextFields = AlgorithmFieldsCreatorFactory.getCreator(algorithm).getTextFields();
                for (TextField algorithmTextField : algorithmTextFields) {
                    northWestPanel.add(algorithmTextField);
                }
                northWestPanel.revalidate();
            }
        });

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(list);
        northPanel.add(pane);
    }

    private void createWindow() {
        setTitle("BSK");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.addActionListener((actionEvent) -> System.exit(0));
        file.add(eMenuItem);
        menuBar.add(file);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args ) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });
    }
}
