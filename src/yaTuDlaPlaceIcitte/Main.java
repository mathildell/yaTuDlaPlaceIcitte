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
    private static int parkSpot = 2; 
    private static int range = 5000; 
    private static int rate = 1000; //1s

    public static void main(String args[]) throws IOException {
    	
    	//On laisse à l'utilisateur le choix des paramètres de config
		try {
	    	System.out.println("================ Number of parking spots (integer) ================");
			input = br.readLine();
			parkSpot = Integer.parseInt(input);
	    	System.out.println("Parking spots: "+input);
	    	System.out.println("====================== Choose range (integer) ======================");
			input = br.readLine();
			range = Integer.parseInt(input);
	    	System.out.println("Range: "+input);
	    	System.out.println("=================== Choose loop rate (int/in ms) ===================");
			input = br.readLine();
			rate = Integer.parseInt(input);
	    	System.out.println("Time: "+input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	System.out.println("========================= Starting process =========================");
		
    	//On créé le nombre approprié de Sensors
        for(int i=0; i < parkSpot; i++){
             Sensor tmp = new Sensor(mustQuit, i, range, rate);
             sensors.add(tmp);
             (new Thread(tmp)).start();
             tmp = null;
        }
        
        //Interface de console, pour pouvoir "quit" le process à tout moment
        (new Thread(new Console())).start();
        
        //tant que le process est en cours, on affiche les messages
        while(!mustQuit.get()){
        	printer.update();
        }   
    }
}
