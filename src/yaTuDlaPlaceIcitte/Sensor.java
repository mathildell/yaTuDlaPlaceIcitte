package yaTuDlaPlaceIcitte;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Sensor implements Runnable {
	
    private long newTime = System.currentTimeMillis();
    private long lastTime = newTime;
    private String str;
    private String lastTimeString;
    private Random rand = new Random();
    private LinkedBlockingQueue<String> messages;
    public int randomInt = 0;
    private AtomicBoolean mustQuit;
    private int id;
    private int range;
    private int rate;
    
    //On créer l'objet avec les variables envoyées par le Main
    public Sensor(AtomicBoolean mustQuit, int i, int range, int rate) {    
		this.messages = Main.messages;                                                             
    	this.mustQuit = mustQuit;                                                                                                
    	this.id = i;                                                                                                             
    	this.range = range;                                                                                                             
    	this.rate = rate;                                              
    }  
    
    public void run() {
    	//Tant que le processus doit être éxécuté
    	while(!mustQuit.get()){
    		//On regarde le temps actuel
    		newTime = System.currentTimeMillis();
    
    		//Si le temps écoulé depuis le dernier envoi de données est supérieur ou égal au rate choisi
    		if(newTime - lastTime >= rate){
    			//On met à jours la variable de dernier envoi
    			lastTime = newTime;
    			//On créé "l'ID" contenant les infos requises
    			str = createString();
    			//On la fait passer par le premier canal
    			str = filter1(str);
    			//On vérifie que l'information retournée est nouvelle
    			boolean isNew = filter2(str);
    			//Si elle est nouvelle
    			if(isNew){
        			//On ajoute son message à la liste de messages à traiter dans Printer
    				messages.add(str);
    			}
    		}
    	}
    }
    
    public String createString(){
    	//Un numéro au hasard pour faire varier l'approche d'une voiture
		randomInt = rand.nextInt(2);
		//Constitution d'un ID avec le numéro de la place de parking (thread), le range, et si la place est occupée ou non
    	String string = id + ":R" + range + "_P" + randomInt + ":OPT";
    	return string;
    }
    
    public static String filter1(String string){
    	//On commence par regarder où se trouve l'élément de repère de l'id, P, et on offet d'1 pour trouver la position du caractère suivant
    	int position = string.indexOf("P") + 1;
    	//On va chercher le caractère après P et on l'isole dans un substring
    	String character = String.valueOf(string.substring(position, position + 1));
    	//Si le caractère est égal à 1, une voiture est garée, sinon la place est libre
        boolean hasCarParked = (Integer.parseInt(character) == 1) ? true : false;
       
    	if(hasCarParked){
    		//Si la place est libre, on retourne DÉTECTÉ
        	return "DÉTECTÉ (" + string + ")";
    	}else{
    		//Sinon, on retourne N/A
        	return "N/A (" + string + ")";
    	}
    }
    
    public boolean filter2(String string){
    	//On vérifie que ce n'est pas la première itération de la boucle
    	if(lastTimeString != null){
    		//Si le message envoyé la dernière fois est le même message que maintenant
    		//On renvoit "false"
    		if(lastTimeString.equals(string)){
    			return false;
    		}else{
        		//Sinon, on set la variable de comparaison à la bonne valeur et on renvoit true
	    		lastTimeString = string;
	        	return true;
    		}
    	}else{
    		lastTimeString = string;
        	return true;
    	}
    }
    
}
