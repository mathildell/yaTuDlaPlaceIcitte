package yaTuDlaPlaceIcitte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console implements Runnable {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String input;
    
    public void run() {
    	while(true){
    		try {
    			input = br.readLine();
    			//Si l'utilisateur input "q" et que la variable mustQuit est fausse
    	        if ("q".equals(input) && Main.mustQuit.get() == false) {
    	        	//On arrête le process
    	            Main.mustQuit.set(true);
    	        	System.out.println("========================= Process stopped =========================");
    	        }
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
}
