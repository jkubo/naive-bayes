import java.util.ArrayList;


class Person {
	private String name, cat;
	private ArrayList<String> bio;
	public Person(String name, String cat, String bio) {
		this.name = name;
		this.cat = cat;
		this.bio = infoParser(bio);
	}
	private ArrayList<String> infoParser(String data) {
		ArrayList<String> ret = new ArrayList<String>();
		String[] temp = data.replaceAll("[^A-Za-z1-9\\s]", "").split("\\s");
		
		for(String s: temp) {
			String str = s.trim();
			if(!str.isEmpty())
				ret.add(s.trim());
		}
		return ret;
	}
	public String toString() {
		return "["+name+" | "+cat+" | ("+bio.size()+") "+bio+"]\n";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public ArrayList<String> getBio() {
		return bio;
	}
	public void setBio(ArrayList<String> bio) {
		this.bio = bio;
	}
}