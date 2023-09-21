import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	public static String task1() {
		String prot = BetaGlobin.Protein;
		String dna = BetaGlobin.pre_mRNA;
		ArrayList<Exon> eList = Part2.findAllExons(dna, prot);
		String s = "", s1 = "",s2 = "";
		
		s1 += dna;
		for (Exon e : eList) {
			String exon = Part2.translateExon(dna, e);
			int currentLength = s2.length();
			for (int i=0; i<e.start-1-currentLength; i++) {
				s2 += " ";
			}
			s2 += "[";
			for (int i=0; i<e.length/3-1; i++) {
				s2 += exon.substring(i, i+1) + "  ";
			}
			s2 += exon.substring(exon.length()-1) + "]";
		}
		int currentS2Length = s2.length();
		for (int i=0; i<s1.length()-currentS2Length; i++) {
			s2 += " ";
		}
		s = s1 + "\n" + s2;
		return s;
	}
	
	public static String task2(int lineLen) {
		String s = task1();
		String s1 = s.substring(0, s.indexOf("\n"));
		String s2 = s.substring(s.indexOf("\n") + 1);
		
		String sNew = "";
		
		while (s1.length() >= lineLen && s2.length() >= lineLen) {
			 sNew += s1.substring(0, lineLen) + "\n" + s2.substring(0, lineLen) + "\n";
			 s1 = s1.substring(lineLen);
			 s2 = s2.substring(lineLen);
		}
		sNew += s1;
		for (int i=0; i<lineLen-s1.length(); i++) {
			sNew += " ";
		}
		sNew += "\n" + s2;
		for (int i=0; i<lineLen-s2.length(); i++) {
			sNew += " ";
		}
		return sNew;
	}
	
	public static String task3(int lineLen) {
		String s = task2(lineLen);
		String sNew = "";
		Pattern pattern = Pattern.compile("[^ ]", Pattern.CASE_INSENSITIVE);
		
		while (s.length() > 0 && s.indexOf("\n") != -1) {
			String s1 = s.substring(0, s.indexOf("\n"));
		    Matcher matcher = pattern.matcher(s1);
		    if (matcher.find()) {
		      sNew += s1+"\n";
		    }
		    s = s.substring(s.indexOf("\n")+1);
		}
		return sNew;
	}
	
	public static String task4(int lineLen) {
		String s = task3(lineLen);
		String sNew = "";

		while (s.length() > 0 && s.indexOf("\n") != -1) {
			String s1 = s.substring(0, s.indexOf("\n"));
			for (int i=0; i<s1.length()/10; i++) {
				sNew += s1.substring(10*i, 10*i+10) + " ";
			}
			sNew += "\n";
			s = s.substring(s.indexOf("\n")+1);
		}
		return sNew;
	}
	
	public static String task5(int lineLen) {
		String s = task4(lineLen);
		String sNew = "";
		Pattern pattern = Pattern.compile("[^ATCG ]", Pattern.CASE_INSENSITIVE);
		
		int n = 1;
		while (s.length() > 0 && s.indexOf("\n") != -1) {
			String s1 = s.substring(0, s.indexOf("\n"));
			Matcher matcher = pattern.matcher(s1);
			if (matcher.find()) {
				sNew += String.format("%-6s ", " ") + s1 + "\n";
			}
			else {
				String filtered = s1.replaceAll("([ ])", "");
				int nChar = filtered.length();
				sNew += String.format("%-6d ", n) + s1 + (n+nChar-1) + "\n";
				n += nChar;
			}
			s = s.substring(s.indexOf("\n")+1);
		}
		return sNew;
	}
	
	public static void main(String[] args) {
		String s = "";
		s += task5(50);
		System.out.println(s);
	}

}
