package machineLearningAs5_2;

import java.util.ArrayList;
import java.util.List;

public class Container {
	private List<List<Integer>> res = new ArrayList<List<Integer>>();
	private int []changedCenterId;
	
	public Container(List<List<Integer>> res,int []changedCenterId){
		this.res = res;
		this.changedCenterId = changedCenterId;
	}
	public List<List<Integer>> getList(){
		return res;
	}
	public int[] getArray(){
		return changedCenterId;
	}
}
