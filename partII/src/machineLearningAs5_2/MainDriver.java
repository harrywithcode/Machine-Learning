package machineLearningAs5_2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainDriver {
	public static void main(String[] args) throws IOException{
		String address = args[2];
		String cen = args[1];
		inputFile f = new inputFile();
		String[][] bucket;
		bucket = f.readDataInstances(address);
		
		initialCentroid i = new initialCentroid();
		
		String []test = i.readCentroid(cen);
		
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		KmeanAlg kk = new KmeanAlg();
		Container c;
		String icluster = args[0];
		int cluster = Integer.parseInt(icluster);
		c = kk.kmean(bucket,cluster,test);
		res = c.getList();
		for(int ii = 0 ; ii < res.size(); ii++){
			for(int jj = 0; jj < res.get(ii).size();jj++){
				if(res.get(ii) == null){
					//System.out.print("null");
				}
				else{
					//System.out.print((res.get(ii).get(jj)+1)+ "  ");
				}
			}
			//System.out.println();
		}
		
		
		Validation v = new Validation();
		//Container c = new Container(res, );
		double sse = 0.0;
		int [] changedCenterId = c.getArray();
		sse = v.valid(res, bucket, changedCenterId);
		System.out.println("sse = "+sse);
		
		
		BufferedWriter output = null;
        try {
            File file = new File(args[3]);
            output = new BufferedWriter(new FileWriter(file));
            output.write("<Cluster Id>    <List of tweet ids separated by comma>");
            output.write("\r\n");
            for(int ii = 0; ii < cluster; ii++){
            	output.write(Integer.toString(ii) + "    ");
            	for(int j = 0; j < res.get(ii).size(); j++){
            		String temp = (bucket[res.get(ii).get(j)][5]);
            		if(j<res.get(ii).size()-1){
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
