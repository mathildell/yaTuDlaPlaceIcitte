package yaTuDlaPlaceIcitte;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Iterator;

public class Printer {
   ConcurrentLinkedQueue<String> messages;
   Printer(ConcurrentLinkedQueue<String> messages){
      this.messages = messages;
   }

    public void update() {
    	for (Iterator<String> msg = messages.iterator(); msg.hasNext();) {
    	    String str = msg.next();
    	    System.out.println(str);
    	}
    	messages.clear();
    }
}
