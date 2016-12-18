package machineLearningAs5_2;

import java.util.*;
import java.lang.Math;


public class KmeanAlg {
	public Container/*List<List<Integer>> */  kmean(String[][] data, int k, String[] center){
		
		int[] mark = new int[data.length];  //record each row of data belong to which cluster
		int [] initialCenterId = new int [k]; //k*2
		//find the initial id
		int centerCount = 0;
		for(int i = 0; i < data.length; i++){
			if(data[i][5] .equals(center[centerCount])){
				initialCenterId[centerCount] = i;
				if(centerCount < k-1){
					centerCount++;
				}
			}
		}
		/*
		for(int i = 0; i < initialCenterId.length;i++){
			System.out.println(initialCenterId[i]);
		}
		*/
		
		int time = 0;
		int judge = 0;
		double distance = 0.0;
		int []centerId = new int[k];
		for(int i = 0; i < centerId.length; i++){
			centerId[i] = Integer.MAX_VALUE;
		}
		int []changedCenterId = new int[centerId.length];//after visit each centroid, change this array
		
		while(judge != k && time < 25){
			
			double[] array = new double[data.length];	//record distance of each node to closest original point
			for(int l = 0; l < array.length; l++){
				array[l] = Double.MAX_VALUE;
			}
			
			
			for(int i = 0; i < k; i++){		
				
				if(centerId[i] == Integer.MAX_VALUE){	//judge whether the loop is the first time
					centerId[i] = initialCenterId[i];
					//System.out.println(centerId[i]);
				}
				else{
					centerId[i] = changedCenterId[i];//changedCenterId is not update!!
					//System.out.println(centerId[i]);
				}
				
				
				for(int j = 0; j < data.length; j++){
					distance = calculateDistance(data[j],data[centerId[i]]);
					
					if(distance < array[j]){
						array[j] = distance;
						mark[j] = i;
					}
				}
			}
			//for(int test = 0; test < array.length; test++){
				//System.out.println(array[test]);
			//}
			//for(int t = 0; t < mark.length; t++){
				//System.out.println(mark[t]+"++++++++");
			//}
			//one loop has been finished, now we will update centroid value
			
			for(int i = 0; i < k; i++){		
				double sumDistance = 0.0;
				double smallest = Double.MAX_VALUE;
				//int closestId = 0;
				List<Integer> li = new ArrayList<Integer>();
				
				for(int j = 0;j < mark.length; j++){
					if(mark[j] == i){
						li.add(j); 
					}
				}
				for(int b = 0 ; b < li.size(); b++){
					for(int c = 0; c < li.size(); c++){
						sumDistance += calculateDistance(data[li.get(b)],data[li.get(c)]);
					}
					if(sumDistance < smallest){
						smallest = sumDistance;
						changedCenterId[i] = li.get(b);
					}
					sumDistance = 0;
				}
				
			}
			//update finished, now we compare new value with old value
			judge = 0;
			for(int i = 0; i < k; i++){
				if(changedCenterId[i] == centerId[i]){
					judge++;
				}
			}
			if(judge == k){
				System.out.println("finish");
			}
			else{
				System.out.println("continue"+time);
			}
			time++;
		}	////////////////////////////////////////////////////////////////////
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < k; i++){		
			List<Integer> result = new ArrayList<>();
			for(int j = 0;j < mark.length; j++){
				if(mark[j] == i){
					result.add(j);
				}
			}
			res.add(result);		
		}
		Container c = new Container(res,changedCenterId);
		return c;
	}
	
	
	public double calculateDistance(String []a, String []b){
		double distance = 0;
		int sameCount = 0;
		int totalCount = 0;
		//System.out.println("========================");
		//for(int i = 0; i < a.length; i++){
		int i = 0;	
			String []listOfEach;
			String []listOfCenter;
			if(a[i] != null){
				listOfEach = a[i].split(" ");
			}
			else{
				listOfEach = null;
			}
			//System.out.println(listOfEach.length);
			if(b[i] != null){
				listOfCenter = b[i].split(" ");
			}
			else{
				listOfCenter = null;
			}
			//System.out.println(listOfCenter.length);
			if(listOfEach != null && listOfCenter != null){
				totalCount = totalCount + listOfEach.length + listOfCenter.length;
			}
			else if(listOfEach == null && listOfCenter != null){
				totalCount = totalCount + listOfCenter.length;
			}
			else if(listOfEach != null && listOfCenter == null){
				totalCount = totalCount + listOfEach.length;
			}
			else{
				totalCount = totalCount + 0;
			}
			//System.out.println(totalCount);
			if(listOfEach != null && listOfCenter != null){
				for(int j = 0; j < listOfEach.length; j++){
					for(int k= 0; k < listOfCenter.length; k++){
						if(listOfEach[j].equals(listOfCenter[k])){//not compare each item in array, but compare each word in array
							sameCount++;
							//System.out.println(sameCount);
						}
					}
				}
			}
		//}
		//System.out.println(sameCount);
		//System.out.println(totalCount);
		distance = 1 - (double)sameCount/(double)(totalCount - sameCount);
		return distance;
	}
	
	
}

