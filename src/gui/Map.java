package gui;

import javax.swing.*;
import java.awt.*;

public class Map {

    private JFrame map, options;
    private SpringLayout layout;
    private JPanel optionsPanel;
    private JButton connectButton, disconnectButton,ipButton;


     public void gameInitialization(){
         map = new JFrame("Game title");
         map.setLayout(new SpringLayout());
         map.setSize(new Dimension(800,800));
         map.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         map.setLocationRelativeTo(null);

         map.setVisible(true);
     }

     public void optionInitialization(){
         options = new JFrame("Game options");
         options.setSize(new Dimension(300,600));
         options.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         options.setLocation(map.getX()+ map.getWidth(), map.getY()); //places options besides the game frame
         layout = new SpringLayout();
         optionsPanel = new JPanel(layout);

         connectButton = new JButton("Connect");
         connectButton.setEnabled(false);
         disconnectButton = new JButton("Disconnect");
         disconnectButton.setEnabled(false);
         ipButton = new JButton("Search");

         options.setVisible(true);

     }
}
