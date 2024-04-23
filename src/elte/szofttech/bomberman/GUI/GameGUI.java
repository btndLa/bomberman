package elte.szofttech.bomberman.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

import elte.szofttech.bomberman.model.GameEngine;

// Responsible for graphical user inteface and main window
public class GameGUI {

    private JFrame frame;
    private Scene gamePanel;
    private GameEngine engine;
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGTH = 1000;

    public GameGUI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        engine = new GameEngine(WINDOW_WIDTH, 3);
        
        // Set frame dimensions and other properties
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGTH));
        frame.setResizable(false);
        frame.pack();
        // Add menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);

        //gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGTH, engine);
        StartCharSelect();
        //gamePanel.setBackground(Color.WHITE);
        //frame.getContentPane().add(gamePanel);
        //frame.getContentPane().add(gameArea);
        frame.pack();
        frame.setVisible(true);
    }

    public void StartCharSelect(){
        frame.getContentPane().add(new CharSelectPanel(WINDOW_WIDTH,WINDOW_HEIGTH,engine));
    }
}
