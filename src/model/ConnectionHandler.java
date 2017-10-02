package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ConnectionHandler {
    private static ConnectionHandler connectionHandler = new ConnectionHandler();
    private ArrayList<ServiceDescription> serviceDescriptions = new ArrayList<ServiceDescription>();

    private ConnectionListener connectionListener;
    private ConnectionSender connectionSender;

    private Socket socket;
    private DataOutputStream output;
    private byte[] message;

    public ConnectionHandler(){

    }

    public void initiateConnection(){
        message = new byte[4];

        ServiceBrowser serviceBrowser = new ServiceBrowser("JavaGameServer");
        serviceBrowser.startBrowser();
    }

    public void connect(int index){
        try{
            connectionListener = new ConnectionListener();

            String hostname = serviceDescriptions.get(index).getAddress().getHostAddress();
            int port = serviceDescriptions.get(index).getPort();

            socket = new Socket(hostname,port);
            output = new DataOutputStream(socket.getOutputStream());

            message[0] = 0;
            output.write(message);

            System.out.println("Connecting...");

            connectionSender = new ConnectionSender(socket);

        } catch (IOException e){
            e.printStackTrace();
        }
            }

    public void disconnect(){
        ConnectionSender.disconnect();
    }

    public void movePlayer(int x, int y){
        connectionSender.movePlayer(x,y);
    }

    public void close(){
        connectionSender = null;
        connectionListener = null;

        try{
            socket.close();
            socket = null;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void askForOpponent(){
        connectionSender.askForOpponent();
    }

    public void opponentReceived(){
        connectionSender.opponentReceived();
    }

    public String getInetAddress(String url) {
        String ip = "";
        try {
            InetAddress[] ipAddress = InetAddress.getAllByName(new URL("http://" + url).getHost());
            InetAddress[] ipV6Address = Inet6Address.getAllByName(url);

            for (InetAddress addr : ipAddress) {
                if (addr instanceof Inet6Address) {
                    ip = addr.getHostAddress();
                    return ip;
                }
            }

            ip = ipAddress[0].getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ip;
    }


    public void addServiceDescription(ServiceDescription serviceDescription){
        for(ServiceDescription description : serviceDescriptions){
            if(serviceDescription.getInstanceName().equals(description.getInstanceName())){
                return;
            }
        }
        serviceDescriptions.add(serviceDescription);
    }

    public void updateServerList(){
        String[] servers = new String[serviceDescriptions.size()];
        int i = 0;

        for (ServiceDescription description : serviceDescriptions){
            servers[i] = description.getInstanceName();
            i++;
        }
        Game.getGame().getMap().updateServerList(servers);
    }

}
