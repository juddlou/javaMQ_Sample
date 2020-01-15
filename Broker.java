package mq;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private static ArrayBlockingQueue<String> array=new ArrayBlockingQueue<>(3);

    public static void produce(String msg){
        if(array.offer(msg)){
            System.out.println("produce message successfully, "+msg+" has been produced");
        }else{
            System.out.println("queue is full, please wait");
        }
        System.out.println("--------------------------");
    }

    public static String consume(){
        String msg=array.poll();
        if(msg!=null){
            System.out.println("message has been consumed, it is "+msg+", current queue size is "+array.size());
        }else{
            System.out.println("no message can be consumed");
        }
        return msg;
    }
}
