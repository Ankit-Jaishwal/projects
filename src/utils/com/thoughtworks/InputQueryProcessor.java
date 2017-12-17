package utils.com.thoughtworks;

public class InputQueryProcessor {
public InputQueryProcessor(){
	super();
}
public boolean isAlienInfo(String request){
	String[] tokens = request.split(" ");
	if(tokens.length == 3 && request.toLowerCase().contains("is") && !request.contains("?"))
		return true;
	return false;
}
public boolean isMetalsInfo(String request){
	if(!request.contains("?") && request.toLowerCase().contains("credits"))
		return true;
	return false;
}
public boolean isQueryForAlienCredits(String request){
	if(request.contains("?") && request.toLowerCase().contains("credits"))
		return true;
	return false;
}
public boolean isAlienToNumericConversionQuery(String request){
	if(request.contains("?") && request.toLowerCase().contains("how much"))
		return true;
	return false;
}
}
