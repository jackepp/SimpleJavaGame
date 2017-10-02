package gui;

import model.ServiceDescription;

import javax.swing.*;
import java.awt.*;

public class Map {

    private JFrame map, options;
    private SpringLayout layout;
    private JPanel optionsPanel;
    private JButton connectButton, disconnectButton,ipButton;
    private JLabel ipLabel;
    private DefaultListModel listModel;
    private JList<ServiceDescription> servers;
    private JTextField inputAddress;
    private boolean connected;
    private Painter painter;

    public Map(){
        connected = false;
        gameInitialization();
        optionInitialization();

    }

     public void gameInitialization(){
         map = new JFrame("Game title");
         map.setLayout(new SpringLayout());
         map.setSize(new Dimension(800,800));
         map.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         map.setLocationRelativeTo(null);

         painter = new Painter();
         map.add(painter);

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
         ipLabel = new JLabel("IP-address will be shown here.");


         listModel = new DefaultListModel();
         servers = new JList<>(listModel);
         servers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
         servers.setLayoutOrientation(JList.VERTICAL);
         servers.setVisibleRowCount(-1);
         servers.setPreferredSize(new Dimension(250,200));

         inputAddress = new JTextField();
         inputAddress.setColumns(15);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, servers, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, servers, 20, SpringLayout.NORTH, optionsPanel);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, connectButton, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, connectButton, 20, SpringLayout.SOUTH, servers);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, disconnectButton, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, disconnectButton, 40, SpringLayout.NORTH, connectButton);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, inputAddress, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, inputAddress, 100, SpringLayout.NORTH, disconnectButton);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ipLabel, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, ipLabel, 30, SpringLayout.NORTH, inputAddress);

         layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, ipButton, 0, SpringLayout.HORIZONTAL_CENTER, optionsPanel);
         layout.putConstraint(SpringLayout.NORTH, ipButton, 30, SpringLayout.NORTH, ipLabel);

         optionsPanel.add(connectButton);
         optionsPanel.add(disconnectButton);
         optionsPanel.add(ipButton);
         optionsPanel.add(ipLabel);
         optionsPanel.add(servers);

         options.add(optionsPanel);
         options.setVisible(true);
     }


     public void updateButtons(){
         if(connected){
             connectButton.setEnabled(false);
             disconnectButton.setEnabled(true);
         } else {
             connectButton.setEnabled(true);
             disconnectButton.setEnabled(false);
         }
        connected = !connected;
    }

    public void updateServerList(String [] serverNames){
        listModel.clear();
        for(String name : serverNames){
            listModel.addElement(name);
        }
    }

    public void actionListeners(){
        //HEJ
    }

    public void repaint(){
        painter.repaint();
    }
}
