package model;

import javax.xml.ws.Service;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class ServiceBrowser {


    private Thread thread;
    private boolean isRunning;

    private String serviceName;

    private MulticastSocket multicastSocket;
    private DatagramPacket receiveDatagramPacket, requestDatagramPacket;

    private int port;
    private InetAddress group;

    public ServiceBrowser(String serviceName){
        this.serviceName = serviceName;
        try{
            group = InetAddress.getByName("239.255.255.250");
            port = 1900;
            isRunning = false;

            multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startBrowser(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();

        requestDatagramPacket = getDatagramRequestPacket();
        sendDatagramPacket();
    }

    public void run(){
        while (isRunning){
            try{
                byte[] datagramPacket = new byte[1024];
                receiveDatagramPacket = new DatagramPacket(datagramPacket,datagramPacket.length);
                multicastSocket.receive(receiveDatagramPacket);
                if(checkReceiveDatagramPacket()){
                    ServiceDescription descriptor = getReplyDescriptor();
                    if(descriptor != null){
                        ConnectionHandler.get().addServiceDescription(descriptor);
                        ConnectionHandler.get().updateServerList();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private boolean checkReceiveDatagramPacket(){
        if(receiveDatagramPacket == null){
            return false;
        } else{
            String data = new String(receiveDatagramPacket.getData());
            return data.startsWith("SERVICE REPLY"+ serviceName);
        }
    }

    private ServiceDescription getReplyDescriptor(){
        String dataString = new String(receiveDatagramPacket.getData());
        System.out.println(dataString);

        String[] data= dataString.split(" ");
        for(int i = 0; i < dataString.length();i++){
            System.out.println(data[i]);
        }

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setInstanceName(data[3]);
        try{
            serviceDescription.setAddress(InetAddress.getByName(data[4]));
        } catch (UnknownHostException e){
            e.printStackTrace();
        }
        serviceDescription.setPort(Integer.parseUnsignedInt(data[5]));
        return serviceDescription;
    }

    private void sendDatagramPacket(){
        if(requestDatagramPacket != null){
            try{
                multicastSocket.send(requestDatagramPacket);
                requestDatagramPacket = null;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private DatagramPacket getDatagramRequestPacket(){
        StringBuffer buf = new StringBuffer();

        try{
            buf.append("SERVICE QUERY " + serviceName);
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }

        byte[] bytes = buf.toString().getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);

        datagramPacket.setAddress(group);
        datagramPacket.setPort(port);
        return datagramPacket;
    }




}
