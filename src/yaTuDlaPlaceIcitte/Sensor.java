package yaTuDlaPlaceIcitte;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;



public class Sensor implements Runnable {
    private AtomicBoolean mustQuit = new AtomicBoolean(false);
    private long newTime = System.currentTimeMillis();
    private long lastTime = newTime;
    private int rate = 1000; //1s
    private String str;
    private String lastTimeString;
    private Random rand = new Random();
    public int randomInt = 0;
    

    ConcurrentLinkedQueue<String> messages;
    Sensor(ConcurrentLinkedQueue<String> messages){
       this.messages = messages;
    }
    
    public void run() {
    	while(mustQuit.get() == false){
    		newTime = System.currentTimeMillis();
    		
    		this.randomInt = rand.nextInt(2);
	        
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
    	String string = "ID:Rxxx_P" + this.randomInt + ":OPT";
    	return string;
    }
    
    public static String filter1(String string){
    	int position = string.indexOf("P") + 1;
    	String character = String.valueOf(string.substring(position, position + 1));
        boolean hasCarParked = (Integer.parseInt(character) == 1) ? true : false;
       
    	if(hasCarParked){
        	return "DÉTECTÉ";
    	}else{
        	return "N/A";
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
    
    public static void main(String args[]) {
    }
}
