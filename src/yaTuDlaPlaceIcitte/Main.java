package yaTuDlaPlaceIcitte;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
    public static LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<String>();

    
	private static Printer printer = new Printer();
    public static AtomicBoolean mustQuit = new AtomicBoolean(false);
	private static List<Object> sensors = new ArrayList<Object>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String input;
    private static int range = 5000; 
    private static int rate = 1000; //1s


    public static void main(String args[]) throws IOException {
    	
    	System.out.println("================ Choose range (integer) ================");
		try {
			input = br.readLine();
			range = Integer.parseInt(input);
	    	System.out.println("Range: "+input);
	    	System.out.println("================ Starting process ================");
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        for(int i=0; i < 2; i++){
             Sensor tmp = new Sensor(mustQuit, i, range, rate);
             sensors.add(tmp);
             (new Thread(tmp)).start();
             tmp = null;
        }
        
        (new Thread(new Console())).start();
        
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
