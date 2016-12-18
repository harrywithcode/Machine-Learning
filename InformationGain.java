package utd.ml.id3;

import java.util.ArrayList;
import java.util.Arrays;



public class InformationGain {	
	//Array "mark" can only mark the attributes appeared in the same path.
	//private int []mark = new int [20];============This may wrong
	
	public double calculateEntropy (int[] count) {
		double totalcount = (double)(count[0] + count[1]);		
		double entropy = 0.0;
		if (count[0] == 0 || count[1] == 0){
			entropy = 0.0;
			return entropy;
		}
		else{	
			entropy = (-1) * (count[0]/totalcount)*(Math.log10(count[0]/totalcount) / Math.log10(2))
					+ (-1) * (count[1]/totalcount)*(Math.log10(count[1]/totalcount) / Math.log10(2)); 
			return entropy;
		}
	}	
	
	
	
	public String calculateGain (DataInstances dataInst) {

		String[][] dataInstances = dataInst.getInstances();
		ArrayList<String> attributeList = new ArrayList<String>();
		attributeList.addAll(dataInst.getAttributes());
		String[]originalAttributes = dataInst.getOriginalAttributes();
		////////////////////////Debug/////////////////////////
		/*
		for(int i = 0;i <attributeList.size(); i++){
			System.out.println("+++++++++++ " + attributeList.get(i));
		}
		for(int i = 0;i <originalAttributes.length; i++){
			System.out.println("****************** " + originalAttributes[i]);
		}
		for(int i = 0; i < dataInstances.length; i++){
			for(int j = 0; j < dataInstances[0].length; j++){
				System.out.print(dataInstances[i][j]);
			}
			System.out.println();
		}
		*/
		////////////////////////Debug//////////////////////////
		int row = dataInstances.length;
		int column = dataInstances[0].length;
		int parentColumn = column - 1;
        
		String []parentData = new String[row];
		for(int j = 0; j < row; j++){
			parentData[j] = dataInstances[j][parentColumn];
		}
		
		double infogain;
		int pos = 0;
		double max = Integer.MIN_VALUE;
		String result = "";
		
		for(int calculate = 0; calculate < originalAttributes.length - 1; calculate++){
			int childColumn = 0;	
			//System.out.println("pos is ------- " + pos);
				
			if(originalAttributes[calculate].equals(attributeList.get(pos))){
				
				childColumn = calculate;
				if(pos < attributeList.size()){
					pos++;
				}
				
				int []parentCounter = new int[2];
				int []leftChildCounter = new int[2];
				int []rightChildCounter = new int[2];
				String []childData = new String [row];
				double []divideParent = new double [2];
				for(int k = 0; k < row; k++){
					childData[k] = dataInstances[k][childColumn];
					
					if(childData[k].equals("0")  && parentData[k].equals("0")){
						leftChildCounter[0]++;
						parentCounter[0]++;
						divideParent[0]++;
					}
					else if(childData[k].equals("0") && parentData[k].equals("1") ){
						leftChildCounter[1]++;
						parentCounter[1]++;
						divideParent[0]++;
					}
					else if(childData[k].equals("1") && parentData[k].equals("0")){
						rightChildCounter[0]++;
						parentCounter[0]++;
						divideParent[1]++;
					}
					else if(childData[k].equals("1") && parentData[k].equals("1")){
						rightChildCounter[1]++;
						parentCounter[1]++;
						divideParent[1]++;
					}
				}
				
				double pLeft = divideParent[0]/(divideParent[0] + divideParent[1]);
				double pRight = divideParent[1]/(divideParent[0] + divideParent[1]);
				double parentEntropy = this.calculateEntropy(parentCounter);
				double leftChildEntropy = this.calculateEntropy(leftChildCounter);	
				double rightChildEntropy = this.calculateEntropy(rightChildCounter);			
				
				infogain = parentEntropy - 
						(pLeft * leftChildEntropy + pRight * rightChildEntropy);

				
				// DEBUG ===============================================			
				if (GlobalClass.getDebugMode())
				{
					System.out.println("[Debug] calculateGain() - Information gain: " + infogain);							
				}
				// DEBUG ===============================================				
				
				if(infogain > max){
					max = infogain;
					result = attributeList.get(pos-1);
				}
				
				if(attributeList.size() == pos){
					break;
				}
				
				
			}
			
		}
		
		return result;
		
		
	}
	
}
