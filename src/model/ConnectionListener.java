package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ConnectionListener{

    private MulticastSocket multicastSocket;
    private DatagramPacket datagramPacket;
    private byte[] message;
    private boolean listening, connected;

    public ConnectionListener(){
        try{
            InetAddress group = InetAddress.getByName("228.5.6.7");
            multicastSocket = new MulticastSocket(4446);
            multicastSocket.joinGroup(group);
            connected = false;
        }catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        listening = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initate(){
        try{
            message = new byte[512];
            datagramPacket = new DatagramPacket(message,message.length);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void listen(){
        try{
            multicastSocket.receive(datagramPacket);
            message = datagramPacket.getData();
            System.out.println("Listening.");
        } catch (Exception e){
            e.printStackTrace();
        }

        switch (message[0]) {
            case 1:
                if (!connected) {
                    System.out.println("Connected.");
                    Game.getGame().joinGame(message[1]);
                    connected = true;
                    ConnectionHandler.get().askForOpponent(); //????????
                } else { //if you are already connected, an enemy gets spawned instead
                    Game.getGame().addEnemy(message[1]);
                }
                Game.getGame().repaint();
            case 2:
                if(message[1] == Game.getGame().getMe().getId()){
                    Game.getGame().leaveGame();
                    System.out.println("Disconnected.");
                    close();
                }
                Game.getGame().repaint();
                /* EVENTUALLY MORE CASES/*

                x
                x
                x
                x

                 */
            case 3:
                System.out.println("You have won. Congratulations!");
            case 8:
                if(message[1] != Game.getGame().getMe().getId()){
                    Game.getGame().addEnemy(message[1], message[2] * 10, message[3] * 10);
                    Game.getGame().repaint();

                    ConnectionHandler.get().opponentReceived();
            }
        }
    }
    private void close(){
        listening = false;
        ConnectionHandler.get().close();
    }


}
