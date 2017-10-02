package model;

import com.sun.xml.internal.ws.api.wsdl.parser.ServiceDescriptor;

import java.net.InetAddress;

/**
 * Created by vikto on 2016-10-13.
 */
public class ServiceDescription {
    private String instanceName;
    private InetAddress address;
    private int port;

    public ServiceDescription(){

    }

    public void setInstanceName(String instanceName){
        this.instanceName = instanceName;
    }

    public String getInstanceName(){
        return instanceName;
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getPort(){
        return port;
    }

    public void setAddress(InetAddress address){
        this.address = address;
    }

    public InetAddress getAddress(){
        return address;
    }

    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append(getInstanceName());
        buf.append(" ");
        buf.append(getAddress().getHostAddress());
        buf.append(" ");
        buf.append(getPort());
        return buf.toString();
    }
}
