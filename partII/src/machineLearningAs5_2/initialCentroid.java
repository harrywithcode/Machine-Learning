package machineLearningAs5_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class initialCentroid {
	public BufferedReader brTest = null;
	public String text;
	public String[] centroid;
	
	
	public String[] readCentroid(String address){
		String[] eachrow = null ;
		List<String[]> list = new ArrayList<String[]>();
		try{
			brTest = new BufferedReader(new FileReader(address));
			text = "initialize";
			while(text != null){
				text = brTest.readLine();
				
				if ((text !=null) && (text.trim().length() !=0)){
					eachrow = text.split(",");
					list.add(eachrow);
					
				}
				
			}
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file 1111111");
		}
		String[] result = new String[list.size() * list.get(0).length];
		int k = 0;
		for(int i = 0; i < list.size(); i++){
			for(int j = 0; j < list.get(0).length; j++){
				result[k++] = list.get(i)[j];
				//System.out.println(result[k-1]);
			}
		}
		return result;
	}
}
