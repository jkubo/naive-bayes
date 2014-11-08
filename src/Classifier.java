import java.util.ArrayList;


public class Classifier extends Main {
	public void classify() {
		for(Person p: testBio) {
			ArrayList<Double> al = new ArrayList<Double>();
			for(String c: catOccur.keySet()) {
				double sum = catProb.get(c);
				for(String w: p.getBio()) {
					if(wordProb.get(c).containsKey(w))
						sum += wordProb.get(c).get(w);
					else
						sum += -Math.log(epsilon)/Math.log(2);
				}
				al.add(sum);
			}
			predProb.put(p.getName(), al);
		}
	}
	
	public void setIndices() {
		for(String p: predProb.keySet()) {
			double max = Double.MAX_VALUE;
			int index = -1;
			
			ArrayList<Double> al = predProb.get(p);
			for(int i=0; i<al.size(); i++) {
				if(max > al.get(i)) {
					max = al.get(i);
					index = i;
				}
			}
			predIndex.put(p, index);
		}
	}
}
