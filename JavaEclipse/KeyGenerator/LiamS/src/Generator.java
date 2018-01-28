import java.util.Random;

public class Generator {
	
	public static void main(String[] args) {
		int length = 16;
		String Space = "";
		boolean Start = true;
		
		for (int l = length; l >= 0; l--) {	
			Random rand = new Random();
			int  n = rand.nextInt((3 - 1)) + 1;
			int min = 0;
			int max = 0;
			if (n == 1) {
				min = 48;
				max = 57;
			}
			if (n == 2) {
				min = 65;
				max = 90;
			}
			if (n == 3) {
				min = 97;
				max = 122;
			}
			if (Space.length() % 5 == 0 && Start == false) {Space = Space + "-";}
			rand = new Random();
			n = rand.nextInt((max - min)) + min;
			Space = Space + (char)n;
			Start = false;
		}
		Space = Space.substring(1);
		System.out.println(Space);
	}
}
