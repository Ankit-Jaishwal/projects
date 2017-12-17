package service.com.thoughtworks;

import utils.com.thoughtworks.CurrencyUtils;
import utils.com.thoughtworks.InputQueryProcessor;
import data.com.thoughtworks.AlienDao;
import data.com.thoughtworks.MetalsDao;
import exception.com.thoughtworks.InvalidInputException;

public class InterGalacticUnitsConversonService {
	AlienDao alienDao;
	MetalsDao metalsDao;
	InputQueryProcessor inputQueryProcessor;
	CurrencyUtils currencyUtils;
	public static String errorMessage = "I have no idea what you are talking about";
	
	public InterGalacticUnitsConversonService(){
		alienDao = new AlienDao();
		metalsDao = new MetalsDao();
		inputQueryProcessor = new InputQueryProcessor();
		currencyUtils = new CurrencyUtils();
	}
	
	public void processRequest(String request) throws InvalidInputException{
		if(inputQueryProcessor.isAlienInfo(request)){
			alienDao.addAlienCurrencyEntry(request);
		}else if(inputQueryProcessor.isMetalsInfo(request)){
			handleMetalCreditInputRequest(request);
		}
		else if(inputQueryProcessor.isAlienToNumericConversionQuery(request)){
			int value = handleAlienToNumericConvQuery(request);
			String alienStr = separateAlienStringFromNumericQuery(request);
			System.out.println(alienStr+" is "+value);
		}
		else if(inputQueryProcessor.isQueryForAlienCredits(request)){
			double value = handleAlienToCreditsConvQuery(request);
			String alienStr = separateAlienStringFromCreditsQuery(request);
			String metalName = getMetalFromAlienStringCreditsQuery(request);
			System.out.println(alienStr+" "+metalName +" is "+value);
		}
		else
			throw new InvalidInputException(errorMessage);
	}
	public void handleMetalCreditInputRequest(String request) throws InvalidInputException{
		String alienStr = separateAlienStringFromCreditsInputRequest(request);
		String romanStr = currencyUtils.convertAlienToRoman(alienDao, alienStr);
		double multiplier = currencyUtils.convertRomanToNumerals(romanStr);
		String[] tokens = request.split(" ");
		int len = tokens.length;
		Double noOfCredits = Double.parseDouble(tokens[len-2]);
		String metalName = tokens[len-4];
		metalsDao.addMetalCreditEntry(metalName, noOfCredits/multiplier);
	}
	private String separateAlienStringFromCreditsInputRequest(String alienRequest){
		String[] tokens = alienRequest.split(" ");
		int len = tokens.length;
		StringBuffer romanStr = new StringBuffer();
		for(int i=0;i<len-4;i++){
			romanStr.append(tokens[i]+" ");
		}
		return romanStr.toString();
	}
	private int handleAlienToNumericConvQuery(String request) throws InvalidInputException{
		String alienStr = separateAlienStringFromNumericQuery(request);
		String romanStr = currencyUtils.convertAlienToRoman(alienDao, alienStr);
		return currencyUtils.convertRomanToNumerals(romanStr);
	}
	
	private String separateAlienStringFromNumericQuery(String alienRequest){
		String[] tokens = alienRequest.split(" ");
		int len = tokens.length;
		StringBuffer romanStr = new StringBuffer();
		for(int i=3;i<len-1;i++){
			romanStr.append(tokens[i]+" ");
		}
		return romanStr.toString();
	}
	
	private double handleAlienToCreditsConvQuery(String request) throws InvalidInputException{
		String alienStr = separateAlienStringFromCreditsQuery(request);
		String metalName = getMetalFromAlienStringCreditsQuery(request);
		if(metalsDao.getMetalValue(metalName) == null)
				throw new InvalidInputException(errorMessage);
		double value = currencyUtils.convertAlienToNumeric(alienDao, alienStr) * 
				metalsDao.getMetalValue(metalName);
		return value;
	}
	private String separateAlienStringFromCreditsQuery(String alienRequest){
		String[] tokens = alienRequest.split(" ");
		int len = tokens.length;
		StringBuffer romanStr = new StringBuffer();
		for(int i=4;i<len-2;i++){
			romanStr.append(tokens[i]+" ");
		}
		return romanStr.toString();
	}
	private String getMetalFromAlienStringCreditsQuery(String request){
		String[] tokens = request.split(" ");
		int len = tokens.length;
		return tokens[len-2];
	}
}
