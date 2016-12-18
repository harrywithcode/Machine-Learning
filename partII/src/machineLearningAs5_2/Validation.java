package machineLearningAs5_2;

import java.util.ArrayList;
import java.util.List;

public class Validation {
	public double valid(List<List<Integer>> res, String [][] data, int []centerId){
		//Container c= new Container();
		KmeanAlg kk = new KmeanAlg();
		//int[] centerId = v.getCenterId(res, data);
		double SSE = 0.0;
		for(int i = 0; i < centerId.length;i++){
			for(int j = 0; j < res.get(i).size();j++){			
				SSE += kk.calculateDistance(data[centerId[i]], data[res.get(i).get(j)]) * kk.calculateDistance(data[centerId[i]], data[res.get(i).get(j)]);
			}
		}
		return SSE;
		
	}
}
