package data.com.thoughtworks;

import java.util.HashMap;

public class AlienDao {
public HashMap<String,String> alienCurrencyMap;
public AlienDao(){
	alienCurrencyMap = new HashMap<String,String>();
}
public void addAlienCurrencyEntry(String alienInput){
	String[] tokens = alienInput.split(" ");
	String alien = tokens[0];
	String roman = tokens[2];
	addAlienCurrencyEntry(alien, roman);
}
public void addAlienCurrencyEntry(String alien, String roman){
	alienCurrencyMap.put(alien, roman);
}
public String getRomanFromAlien(String alienStr){
	return alienCurrencyMap.get(alienStr);
}
}
