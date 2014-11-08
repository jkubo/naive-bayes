import java.util.ArrayList;
import java.util.HashMap;


public class Learner extends Main {
	public void normalize() {
		for(Person p: trainBio) {
			ArrayList<String> al = new ArrayList<String>(p.getBio());
			for(int i=al.size()-1; i>-1; i--) {
				String s = al.set(i, al.get(i).toLowerCase());
				if(s.length() <= 2 || stopWords.contains(s))
					al.remove(i);
				else
					allWords.add(s.toLowerCase());
			}
			p.setBio(al);
		}
		for(Person p: testBio) {
			ArrayList<String> al = new ArrayList<String>(p.getBio());
			for(int i=al.size()-1; i>-1; i--) {
				String s = al.set(i, al.get(i).toLowerCase());
				if(s.length() <= 2 || stopWords.contains(s) || !allWords.contains(s))
					al.remove(i);
			}
			p.setBio(al);
		}
	}
	
	public void count() {
		for(Person p: trainBio) {
			String cat = p.getCat();
			if(catOccur.containsKey(cat))
				catOccur.put(cat, catOccur.get(cat)+1);
			else
				catOccur.put(cat, 1);
		}
		for(String c: catOccur.keySet()) {
			HashMap<String, Integer> hm = new HashMap<String, Integer>(); 
			wordOccur.put(c, hm);
			for(Person p: trainBio) {
				if(p.getCat().equals(c)) {
					for(String w: allWords) {
						if(p.getBio().contains(w)) {
							if(hm.containsKey(w))
								hm.put(w, hm.get(w)+1);
							else
								hm.put(w, 1);
						}
					}
				}
			}
		}
	}
	
	public void probabilize() {
		for(String c: catOccur.keySet()) {
			double cat = Double.valueOf(catOccur.get(c))/trainBio.size();
			cat = (cat+epsilon)/(1+catOccur.size()*epsilon);
			cat = -Math.log(cat)/Math.log(2);
			catProb.put(c, cat);
			
			HashMap<String, Double> hm = new HashMap<String, Double>();
			for(String w: wordOccur.get(c).keySet()) {
				double word = Double.valueOf(wordOccur.get(c).get(w))/catOccur.get(c);
				word = (word+epsilon)/(1+2*epsilon);
				word = -Math.log(word)/Math.log(2);
				hm.put(w, word);
			}
			wordProb.put(c, hm);
		}
	}
}
