package ml5Part1;


import java.util.*;
import java.lang.Math;


public class KmeanAlg {
	public List<List<Integer>>  kmean(double[][] data, int k){
		int[] mark = new int[data.length];  //record each row of data belong to which cluster
		double[] randomValue = new double[k*2];	//k*2,at this time, k = 5
		double [] centroidValue = new double [k*2]; //k*2
		
		for(int i = 0; i < k; i++){
			randomValue[2*i] = Math.random();
			randomValue[2*i+1] = Math.random();
		}
		for(int i = 0; i < centroidValue.length; i++){
			centroidValue[i] = Double.MAX_VALUE;
		}
		int time = 0;
		int judge = 0;
		double randomX = 0.0;
		double randomY = 0.0;
		
		while(judge != 2*k && time < 25){
			//
			double[] array = new double[data.length];	//record distance of each node to closest original point
			for(int l = 0; l < array.length; l++){
				array[l] = Double.MAX_VALUE;
			}
			//
			for(int i = 0; i < k; i++){		
				
				if(centroidValue[2*i] == Double.MAX_VALUE && centroidValue[2*i+1] == Double.MAX_VALUE){	//0.0 can be changed to max value
					randomX = randomValue[2*i];
					randomY = randomValue[2*i+1];
				}
				else{
					randomX = centroidValue[2*i];
					randomY = centroidValue[2*i+1];
					
					randomValue[2*i] = randomX;
					randomValue[2*i+1] = randomY;
				}
				
				for(int j = 0; j < data.length; j++){
					double distance = (data[j][1] - randomX)*(data[j][1] - randomX) + (data[j][2] - randomY)*(data[j][2] - randomY);
					if(distance < array[j]){
						array[j] = distance;
						mark[j] = i;
					}
				}
			}
			//one loop has been finished, now we will update centroid value
			
			for(int i = 0; i < k; i++){		
				double centroidX = 0.0;
				double centroidY = 0.0;
				double countX = 0.0;
				double countY = 0.0;
				
				for(int j = 0;j < mark.length; j++){
					if(mark[j] == i){
						centroidX += data[j][1]; 
						centroidY += data[j][2];
						countX++;
						countY++;
					}
				}
				centroidX = centroidX / countX;
				centroidY = centroidY / countY;
				centroidValue[2*i] = centroidX;
				centroidValue[2*i+1] = centroidY;
				
			}
			//update finished, now we compare new value with old value
			judge = 0;
			for(int i = 0; i < 2*k; i++){	//	k*2
				if(centroidValue[i] == randomValue[i]){
					judge++;
				}
			}
			if(judge == 2*k){	
				System.out.println("finish");
			}
			else{
				System.out.println("it runs "+time+"th times");
			}
			time++;
			
		}	
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < k; i++){		//k = 5
			List<Integer> result = new ArrayList<>();
			for(int j = 0;j < mark.length; j++){
				if(mark[j] == i){
					result.add(j);
				}
			}
			res.add(result);		
		}
		return res;
	}
}

