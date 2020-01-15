package mq;

import java.io.IOException;

public class ProduceClient {
    public static void main(String[] args) throws IOException {
        MqClient mqClient=new MqClient();
        mqClient.produce("produce");
    }
}

