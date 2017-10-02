package gui;

import model.Game;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class Painter extends JPanel {

    public Painter(){
        super();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i= 0; i < Game.getGame().getPlayers().size();i++){
            Player tempPlayer = Game.getGame().getPlayers().get(i);
            if(tempPlayer.getMe()){
                g.setColor(Color.GREEN);
                g.fillOval(tempPlayer.getX(),tempPlayer.getY(), 30, 30);
            } else{
                g.setColor(Color.RED);
                g.fillOval(tempPlayer.getX(),tempPlayer.getY(),30,30);
            }
        }
    }
    public Dimension getPrefferedSize(){
        return new Dimension(800,800);
    }
}
