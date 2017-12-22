package yaTuDlaPlaceIcitte;

import java.util.concurrent.LinkedBlockingQueue;

public class Printer {
	
	private LinkedBlockingQueue<String> messages;
	
	Printer(){
		this.messages = Main.messages;
	}

    public void update() {
    	//tant qu'il y a des messages à traiter
    	if(!messages.isEmpty()){
    		while(messages.size() > 0){
    			//On sélectione le premier message de la liste
    			//On le supprime de la liste et le stock dans une variable
    			String str = messages.poll();
    			//On affiche la variable à l'utilisateur
        	    System.out.println(str);
    		}
    	}
    }
}
