package utd.ml.id3;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class MainDriver {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 // Check how many arguments were passed in
	    if(args.length != 3)
	    {
	        System.out.println("Please enter three parameters such as a path of train file and test file as well as a pruning factor like 0.1");
	        System.exit(0);
	    }
	    
	    File trainFile = new File(args[0]);
	    if (!trainFile.isFile())
	    {
	        System.out.println("Please enter a correct path of the train file");
	        System.exit(0);	    	
	    }
	    
	    File testFile = new File(args[1]);
	    if (!testFile.isFile())
	    {
	        System.out.println("Please enter a correct path of the test file");
	        System.exit(0);	    	
	    }
	    
	    
		String trainFilePath = args[0];
		DataInstances trainInst = readTrainInputFile(trainFilePath);		
		
		String testFilePath = args[1];		
		DataInstances testInst = readTrainInputFile(testFilePath);		
		
		double pruneFactor = Double.parseDouble(args[2]); 
		run(trainInst, testInst, pruneFactor, 100);
	
	}
	
	
	private static void run(DataInstances trainInst,DataInstances testInst, double pruneFactor, int pruneRepetition)
	{
		///////////////////////////////////		
		// Create a performance object to store summary data of input and result
		///////////////////////////////////		
		Performance trainPerformance = new Performance();
		// Store number of training instances
		trainPerformance.setNoOfInstances(trainInst.getInstances().length);
		// Store number of training attributes
		trainPerformance.setNoOfAttributes(trainInst.getAttributes().size());
		

		///////////////////////////////////		
		// Training Process 
		///////////////////////////////////
		DecisionTree preprunedDT = new DecisionTree();
		ID3Util id3Util = new ID3Util();		
		ID3Alg id3Alg = new ID3Alg(id3Util);

		preprunedDT.setRoot(id3Alg.train(trainInst, null));
		id3Alg.setPreprunedDT(preprunedDT);
		
		
		///////////////////////////////////		
		// Verify decision tree built, traversing it and obtaining its summary data 
		///////////////////////////////////
		preprunedDT.traverseTree(preprunedDT.getRoot());
		// Store total number of nodes in the tree
		trainPerformance.setNoOfTreeNodes(preprunedDT.getNoOfTreeNodes());
		// Store number of leaf nodes in the tree
		trainPerformance.setNoOfLeafNodes(preprunedDT.getNoOfLeafNodes());
		// Put the performance object to DT.
		preprunedDT.getListOfPerformance().put("Train",trainPerformance);
		
		///////////////////////////////////		
		// Evaluate train data and print performance
		///////////////////////////////////
		Evaluation trainEvaluation = new Evaluation (preprunedDT, trainInst, id3Util, trainPerformance);
		trainEvaluation.evaluate();		
		System.out.println("================================");		
		System.out.println("Pre-pruned Decision Tree Structure");
		System.out.println("================================");					
		
		// Print out a structure of the pre-pruned DT
		trainEvaluation.printTree(preprunedDT.getRoot(),0);

		System.out.println("================================");		
		System.out.println("Pre-pruned Accuracy: Train");
		System.out.println("================================");					
		trainPerformance.printTrainResult();

		///////////////////////////////////		
		// Create a performance object to store summary data of input and result
		///////////////////////////////////		
		Performance testPerformance = new Performance();
		// Store number of training instances
		testPerformance.setNoOfInstances(testInst.getInstances().length);
		// Store number of training attributes
		testPerformance.setNoOfAttributes(testInst.getAttributes().size());
		preprunedDT.getListOfPerformance().put("Test",testPerformance);
		
		///////////////////////////////////		
		// Evaluate test data and print performance
		///////////////////////////////////
		Evaluation testEvaluation = new Evaluation (preprunedDT, testInst, id3Util, testPerformance);
		testEvaluation.evaluate();
		System.out.println("================================");		
		System.out.println("Pre-pruned Accuracy: Test");
		System.out.println("================================");			
		testPerformance.printTestResult();
				

		
		
		
		///////////////////////////////////		
		// Pruning Phase
		///////////////////////////////////
		
		Double pruningFactor=0.0;
		pruningFactor = pruneFactor;		
					
		for(int i=0; i < pruneRepetition; i++)
		{
			if (GlobalClass.getDebugMode())
			{
				System.out.println("================================");		
				System.out.println(" The iteration: " + i);
				System.out.println("================================");
			}
			
			///////////////////////////////////		
			// Pruning Process 
			///////////////////////////////////
			DecisionTree clonedDT = new DecisionTree();

			// Before a clone process, initialize the nodeLabel to 0
			id3Alg.setNodeLabel(0);
			// Clone process of the way a training process is run again
			clonedDT.setRoot(id3Alg.train(trainInst, null));
			// Store # of total nodes and leaf nodes, traversing the cloneDT
			clonedDT.traverseTree(clonedDT.getRoot());	


				
			if (GlobalClass.getDebugMode())
			{
				System.out.println("[Debug] main(): pruningFactor " + pruningFactor);				
				System.out.println("================================");		
				System.out.println(" The iteration: " + i);
				System.out.println("================================");
			}
			
			
			DecisionTree postprunedDT = id3Alg.prune(clonedDT, id3Util, pruningFactor);
			String nameOfPostPrunedDT= "Postpruned_" + Integer.toString(i); 
			id3Alg.getListOfPostprunedDT().put(nameOfPostPrunedDT, postprunedDT);

	
			

			
			///////////////////////////////////		
			// Create a performance object for a pruned decision tree on a train data
			///////////////////////////////////		
			Performance trainPerformanceForPruning = new Performance();
			// Store number of training instances
			trainPerformanceForPruning.setNoOfInstances(trainInst.getInstances().length);
			// Store number of training attributes
			trainPerformanceForPruning.setNoOfAttributes(trainInst.getAttributes().size());
			
			///////////////////////////////////		
			// Verify the decision tree cloned, traversing it and obtaining its summary data 
			///////////////////////////////////
			//DecisionTree postprunedDT = id3Alg.getListOfPostprunedDT().get("Postpruned01");
			//DecisionTree postprunedDT = id3Alg.getListOfPostprunedDT().get(nameOfPostPrunedDT);			
			
			//DecisionTree postprunedDT = id3Alg.getPostprunedDT();
			//postprunedDT.traverseTree(postprunedDT.getRoot());
			// Store total number of nodes in the tree
			postprunedDT.setNoOfTreeNodes(0);
			postprunedDT.setNoOfLeafNodes(0);			
			postprunedDT.traverseTree(postprunedDT.getRoot());			
			trainPerformanceForPruning.setNoOfTreeNodes(postprunedDT.getNoOfTreeNodes());
			// Store number of leaf nodes in the tree
			trainPerformanceForPruning.setNoOfLeafNodes(postprunedDT.getNoOfLeafNodes());		
			// Put the performance object to DT
			postprunedDT.getListOfPerformance().put("Train", trainPerformanceForPruning);


			
			
			///////////////////////////////////		
			// Evaluate train data for post-pruned DT and print performance
			///////////////////////////////////
			Evaluation trainEvaluationForPrune = new Evaluation (postprunedDT, trainInst, id3Util, trainPerformanceForPruning);
			trainEvaluationForPrune.evaluate();
			// DEBUG ===============================================			
			if (GlobalClass.getDebugMode())
			{
				System.out.println("[Debug] main() ");
				System.out.println("================================");		
				System.out.println("Pruned Accuracy: Train");
				System.out.println("================================");		
				trainPerformanceForPruning.printTrainResult();				
			}
			// DEBUG ===============================================				
			

			
			
			///////////////////////////////////		
			// Create a test performance object for pruned DT to store summary data of input and result
			///////////////////////////////////		
			Performance testPerformanceForPrune = new Performance();
			// Store number of training instances
			testPerformanceForPrune.setNoOfInstances(testInst.getInstances().length);
			// Store number of training attributes
			testPerformanceForPrune.setNoOfAttributes(testInst.getAttributes().size());
			postprunedDT.getListOfPerformance().put("Test", testPerformanceForPrune);		
			
			///////////////////////////////////		
			// Evaluate test data and print performance
			///////////////////////////////////
			Evaluation testEvaluationForPrune = new Evaluation (postprunedDT, testInst, id3Util, testPerformanceForPrune);
			testEvaluationForPrune.evaluate();
			// DEBUG ===============================================			
			if (GlobalClass.getDebugMode())
			{
				System.out.println("[Debug] main() ");
				System.out.println("================================");		
				System.out.println("Pruned Accuracy: Test");
				System.out.println("================================");	
				testPerformanceForPrune.printTestResult();				
			}
			// DEBUG ===============================================				
			

			
		}
		
		DecisionTree bestPostprunedDT=null, postprunedDT=null;
		Performance bestTestPeformance = null, performance=null, bestTrainPerformance=null;
		
		
		
		Map<String, DecisionTree> map = id3Alg.getListOfPostprunedDT();
		double bestAccuracy=0.0, accuracy=0.0;
		for (Map.Entry<String, DecisionTree> entry : map.entrySet()) 
		{
			DecimalFormat df = new DecimalFormat("#.00%");
			postprunedDT = entry.getValue();
			performance = postprunedDT.getListOfPerformance().get("Test");
			accuracy = performance.getAccuracy();
			if (bestAccuracy < accuracy )
			{
				bestAccuracy = accuracy;
				bestTestPeformance = performance;
				bestTrainPerformance = postprunedDT.getListOfPerformance().get("Train");
				bestPostprunedDT = postprunedDT;
			}
			// DEBUG ===============================================			
			if (GlobalClass.getDebugMode())
			{
				System.out.println("[Debug] main() ");				
				System.out.println("Accuracy:  " + df.format(accuracy) +  "\t" + " Best accuracy: " +  df.format(bestAccuracy) + " on " + entry.getKey());
			}
		} // End of for loop
		
		System.out.println("================================");		
		System.out.println("Post-pruned Decision Tree Structure");
		System.out.println("================================");					
		
		// Print out a structure of the pre-pruned DT
		trainEvaluation.printTree(bestPostprunedDT.getRoot(),0);
		
		
		System.out.println("================================");		
		System.out.println("Post-pruned Accuracy: Train");
		System.out.println("================================");		
		bestTrainPerformance.printTrainResult();
		System.out.println("================================");		
		System.out.println("Post-pruned Accuracy: Test");
		System.out.println("================================");		
		bestTestPeformance.printTestResult();
		
		
		
	}
	

	private static DataInstances readTrainInputFile(String filePath)
	{
		String[] originalAttributes = null;	
		ArrayList<String> attributes = null;
		String targetAttribute = null;
		String Instances[][] = null;
		
		InputFile input = new InputFile();
		
		originalAttributes = input.readOriginalAttributes(filePath);
		attributes = input.readAttributes(filePath);
		targetAttribute = input.readTargetAttributes(filePath);
		Instances = input.readDataInstances(filePath);
		
		DataInstances dataInst= new DataInstances(originalAttributes, attributes,targetAttribute,Instances);
		
		return dataInst;
		
	}
	
	

}
