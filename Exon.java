
public class Exon implements Comparable<Exon> {

	public int start;
	public int length;
	
	public Exon() {
		start = 0;
		length = 0;
	}
	
	public Exon(int start, int length) {
		this.start = start;
		this.length = length;
	}
	
	public String toString() {
		return "Start Index: " + this.start + " Length: " + this.length;
	}
	
	@Override
	public int compareTo(Exon e) {
		if (this.start > e.start) {
			return 1;
		}
		if (this.start < e.start) {
			return -1;
		}
		return 0;
	}	
}
