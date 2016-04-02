package com.my;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App() throws HeadlessException {
        initUi();
    }

    private void initUi() {
        setTitle("BSK");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
