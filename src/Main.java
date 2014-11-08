import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Main {
	static int N;
	static double epsilon = 0.1;
	
	static ArrayList<Person> trainBio = new ArrayList<Person>();
	static ArrayList<Person> testBio = new ArrayList<Person>();
	static HashSet<String> stopWords = new HashSet<String>();
	static HashSet<String> allWords = new HashSet<String>();
	
	static HashMap<String, Integer> catOccur = new HashMap<String, Integer>();
	static HashMap<String, HashMap<String, Integer>> wordOccur = new HashMap<String, HashMap<String, Integer>>();
	static HashMap<String, Double> catProb = new HashMap<String, Double>();
	static HashMap<String, HashMap<String, Double>> wordProb = new HashMap<String, HashMap<String, Double>>();
	
	static HashMap<String, ArrayList<Double>> predProb = new HashMap<String, ArrayList<Double>>();
	static HashMap<String, Integer> predIndex = new HashMap<String, Integer>();
	
	public static void main(String args[]) throws IOException {
		inputMethods();
		learningMethods();
		classifyMethods();
		outputMethods();
	}
	
	private static void inputMethods() throws IOException {
		DataInput in = new DataInput();
		in.inputTests(new InputStreamReader(System.in));
		in.parseStop(new FileReader("stopword.txt"));
		in.parseCorpus(new FileReader("corpus.txt"));
	}
	
	private static void learningMethods() {
		Learner learn = new Learner();
		learn.normalize();
		learn.count();
		learn.probabilize();
	}
	
	private static void classifyMethods() {
		Classifier classy = new Classifier();
		classy.classify();
		classy.setIndices();
	}
	
	private static void outputMethods() throws IOException {
		DataOutput out = new DataOutput();
		System.out.println(out.outputResult());
		out.exportResult(new FileWriter("output.txt"));
	}
}