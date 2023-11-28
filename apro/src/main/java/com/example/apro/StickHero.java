package com.example.apro;

import javax.swing.*;

public class StickHero {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Stick Hero");

        GameEngine engine = new GameEngine();
        GameController controller = new GameController();
        GamePanel panel = new GamePanel();

        engine.init();
        controller.init(engine, panel);
        panel.init(engine, controller);

        controller.start();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}