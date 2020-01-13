package Models;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile{
	private String path;
	
	public WriteFile(String file_path){
		path = file_path;
	}
	
	public void writeToFile(String textLine) throws IOException {
		FileWriter write = new FileWriter(path + ".txt", true);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		
		print_line.close();
	}
}