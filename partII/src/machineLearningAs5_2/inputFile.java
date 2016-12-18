package machineLearningAs5_2;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class inputFile{	
	
	public BufferedReader brTest = null;
	public String text1;
	int countRow = 0;
	
	
	
	public String[][] readDataInstances(String address) {
		
		List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		try{
			brTest = new BufferedReader(new FileReader(address));
			text1 = "Initialize";
			//System.out.println(text1);
			while(text1 != null){
				text1 = brTest.readLine();
				
				if ((text1 !=null) && (text1.trim().length() !=0)){
					countRow++;
					ArrayList<String> eachrow = new ArrayList<String>() ;
					//System.out.println(text1);
					//count++;
					//System.out.println(count);
					///*
					JSONParser parser = new JSONParser();
			        try {     
			            Object obj = parser.parse(text1);

			            JSONObject jsonObject =  (JSONObject) obj;

			            String text = (String) jsonObject.get("text");
			            //System.out.println(text);
			            eachrow.add(text);
			            
			            
			            String profile_image_url = (String) jsonObject.get("profile_image_url");
			            //System.out.println(profile_image_url);
			            eachrow.add(profile_image_url);
			            
			            
			            String from_user = (String) jsonObject.get("from_user");
			            //System.out.println(from_user);
			            eachrow.add(from_user);
			            
			            
			            long from_user_id = (long) jsonObject.get("from_user_id");
			            String strLong = Long.toString(from_user_id);
			            //System.out.println(strLong);
			            eachrow.add(strLong);
			            
			            
			            String geo = (String) jsonObject.get("geo");
			            //System.out.println(geo);
			            eachrow.add(geo);
			            
			            
			            long id = (long) jsonObject.get("id");
			            String strId = Long.toString(id);
			            //System.out.println(id);
			            eachrow.add(strId);
			            
			            
			            String iso_language_code = (String) jsonObject.get("iso_language_code");
			            //System.out.println(iso_language_code);
			            eachrow.add(iso_language_code);
			            
			            
			            String from_user_id_str = (String) jsonObject.get("from_user_id_str");
			            //System.out.println(from_user_id_str);
			            eachrow.add(from_user_id_str);
			            
			            
			            String created_at = (String) jsonObject.get("created_at");
			            //System.out.println(created_at);
			            eachrow.add(created_at);
			            
			            
			            String source = (String) jsonObject.get("source");
			            //System.out.println(source);
			            eachrow.add(source);
			            
			            
			            String id_str = (String) jsonObject.get("id_str");
			            //System.out.println(id_str);
			            eachrow.add(id_str);
			            
			            
			            String from_user_name = (String) jsonObject.get("from_user_name");
			            //System.out.println(from_user_name);
			            eachrow.add(from_user_name);
			            
			            
			            String profile_image_url_https = (String) jsonObject.get("profile_image_url_https");
			            //System.out.println(profile_image_url_https);
			            eachrow.add(profile_image_url_https);
			            
			            
			            Object metadata =  jsonObject.get("metadata");
			            JSONObject result = (JSONObject) metadata;
			            String result_type = (String) result.get("result_type");
			            //System.out.println(result_type);
			            
			            eachrow.add(result_type);
			            list.add(eachrow);
			  
			        } 
			        catch (ParseException e) {
			            e.printStackTrace();
			        }
			        
			        //*/
					
				}
				
				
				
			}
			//Check all data load or not
			if(list.size() != countRow ){
				System.out.println(countRow);
				System.out.println("data load error111111");
			}
			///*
		    brTest.close();
		}
		catch(Exception e){
			System.out.println("could not find file 1111111");
		}
		/*/Check all data load or not
		if(list.size() != countRow || list.get(0).size() != countColumn){
			System.out.println(countRow);
			System.out.println(countColumn);
			System.out.println("data load error111111");
		}
		*/
		///*
		String[][]dataInstances = new String[list.size()][list.get(0).size()];
		
		for(int i = 0; i < list.size(); i++){
			for(int j = 0; j < list.get(0).size();j++){
				dataInstances[i][j]= list.get(i).get(j);
			}
		}
		
		return dataInstances;
		//*/
	}	
}
