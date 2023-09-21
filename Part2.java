import java.util.*;
public class Part2 {

	public static Exon findExon(String dnaDB, String protDB) {
		Exon e = new Exon();
		String prot = protDB;
		String nProt = Part1.translateRNA(dnaDB);
		while (nProt.length() >= 0 && nProt.indexOf(prot) == -1) {
			prot = prot.substring(0, prot.length()-1);
		}
		if (nProt.contains(prot)) {
			e = new Exon(1+nProt.indexOf(prot)*3, prot.length()*3);
		}
		return e;
	}
	
	public static Exon findLongestExonList(ArrayList<Exon> exons) {
		int l = 0;
		Exon longest = new Exon();
		for (Exon e : exons) {
			if (e.length > l) {
				l = e.length;
				longest = e;
			}
		}
		return longest;
	}
	
	public static Exon findLongestExon(String dnaDB, String protDB) {
		ArrayList<Exon> exonList = new ArrayList<Exon>();
		for (int i=0; i<3; i++) {
			String dna = dnaDB.substring(i);
			Exon e = findExon(dna, protDB);
			e.start+=i;
			exonList.add(e);
		}
		return(findLongestExonList(exonList));
	}
	
	public static ArrayList<Exon> findAllExons(String dnaDB, String protDB) {
		ArrayList<Exon> eList = new ArrayList<Exon>();
		String prot = protDB;
		while (prot.length() != 0) {
			Exon e = findLongestExon(dnaDB, prot);
			eList.add(e);
			String nProt = translateExon(dnaDB, e);
			prot = prot.substring(nProt.length());
		}
		return eList;
	}
	
	public static String translateExon(String dna, Exon e) {
		String nDna = dna.substring(e.start-1, e.start+e.length-1);
		return Part1.translateRNA(nDna);
	}
	
	public static int exonsInOrder(ArrayList<Exon> exons) {
		int n = 0;
		for (int i=0; i<exons.size()-1; i++) {
			n += exons.get(i+1).compareTo(exons.get(i));
		}
		return n;
	}
	
	public static void main(String[] args) {
		String prot = BetaGlobin.Protein;
		String dna = BetaGlobin.pre_mRNA;
		ArrayList<Exon> eList = findAllExons(dna, prot);
		int i = 1;
		String completeExon = "";
		for (Exon e : eList) {
			completeExon += translateExon(dna, e);
			System.out.println(String.format("Exon %d: (%d:%d)\n%s", i++, e.start, e.length, translateExon(dna, e)));
		}
		boolean match = false;
		if (prot.equals(completeExon)) {
			match = true;
		}
		System.out.println(String.format("\nBetaGlobin Match = %b", match));
		
		String not = "";
		if (exonsInOrder(eList) != eList.size()-1) {
			not = "not ";
		}
		System.out.println(String.format("Exons %sin sequential order!", not));
	}

}
