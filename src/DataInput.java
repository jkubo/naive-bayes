import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DataInput extends Main {
	static BufferedReader br;
	
	public void parseCorpus(FileReader fr) throws IOException {
		br = new BufferedReader(fr);
		String name, cat, bio, line = br.readLine();
		
		while(true) {
			name = line.trim();
			cat = br.readLine().trim();
			bio = br.readLine();
			while((line = br.readLine()).length() != 0)
				bio += " "+line;
			if(N-- > 0)
				trainBio.add(new Person(name, cat, bio.trim()));
			else
				testBio.add(new Person(name, cat, bio.trim()));
			
			while((line = br.readLine()) != null && line.length() == 0)
				continue;
			if(line == null) break;
		}
		
		fr.close();
	}
	
	public void inputTests(InputStreamReader in) throws IOException {
		br = new BufferedReader(in);
		System.out.print("Enter number of training cases (N): ");
		N = Integer.parseInt(br.readLine());
		System.out.print("\n");
		
		in.close();
	}
	
	public void parseStop(FileReader fr) throws IOException {
		br = new BufferedReader(fr);
		String line;
		
		while((line = br.readLine()) != null) {
			String[] token = line.trim().split("\\s");
			for(String s: token)
				if(!s.isEmpty()) stopWords.add(s);
		}
		fr.close();
	}
}
