package mq;

public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        MqClient mqClient=new MqClient();
        System.out.println("consumed message is: "+mqClient.consume());
    }
}
