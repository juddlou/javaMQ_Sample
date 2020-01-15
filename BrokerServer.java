package mq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable {
    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
            while(true){
                String msg=(String)objectInputStream.readObject();
                if(msg==null){
                    continue;
                }else if(msg.equals("produce")){
                    Broker.produce(msg);
                }else if(msg.equals("consume")){
                    String consume_msg=Broker.consume();
                    ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(consume_msg);
                    System.out.println(consume_msg+" has been consumed");
                }else{
                    System.out.println("no service");
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        while(true){
            BrokerServer brokerServer= null;
            try {
                brokerServer = new BrokerServer(serverSocket.accept());
            } catch (IOException e) {
                //e.printStackTrace();
            }
            new Thread(brokerServer).start();
        }
    }
}
