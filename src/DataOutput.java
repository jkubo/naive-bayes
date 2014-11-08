import java.io.FileWriter;
import java.io.IOException;


public class DataOutput extends Main {
	public String outputResult() {
		StringBuilder ret = new StringBuilder();
		int count = 0;
		
		for(int i=0; i<testBio.size(); i++) {
			String name = testBio.get(i).getName();
			String actual = testBio.get(i).getCat();
			String predict = catOccur.keySet().toArray()[predIndex.get(name)].toString();
			
			if(actual.equals(predict)) count++;
			
			ret.append(name).append(".  ")
			.append("Prediction: ").append(predict).append(".  ")
			.append(actual.equals(predict) ? "Right" : "Wrong").append(".\n");
			
			for(int j=0; j<catOccur.size(); j++) {
				String cat = catOccur.keySet().toArray()[j].toString();
				ret.append(cat).append(": ")
				.append(String.format("%.2f", (recoverProb(predProb.get(name).get(j), name, cat))));
				if(j < catOccur.size()-1) ret.append("    ");
			}
			ret.append("\n\n");
		}
		
		ret.append("Overall accuracy: ").append(count).append(" out of ")
		.append(testBio.size()).append(" = ")
		.append(String.format("%.2f", (Double.valueOf(count)/testBio.size()))).append(".");
		
		return ret.toString().trim();
	}
	
	public double recoverProb(double logProb, String bio, String cat) {
		double m = Double.MAX_VALUE;
		double[] x = new double[catOccur.size()];
		double s = Double.valueOf(0);
		int index = -1;
		
		for(int i=0; i<catOccur.size(); i++) {
			if(catOccur.keySet().toArray()[i].equals(cat))
				index = i;
			m = Math.min(m, predProb.get(bio).get(i));
		}
		
		for(int i=0; i<catOccur.size(); i++) {
			if(predProb.get(bio).get(i) - m < 7)
				x[i] = Math.pow(2, m-predProb.get(bio).get(i));
		}
		
		for(int i=0; i<catOccur.size(); i++) {
			s += x[i];
		}
		
		return x[index]/s;
	}
	
	public void exportResult(FileWriter fw) throws IOException {
		fw.write(outputResult());
		fw.close();
	}
}