package elte.szofttech.bomberman.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
    private Scene charSelect;
    private Scene gamePanel;
    private GameEngine engine;
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGTH = 1000;

    public GameGUI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set frame dimensions and other properties
        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGTH));
        frame.setResizable(false);
        frame.pack();
        // Add menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> {
            this.startCharSelect();
        });
        gameMenu.add(newGame);
        menuBar.add(gameMenu); 

        //gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGHT, engine, this);
        startCharSelect();

        //frame.getContentPane().add(gameArea);
        frame.pack();
        frame.setVisible(true);
    }

    public void startCharSelect(){
        frame.setVisible(false);
        frame.getContentPane().removeAll();
        engine = new GameEngine(WINDOW_WIDTH, 3);
        charSelect = new CharSelectPanel(WINDOW_WIDTH,WINDOW_HEIGTH,engine, this);
        gamePanel = null;
        //gamePanel.setBackground(Color.WHITE);
        frame.getContentPane().add(charSelect);
        frame.pack();
        frame.setVisible(true);
    }

    public void startGame(){
        gamePanel = new GamePanel(WINDOW_WIDTH, WINDOW_HEIGTH,engine,this,engine.getPlayers().size());
        frame.getContentPane().removeAll();
        frame.setVisible(false);
        frame.getContentPane().add(gamePanel);
        //frame.getContentPane().add(gameArea);
        frame.pack();
        frame.setVisible(true);
    }
}
