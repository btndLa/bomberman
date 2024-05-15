package elte.szofttech.bomberman.GUI;

import elte.szofttech.bomberman.model.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharSelectPanel extends Scene {
    CharSelectPanel(int width, int height, GameEngine engine, GameGUI gui){
        super(width,height,engine, gui);

        this.setLayout(new BorderLayout());
        JPanel playerOnePanel = new JPanel();
        playerOnePanel.setSize(this.getWidth() / 2, this.getHeight());
    //    playerOnePanel.setBackground(Color.green);
        ButtonGroup playerGroup = new ButtonGroup();
        //Declared as an array because of accessibility from action listeners
        final int[] players = {2};
        final boolean[] playerChoosed = {true};
        final boolean[] monsterChoosed = {true};

        JRadioButton twoplayerBTN = new JRadioButton("2");
        twoplayerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players[0] = 2;
                playerChoosed[0] = true;
            }
        });
        
        
        JRadioButton threeplayerBTN = new JRadioButton("3");
        threeplayerBTN.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            players[0] = 3;
            playerChoosed[0] = true;
          }
        });
        
        playerGroup.add(twoplayerBTN);
        playerGroup.add(threeplayerBTN);
        twoplayerBTN.setSelected(true);

        JLabel text = new JLabel("Players");

        playerOnePanel.add(text);
        playerOnePanel.add(twoplayerBTN);
        playerOnePanel.add(threeplayerBTN);

        this.add(playerOnePanel,BorderLayout.WEST);

        JPanel playerTwoPanel = new JPanel();
    //    playerTwoPanel.setBackground(Color.green);
        playerTwoPanel.setSize(this.getWidth() / 2, this.getHeight());

        ButtonGroup monsterGroup = new ButtonGroup();
        JRadioButton twomonsterBTN = new JRadioButton("2");
        //Declared as an array because of accessibility from action listeners
        final int[] monsterNumber = {2};
        twomonsterBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monsterNumber[0] = 2;
                monsterChoosed[0] = true;
            }
        });
        
        JRadioButton threemonsterBTN = new JRadioButton("3");
        threemonsterBTN.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            monsterNumber[0] = 3;
            monsterChoosed[0] = true;
          }
        });
        
        monsterGroup.add(twomonsterBTN);
        monsterGroup.add(threemonsterBTN);
        twomonsterBTN.setSelected(true);

        JLabel monstertext = new JLabel("Monsters");

        playerTwoPanel.add(monstertext);
        playerTwoPanel.add(twomonsterBTN);
        playerTwoPanel.add(threemonsterBTN);

        this.add(playerTwoPanel,BorderLayout.EAST);

        JPanel startPanel = new JPanel();
        startPanel.setSize(this.getWidth(), this.getHeight()/3);

        JButton map = new JButton("First");
        int[] level = {0};
        map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level[0] = 2;
            }
        });

        startPanel.add(map);

        JButton startBTN = new JButton("Start");
        startBTN.setSize(50,30);
        startBTN.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playerChoosed[0] && monsterChoosed[0]){
                    engine.finishedCharSelect(players[0], monsterNumber[0], level[0]);
                    gui.startGame();
                }
            }
        }));
        startPanel.add(startBTN, BorderLayout.CENTER);
        this.add(startPanel);
    }
}
