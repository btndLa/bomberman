package elte.szofttech.bomberman.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import elte.szofttech.bomberman.model.GameEngine;

// Responsible for graphical user inteface and main window
public class GameGUI {

    private JFrame frame;
    private GameEngine gameArea;
    private static final int WINDOW_SIZE = 1000;

    public GameGUI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameArea = new GameEngine();
        
        // Set frame dimensions and other properties
        frame.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        frame.setResizable(false);
        frame.pack();
        // Add menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu); 
        // Add game area to frame
        frame.getContentPane().add(gameArea);
        frame.pack();
        frame.setVisible(true);
}
}
