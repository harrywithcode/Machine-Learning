package ml5Part1;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MainDriver {
	public static void main(String[] args)throws IOException{
		String address = args[1];
		InputFile q = new InputFile();
		double[][]dataInstances = new double[101][3];
		
		
		
		String[] test = new String[10];
		test = q.readOriginalAttributes(address);
		
		
		dataInstances =  q.readDataInstances(address);
		
		KmeanAlg k = new KmeanAlg();
		String readCluster = args[0];
		int cluster = Integer.parseInt(readCluster);
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result = k.kmean(dataInstances,cluster);
		
		Validation v = new Validation();
		double sse = 0.0;
		sse = v.valid(result, dataInstances);
		System.out.print("The SSE is: " + sse);
		
		
		
        BufferedWriter output = null;
        try {
            File file = new File(args[2]);
            output = new BufferedWriter(new FileWriter(file));
            output.write("<Cluster Id>    <List of points ids separated by comma>");
            output.write("\r\n");
            for(int i = 0; i < cluster; i++){
            	output.write(Integer.toString(i) + "    ");
            	for(int j = 0; j < result.get(i).size(); j++){
            		String temp = Integer.toString(result.get(i).get(j)+1);
            		if(j<result.get(i).size()-1){
            			output.write(temp + ", ");
            		}
            		else{
            			output.write(temp);
            		}
            	}
            	output.write("\r\n");
            }
            output.write("\r\n");
            output.write("The SSE is: " + Double.toString(sse));
        } 
        catch ( IOException e ) {
            e.printStackTrace();
        } 
        finally {
          if ( output != null ) {
            output.close();
          }
        }
        
        
		
	}
}
