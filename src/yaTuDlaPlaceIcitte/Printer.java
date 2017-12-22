package yaTuDlaPlaceIcitte;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Iterator;

public class Printer {
	private ConcurrentLinkedQueue<String> messages;
	
	Printer(){
		this.messages = Main.messages;
	}

    public void update() {
    	if(messages != null){
        	for (Iterator<String> msg = messages.iterator(); msg.hasNext();) {
        	    String str = msg.next();
        	    System.out.println(str);
        	}
        	messages.clear();
    	}
    }
}
