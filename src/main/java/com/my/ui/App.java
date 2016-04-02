package com.my.ui;

import com.my.enums.CipherAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class App extends JFrame {

    private JLabel label;
    private JList list;
    private TextArea input, output;
    private Button cipherButton, decipherButton;
    private JPanel panel;
    private JPanel northPanel, southPanel;

    public App() throws HeadlessException {
        initUi();
    }

    private void initUi() {
        createWindow();
        createMenuBar();
        createPanel();
        createTopPanel();
        createSouthPanel();
        createAlgorithmSelect();
        createInputOutputTextAreas();
    }


    private void createInputOutputTextAreas() {
        input = new TextArea();
//        input.setPreferredSize(new Dimension(300, 100));
        output = new TextArea("Output");
//        output.setPreferredSize(new Dimension(300, 100));
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
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    private void createTopPanel() {
        northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panel.add(northPanel, BorderLayout.NORTH);
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(panel);
    }

    private void createAlgorithmSelect() {
        list = new JList(CipherAlgorithm.values());
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                CipherAlgorithm algorithm = (CipherAlgorithm) list.getSelectedValue();
            }
        });

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(list);
        pane.setPreferredSize(new Dimension(200, 200));
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
