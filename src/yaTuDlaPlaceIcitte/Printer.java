package yaTuDlaPlaceIcitte;
import java.util.concurrent.LinkedBlockingQueue;

public class Printer {
	private LinkedBlockingQueue<String> messages;
	
	Printer(){
		this.messages = Main.messages;
	}

    public void update() {
    	if(!messages.isEmpty()){
    		while(messages.size() > 0){
    			String str = messages.poll();
        	    System.out.println(str);
    		}
    		/*
        	for (Iterator<String> msg = messages.iterator(); msg.hasNext();) {
        	    String str = msg.next();
        	    System.out.println(str);
        	}
        	messages.clear();
        	*/
    	}
    }
}
