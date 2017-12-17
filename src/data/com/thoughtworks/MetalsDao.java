package data.com.thoughtworks;

import java.util.HashMap;

public class MetalsDao {
	public HashMap<String,Double> MetalsCreditMap;
	public MetalsDao(){
		MetalsCreditMap = new HashMap<String,Double>();
	}
	public void addMetalCreditEntry(String metal, Double value){
		MetalsCreditMap.put(metal, value);
	}
	public void addMetalCreditEntry(String metalInfoRequest){
		String[] tokens = metalInfoRequest.split(" ");
		int len = tokens.length;
		Double noOfCredits = Double.parseDouble(tokens[len-2]);
		String metalName = tokens[len-4];
		addMetalCreditEntry(metalName, noOfCredits);
	}
	public Double getMetalValue(String metalName){
		return MetalsCreditMap.get(metalName);
	}
}
