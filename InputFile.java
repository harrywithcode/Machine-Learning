package utd.ml.id3;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputFile{	
	
	public BufferedReader brTest = null;
	public String text;
	public String[] attributes;
	public String[] originalAttributes;
	public String targetAttribute;
	
	
	public String[] readOriginalAttributes(String address){
		
		try{
			brTest = new BufferedReader(new FileReader(address));
			text = brTest.readLine();
			originalAttributes = text.split("\\t");
		    //System.out.println("Firstline is : " + Arrays.asList(attributes));
			// DEBUG ===============================================		
			if (GlobalClass.getDebugMode())
			{
				System.out.println("[Debug] readOriginalAttributes() - originalAttributes: " );
				for (int i=0; i < originalAttributes.length; i++)
					System.out.println(originalAttributes[i] + "\t");
				System.out.println("\n");
			}
			// DEBUG ===============================================		
			
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
		return originalAttributes;
	}

	
	public ArrayList<String> readAttributes(String address){
		
		List<String> temp = new ArrayList<String>();
		ArrayList<String> attributeList = new ArrayList<String>();
		
		try{
			brTest = new BufferedReader(new FileReader(address));
			text = brTest.readLine();
			attributes = text.split("	");
		    //System.out.println("Firstline is : " + Arrays.asList(attributes));
			temp = Arrays.asList(attributes);
			for(int i = 0; i < attributes.length-1;i++){
				attributeList.add(temp.get(i));
			}
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
		return attributeList;
	}

	
	public String readTargetAttributes(String address){

		try{
			brTest = new BufferedReader(new FileReader(address));
			text = brTest .readLine();
			attributes = text.split("	");
			targetAttribute = attributes[attributes.length-1];
		    //System.out.println("target attribute is : " + targetAttribute);
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
		return targetAttribute;
	}
	

	
	public String[][] readDataInstances(String address){
		
		int column = attributes.length;
		int checkRow = 0;
		List<String[]> list = new ArrayList<String[]>();
		String[] eachrow = null ;
		try{
			brTest = new BufferedReader(new FileReader(address));
			
			while(text != null){
				text = brTest.readLine();
				
				if ((text !=null) && (text.trim().length() !=0))
				{
					eachrow = text.split("	");
					list.add(eachrow);
					checkRow++;
				}
				// DEBUG ===============================================		
				if (GlobalClass.getDebugMode())
				{
					System.out.println("[Debug] readDataInstances() - text: " + text);
					System.out.println("[Debug] readDataInstances() - # of rows: " + checkRow);					
				}
				// DEBUG ===============================================		

				
			}
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file 1111111");
		}
		
		//check part
		if(list.size() != checkRow || eachrow.length != attributes.length){
			System.out.println("error");
		}
		else{
			//System.out.println("load correctly");
			;
		}
		//check end
		
		String[][]dataInstances = new String[list.size()-1][column];
		for(int i = 0; i < list.size()-1; i++){
			for(int j = 0; j < column;j++){
				dataInstances[i][j] = list.get(i+1)[j];
				
			}
		}
		
		
		return dataInstances;
	}
	
	
	
}
