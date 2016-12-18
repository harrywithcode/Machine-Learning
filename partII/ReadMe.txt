1. Programming language: Java
	1) Java runtime environment: 1.8.0_25
	2) Java compiler: 1.8.0_25

2. How to compile:
javac -cp "json-simple-1.1.jar;java-json.jar;" -d ../bin machineLearningAs5_2/*.java

 3. How to run: 
java -cp "json-simple-1.1.jar;java-json.jar;" machineLearningAs5_2.MainDriver "25" "InitialSeeds.txt" "Tweets.json" "output.txt" 
# You should provide three arguments
	1) number of clusters
	2)a path of a initial data file
	3) a path of json data file
  4) path of output file 