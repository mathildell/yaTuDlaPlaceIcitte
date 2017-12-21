package yaTuDlaPlaceIcitte;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {


    public static void main(String args[]) {
        ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<String>();
        
        Thread Sensor1 = new Thread(new Sensor(messages));
    	Sensor1.start();
    }
    
}
