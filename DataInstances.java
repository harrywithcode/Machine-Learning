package utd.ml.id3;

import java.util.ArrayList;

public class DataInstances {
	private String[] originalAttributes;	
	private ArrayList<String> attributes;
	private String targetAttribute;
	private String Instances[][];
	

	public DataInstances(String[] originalAttributes, ArrayList<String> attributes,String targetAttribute,String Instances[][])
	{
		this.originalAttributes = originalAttributes;
		this.attributes = attributes;
		this.targetAttribute = targetAttribute;
		this.Instances = Instances;
	}

	public void printOriginalAttributes()
	{
		System.out.println("Original attributes:");		
		for (int i=0; i< originalAttributes.length; i++)
		{
			System.out.print(originalAttributes[i] + "\t");
		}
		System.out.println("\n");		
	}
	
	
	public void printAttributes()
	{
		System.out.println("Attributes:");		
		for (int i=0; i< attributes.size(); i++)
		{
			System.out.print(attributes.get(i) + "\t");
		}
		System.out.println("\n");		
	}
	
	public void printInstances()
	{
		System.out.println("dataInstances:");		
		for (int i=0; i< Instances.length; i++)
		{
			for (int j=0; j< Instances[i].length; j++)
				System.out.print(Instances[i][j] + "\t");
			System.out.println("\n");
		}
		System.out.println("\n");		
	}
	
	public String[] getOriginalAttributes() {
		return originalAttributes;
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public String getTargetAttribute() {
		return targetAttribute;
	}

	public String[][] getInstances() {
		return Instances;
	}
	
	

}
