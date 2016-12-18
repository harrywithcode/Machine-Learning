package ml5Part1;


import java.util.*;

public class Validation {
	public double valid(List<List<Integer>> re, double [][] data){
		double SSE = 0.0;
		double valueX = 0.0;
		double valueY = 0.0;
		double numX = 0.0;
		double numY = 0.0;
		for(int i = 0;i < re.size(); i++){
			for(int j = 0; j < re.get(i).size(); j++){
				valueX += data[re.get(i).get(j)][1];
				valueY += data[re.get(i).get(j)][2];
				numX++;
				numY++;
			}
			if(numX == 0){
				valueX = 0;
			}
			else{
				valueX = valueX / numX;
			}
			if(numY == 0){
				valueY = 0;
			}
			else{
				valueY = valueY / numY;
			}
			for(int j = 0; j < re.get(i).size(); j++){
				SSE += (valueX - data[re.get(i).get(j)][1]) * (valueX - data[re.get(i).get(j)][1])
						+ (valueY - data[re.get(i).get(j)][2]) * (valueY - data[re.get(i).get(j)][2]);
			}
		}
		return SSE;
		
	}
}
