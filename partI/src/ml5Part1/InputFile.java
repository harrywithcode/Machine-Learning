package ml5Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class InputFile{	
	
	public BufferedReader brTest = null;
	public String text;
	public String[] originalAttributes;
	public String targetAttribute;
	
	public String[] readOriginalAttributes(String address){
		
		try{
			brTest = new BufferedReader(new FileReader(address));
			text = brTest.readLine();
			originalAttributes = text.split("\\t");
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
		return originalAttributes;
	}
	
	public double[][] readDataInstances(String address){
		
		int column = 0;
		
		List<String[]> list = new ArrayList<String[]>();
		String[] eachrow = null ;
		try{
			brTest = new BufferedReader(new FileReader(address));
			
			while(text != null){
				text = brTest.readLine();
				
				if ((text !=null) && (text.trim().length() !=0)){
					eachrow = text.split("	");
					column = eachrow.length;
					list.add(eachrow);
					
				}
				
			}
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file 1111111");
		}
		
		
		String[][]dataInstances = new String[list.size()-1][column];
		double Instances[][] = new double[dataInstances.length][dataInstances[0].length];
		for(int i = 0; i < list.size()-1; i++){
			for(int j = 0; j < column;j++){
				//dataInstances[i][j] = list.get(i+1)[j];
				Instances[i][j]= Double.parseDouble(list.get(i+1)[j]);
			}
		}
		
		//int Instances[][] = new int[dataInstances.length][dataInstances[0].length];
		//for(int i = 0; i < dataInstances.length; i++){
			//for(int j = 0; j < dataInstances[0].length; j++){
				//Instances[i][j]= Integer.valueOf(dataInstances[i][j]).intValue();
			//}
		//}
		return Instances;
	}	
}
