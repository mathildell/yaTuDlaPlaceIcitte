package yaTuDlaPlaceIcitte;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;



public class Sensor implements Runnable {
    private long newTime = System.currentTimeMillis();
    private long lastTime = newTime;
    private String str;
    //private String filter1;
    private String lastTimeString;
    private Random rand = new Random();
    private ConcurrentLinkedQueue<String> messages;
    public int randomInt = 0;

    private boolean mustQuit;
    private int id;
    private int range;
    private int rate;

    public Sensor(boolean mustQuit, int i, int range, int rate) {    
		this.messages = Main.messages;                                                             
    	this.mustQuit = mustQuit;                                                                                                
    	this.id = i;                                                                                                             
    	this.range = range;                                                                                                             
    	this.rate = rate;                                              
    }  
    
    public void run() {
    	while(!mustQuit){
    		newTime = System.currentTimeMillis();
    		
    		randomInt = rand.nextInt(2);
	        
    		if(newTime - lastTime > rate){

        	    
    			lastTime = newTime;
    			
    			str = createString();
    			str = filter1(str);
    			Boolean isNew = filter2(str);
    			
    			if(isNew){
    				messages.add(str);
    			}
    	        
    		}
    	}
    }
    
    public String createString(){
    	String string = id + ":R" + range + "_P" + randomInt + ":OPT";
    	return string;
    }
    
    public static String filter1(String string){
    	int position = string.indexOf("P") + 1;
    	String character = String.valueOf(string.substring(position, position + 1));
        boolean hasCarParked = (Integer.parseInt(character) == 1) ? true : false;
       
    	if(hasCarParked){
        	return "DÉTECTÉ (" + string + ")";
    	}else{
        	return "N/A (" + string + ")";
    	}
    }
    
    public Boolean filter2(String string){
		
	    
    	if(lastTimeString != null){
    		if(lastTimeString == string){
    			return false;
    		}else{
	    		lastTimeString = string;
	        	return true;
    		}
    	}else{
    		lastTimeString = string;
        	return true;
    	}
    }
    
}
