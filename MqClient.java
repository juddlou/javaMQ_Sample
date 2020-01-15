package mq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MqClient {
    public static void produce(String message) throws IOException {
        Socket socket=new Socket("127.0.0.1",9999);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
    }

    public static String consume() throws Exception {
        Socket socket=new Socket("127.0.0.1",9999);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject("consume");
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        String msg=(String) objectInputStream.readObject();
        return msg;
    }
}
