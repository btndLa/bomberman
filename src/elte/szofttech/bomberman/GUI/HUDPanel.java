package elte.szofttech.bomberman.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elte.szofttech.bomberman.model.GameEngine;

public class HUDPanel extends Scene {
  private JLabel timeLabel;
  private JPanel playerPanel;
  private JLabel[] playerLabels;
  private JLabel[] playerPointsLabels;
  private JPanel[] playerPowerupPanels;
  
  public HUDPanel(int width, int heigth, GameEngine engine){
    super(width, heigth, engine);
    setPreferredSize(new Dimension(width, heigth));
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    
    // Time Label
    timeLabel = new JLabel("00:00");
    add(timeLabel, BorderLayout.WEST);
    timeLabel.setPreferredSize(new Dimension(width/4, heigth));

    // Player Panel
    playerPanel = new JPanel();
    playerPanel.setLayout(new GridLayout(1, 3));
    playerPanel.setBorder(new EmptyBorder(10, 30, 10, 10));
    add(playerPanel, BorderLayout.CENTER);

    // Initialize player labels, points labels, and powerup panels
    playerLabels = new JLabel[3];
    playerPointsLabels = new JLabel[3];
    playerPowerupPanels = new JPanel[3];
    for (int i = 0; i < 3; i++) {
        playerLabels[i] = new JLabel("Player " + (i + 1));
        playerPointsLabels[i] = new JLabel("0");
        playerPowerupPanels[i] = new JPanel();
        playerPowerupPanels[i].setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
        playerPowerupPanels[i].setPreferredSize(new Dimension(width/4, 50)); // Adjust size as needed

        // Add player components to player panel
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new BorderLayout());
        playerInfoPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
        playerInfoPanel.add(playerLabels[i], BorderLayout.CENTER);
        playerInfoPanel.add(playerPointsLabels[i], BorderLayout.EAST);
        playerInfoPanel.add(playerPowerupPanels[i], BorderLayout.SOUTH);
        playerPanel.add(playerInfoPanel);
    }
  }

  // Method to update time display
    public void updateTime(String time) {
        timeLabel.setText(time);
    }

    // Method to update player points
    public void updatePlayerPoints(int playerIndex, int points) {
        playerPointsLabels[playerIndex].setText("Points: " + points);
    }

    // Method to add powerup icon to a player
    public void addPowerupToPlayer(int playerIndex, Icon powerupIcon) {
        JLabel powerupLabel = new JLabel(powerupIcon);
        playerPowerupPanels[playerIndex].add(powerupLabel);
    }

    // Method to remove all powerups from a player
    public void clearPowerups(int playerIndex) {
        playerPowerupPanels[playerIndex].removeAll();
    }

}
