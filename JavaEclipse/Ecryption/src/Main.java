import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {	
		startUp();
	}
		public static void startUp() throws IOException {
			String content = null;
			boolean rewrite = false;
			int num = 0;
			
			content = new String(Files.readAllBytes(Paths.get("C:/Users/liam/Desktop/EFile.txt")));
	
			StringBuffer text = new StringBuffer(content);
			Scanner input = new Scanner(System.in);
			String input1 = input.nextLine();
			
			if (input1.equals("E")) {
				int len = text.length();
				for (int i = 0; i < len; i++) {
					num += 1;
					int temp = 0;
					temp = (int)text.charAt(i);
					if (temp >= 8 && temp <= 15) {}
					else if (temp != 160) {	
						if (temp + num <= 126) {temp = temp + num;}	
						else if (temp + num > 126) {
							temp = temp + num;
							temp = (temp % 126) + 32;
							if (temp > 126) {temp = (temp % 126) + 32;}
						}
						if (temp < 32 || temp > 126) {System.out.println("WARNING" + temp + " IS OUT OF RANGE");}
						else {System.out.println(temp);}
						text.setCharAt(i, (char)temp);
					}
				}
				rewrite = true;
			}
			if (input1.equals("D")) {	
				int len = text.length();
				for (int i = 0; i < len; i++) {
					num += 1;
					int temp = 0;
					temp = (int)text.charAt(i);
					if (temp >= 8 && temp <= 15) {}
					else if (temp != 160) {	
						if (temp - num >= 32) {temp = temp - num;}
						else if (temp - num < 32) {
							temp = temp - num;
							temp = 126 + ((temp - 32) % 126);
							if (temp < 32) {temp = 126 + (temp - 32);}
						}
						if (temp < 32 || temp > 126) {System.out.println("WARNING" + temp + " IS OUT OF RANGE");}
						text.setCharAt(i, (char)temp);
					}
				}
				rewrite = true;
			}
			
			if (rewrite) {
				System.out.println("\n" + text);
				
				File theFile = new File("C:/Users/liam/Desktop/EFile.txt");
				FileWriter fileWriter = new FileWriter(theFile, false);
				fileWriter.write(text.toString());
				fileWriter.close();
				
				startUp();
			}
		}
}
