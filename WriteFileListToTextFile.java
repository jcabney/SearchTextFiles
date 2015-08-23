package fileSearch;

/* WriteFileListToTextFile Class - writeFile method accepts file path and ArrayList of strings. Writes files in ArrayList to text file in directory 
* specified by user. 
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFileListToTextFile {
	
	private String nameOfFile; // set by writeFile method
	
	public void writeFile(File fileSaveLocation, ArrayList<String> files) {
		try {
			nameOfFile = fileSaveLocation.toString() + "/keyWordSearch.txt"; //creates complete path, saving file as keyWordSearch.txt
			File file = new File(nameOfFile);
			
			//creates file if that name doesn't already exist
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			
			if (files.size() == 0) {
				bw.write("No files found");
			} else {
				//loops through ArrayList, writing each entry on its own line
				for(int i=0; i<files.size(); i++) {
					bw.write(files.get(i)); //writes item from ArrayList to file
					bw.newLine(); // goes to next line
				}
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
