import java.util.*;
public class Part1 {

	public static TreeMap<String, String> codonTable = new TreeMap<String, String>();
	
	public static void initCodonTable() {
		codonTable.put("GTT", "V");
		codonTable.put("GTC", "V");
		codonTable.put("GTA", "V");
		codonTable.put("GTG", "V");
		codonTable.put("GCT", "A");
		codonTable.put("GCC", "A");
		codonTable.put("GCA", "A");
		codonTable.put("GCG", "A");
		codonTable.put("GAT", "D");
		codonTable.put("GAC", "D");
		codonTable.put("GAA", "E");
		codonTable.put("GAG", "E");
		codonTable.put("GGT", "G");
		codonTable.put("GGC", "G");
		codonTable.put("GGA", "G");
		codonTable.put("GGG", "G");
		
		codonTable.put("TTT", "F");
		codonTable.put("TTC", "F");
		codonTable.put("TTA", "L");
		codonTable.put("TTG", "L");
		codonTable.put("TCT", "S");
		codonTable.put("TCC", "S");
		codonTable.put("TCA", "S");
		codonTable.put("TCG", "S");
		codonTable.put("TAT", "Y");
		codonTable.put("TAC", "Y");
		codonTable.put("TAA", "*");
		codonTable.put("TAG", "*");
		codonTable.put("TGT", "C");
		codonTable.put("TGC", "C");
		codonTable.put("TGA", "*");
		codonTable.put("TGG", "W");
		
		codonTable.put("CTT", "L");
		codonTable.put("CTC", "L");
		codonTable.put("CTA", "L");
		codonTable.put("CTG", "L");
		codonTable.put("CCT", "P");
		codonTable.put("CCC", "P");
		codonTable.put("CCA", "P");
		codonTable.put("CCG", "P");
		codonTable.put("CAT", "H");
		codonTable.put("CAC", "H");
		codonTable.put("CAA", "Q");
		codonTable.put("CAG", "Q");
		codonTable.put("CGT", "R");
		codonTable.put("CGC", "R");
		codonTable.put("CGA", "R");
		codonTable.put("CGG", "R");
		
		codonTable.put("ATT", "I");
		codonTable.put("ATC", "I");
		codonTable.put("ATA", "I");
		codonTable.put("ATG", "M");
		codonTable.put("ACT", "T");
		codonTable.put("ACC", "T");
		codonTable.put("ACA", "T");
		codonTable.put("ACG", "T");
		codonTable.put("AAT", "N");
		codonTable.put("AAC", "N");
		codonTable.put("AAA", "K");
		codonTable.put("AAG", "K");
		codonTable.put("AGT", "S");
		codonTable.put("AGC", "S");
		codonTable.put("AGA", "R");
		codonTable.put("AGG", "R");
	}
	
	public static String translateRNA(String RNA) {
		initCodonTable();
		String protein = "";
		for (int i=0; i<RNA.length()-2; i+=3) {
			protein += codonTable.get(RNA.substring(i, i+3));
		}
		
		return protein;
	}
	
	public static String processProtein(String protRaw) {
		if (!protRaw.contains("M")) {
			return "No START signal";
		}
		if (!protRaw.contains("*")) {
			return "No STOP signal";
		}
		int start = protRaw.indexOf("M");
		int stop = protRaw.substring(start).indexOf("*");
		if (stop >= start) {
			return (protRaw.substring(start, start+stop));
		}
		return "didn't work";
	}
	
	public static String subUForT(String dna) {
		return (dna.replace('T', 'U'));
	}
	
	public static void main(String[] args) {
		String protein = BetaGlobin.Protein;
		
		for (int i=0; i<3; i++) {
			String mRNA = BetaGlobin.mRNA.substring(i);
			String protRaw = translateRNA(mRNA);
			String protProc = processProtein(protRaw);
			Boolean match = false;
			if (protProc.equals(protein)) {
				match = true;
			}
			String s = String.format("Raw: \n%s\nProcessed: %s\nBetaGlobin Match = %s", protRaw, protProc, match.toString());
			System.out.println(s);
		}
	}

}
