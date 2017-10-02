package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionSender {


    private Socket socket;
    private DataOutputStream output;
    private byte[] message;

    public ConnectionSender(Socket socket){


    }

    private void initiate(){
        try{
            message = new byte[512];
            output = new DataOutputStream(socket.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        message[0] = 1;
        message[1] = (byte) Game.getGame().getMe().getId();
        try {
            output.write(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void movePlayer(int x, int y){
        message[0] = 2;
        message[1] = (byte) Game.getGame().getMe().getId();
        message[2] = (byte) x;
        message[3] = (byte) y;

        try {
            output.write(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void askForOpponent(){
        message[0] = 7;
        try {
            output.write(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void opponentReceived(){
        message[0] = 8;
        try {
            output.write(message);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
