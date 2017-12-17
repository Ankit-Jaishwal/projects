package main;

import java.util.ArrayList;
import java.util.Scanner;

import exception.com.thoughtworks.InvalidInputException;

import service.com.thoughtworks.InterGalacticUnitsConversonService;

public class GalaxyGuideMain {
public static void main(String args[]){
	InterGalacticUnitsConversonService service = new InterGalacticUnitsConversonService();
	Scanner scanner = new Scanner(System.in);
	ArrayList<String> inputList = new ArrayList<String>();
	String terminatorString = ".";
	while(scanner.hasNext()){
		String input = scanner.nextLine();
		if(!input.equals(".")){
			inputList.add(input);
		}
		else
			break;
	}
	for(int i=0;i<inputList.size();i++){
		try {
			//System.out.println("processing request"+i +" "+inputList.get(i));
			service.processRequest(inputList.get(i));
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
}
