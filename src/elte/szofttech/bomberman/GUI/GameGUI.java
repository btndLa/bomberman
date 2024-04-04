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

public class GameGUI {

    private JFrame frame;
    private GameEngine gameArea;

    public GameGUI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        //750*750
        frame.setPreferredSize(new Dimension(766, 829));
        frame.setResizable(false);
        frame.pack();
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu); 
        //frame.getContentPane().add(gameArea.getTimerLabel(), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
}
}
