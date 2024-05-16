package elte.szofttech.bomberman.GUI;

import elte.szofttech.bomberman.model.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharSelectPanel extends Scene {
    CharSelectPanel(int width, int height, GameEngine engine, GameGUI gui){
        /*
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


    //    ImageIcon level1icon = new ImageIcon("src/elte/szofttech/assets/images/level1.png");
        JRadioButton map1 = new JRadioButton("Map 1", true);
        JRadioButton map2 = new JRadioButton("Map 2", false);
        JRadioButton map3 = new JRadioButton("Map 3", false);
        int[] level = {1};

        map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level[0] = 1;
            }
        });


        map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level[0] = 2;
            }
        });


        map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level[0] = 3;
            }
        });
        ButtonGroup maps = new ButtonGroup();
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);

        JPanel mapPanel = new JPanel();
        mapPanel.setSize(this.getWidth(), this.getHeight()/3);
        mapPanel.add(map1, BorderLayout.NORTH);
        mapPanel.add(map2, BorderLayout.NORTH);
        mapPanel.add(map3, BorderLayout.NORTH);

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
        startPanel.add(mapPanel, BorderLayout.NORTH);
        startPanel.add(Box.createVerticalStrut(10));
        startPanel.add(startBTN, BorderLayout.CENTER);
        this.add(startPanel);
    */

        super(width, height, engine, gui);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Use BoxLayout for vertical stacking

        // Player Selection Panel
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout());

        JLabel playerText = new JLabel("Players");
        ButtonGroup playerGroup = new ButtonGroup();
        final int[] players = {2};
        final boolean[] playerChoosed = {true};

        JRadioButton twoPlayerBtn = new JRadioButton("2");
        twoPlayerBtn.addActionListener(e -> {
            players[0] = 2;
            playerChoosed[0] = true;
        });
        JRadioButton threePlayerBtn = new JRadioButton("3");
        threePlayerBtn.addActionListener(e -> {
            players[0] = 3;
            playerChoosed[0] = true;
        });

        playerGroup.add(twoPlayerBtn);
        playerGroup.add(threePlayerBtn);
        twoPlayerBtn.setSelected(true);

        playerPanel.add(playerText);
        playerPanel.add(twoPlayerBtn);
        playerPanel.add(threePlayerBtn);

        // Monster Selection Panel (Similar Structure)
        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new FlowLayout());

        JLabel monsterText = new JLabel("Monsters");
        ButtonGroup monsterGroup = new ButtonGroup();
        final int[] monsterNumber = {2};
        final boolean[] monsterChoosed = {true};

        JRadioButton twoMonsterBtn = new JRadioButton("2");
        twoMonsterBtn.addActionListener(e -> {
            monsterNumber[0] = 2;
            monsterChoosed[0] = true;
        });
        JRadioButton threeMonsterBtn = new JRadioButton("3");
        threeMonsterBtn.addActionListener(e -> {
            monsterNumber[0] = 3;
            monsterChoosed[0] = true;
        });

        monsterGroup.add(twoMonsterBtn);
        monsterGroup.add(threeMonsterBtn);
        twoMonsterBtn.setSelected(true);

        monsterPanel.add(monsterText);
        monsterPanel.add(twoMonsterBtn);
        monsterPanel.add(threeMonsterBtn);

        // Map Selection Panel
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new FlowLayout());

        JLabel mapLabel = new JLabel("Select Map");
        ButtonGroup maps = new ButtonGroup();
        int[] level = {1};

        JRadioButton map1 = new JRadioButton("Map 1", true);
        map1.addActionListener(e -> level[0] = 1);
        JRadioButton map2 = new JRadioButton("Map 2", false);
        map2.addActionListener(e -> level[0] = 2);
        JRadioButton map3 = new JRadioButton("Map 3", false);
        map3.addActionListener(e -> level[0] = 3);

        maps.add(map1);
        maps.add(map2);
        maps.add(map3);

        mapPanel.add(mapLabel);
        mapPanel.add(map1);
        mapPanel.add(map2);
        mapPanel.add(map3);

        // Start Button Panel
        JPanel startPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        startBtn.setSize(50, 30);
      //  startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startBtn.addActionListener(e -> {
            if (playerChoosed[0] && monsterChoosed[0]) {
                engine.finishedCharSelect(players[0], monsterNumber[0], level[0]);
                gui.startGame();
            }
        });
        startPanel.add(startBtn);

        // Add Panels to Main Panel (this)
        this.add(playerPanel);
        this.add(monsterPanel);
        this.add(mapPanel);
        this.add(startPanel);
    }

}
