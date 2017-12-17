package utils.com.thoughtworks;

import java.util.HashMap;

import service.com.thoughtworks.InterGalacticUnitsConversonService;

import data.com.thoughtworks.AlienDao;
import exception.com.thoughtworks.InvalidInputException;

public class CurrencyUtils {
public HashMap<String, Integer> RomanToNumeralsMap;
public CurrencyUtils(){
	RomanToNumeralsMap = new HashMap<String, Integer>();
	populateRomanMap();
}
public void populateRomanMap(){
	RomanToNumeralsMap.put("I",1);
	RomanToNumeralsMap.put("V",5);
	RomanToNumeralsMap.put("X",10);
	RomanToNumeralsMap.put("L",50);
	RomanToNumeralsMap.put("C",100);
	RomanToNumeralsMap.put("D",500);
	RomanToNumeralsMap.put("M",1000);
}
public int convertRomanToNumerals(String romanString){
	int value = 0;
	String[] tokens = romanString.split(" ");
	int len = tokens.length;
	int prev = 0;
	int curr = 0;
	for(int i=len-1;i>=0;i--){
		curr = RomanToNumeralsMap.get(tokens[i]);
		if(curr < prev){
			curr = -curr;
			prev = 0;
		}else
			prev = curr;
		value=value+curr;
	}
	return value;
}

public double convertAlienToNumeric(AlienDao alienDao, String request) throws InvalidInputException{
	String romanValue = convertAlienToRoman(alienDao, request);
	return convertRomanToNumerals(romanValue);
}
public String convertAlienToRoman(AlienDao alienDao, String alienRequest) throws InvalidInputException{
	String[] tokens = alienRequest.split(" ");
	int len = tokens.length;
	StringBuffer romanStr = new StringBuffer();
	for(int i=0;i<len;i++){
		romanStr.append(alienDao.getRomanFromAlien(tokens[i])+" ");
	}
	if(romanStr.toString().contains("null"))
		throw new InvalidInputException(InterGalacticUnitsConversonService.errorMessage);
	return romanStr.toString();
}
}
