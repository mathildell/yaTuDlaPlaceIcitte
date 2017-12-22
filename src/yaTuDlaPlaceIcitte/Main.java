package yaTuDlaPlaceIcitte;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	
    public static ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<String>();

	//public static InputStreamReader input = new InputStreamReader(System.in);
    //System.out.println(input);

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    
	private static Printer printer = new Printer();
    private static AtomicBoolean mustQuit = new AtomicBoolean(false);
	private static List<Object> sensors = new ArrayList<Object>();
    private static int range = 5000;
    private static int rate = 1000; //1s


    public static void main(String args[]) throws IOException {

        for(int i=0; i < 2; i++){
             Sensor tmp = new Sensor(mustQuit.get(), i, range, rate);
             sensors.add(tmp);
             (new Thread(tmp)).start();
             tmp = null;
        }

        while(!mustQuit.get()){
        	printer.update();
        }
        
        /*
        for (Iterator<Object> i = sensors.iterator(); i.hasNext();) {
            Object item = i.next();
            if(item instanceof Object){
            }
            System.out.println(item);
        }
        */
        
    }
    
}
