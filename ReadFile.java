package fileSearch;
 
/* ReadFile Class - accepts file path and keywords array. Goes line by line through each text file in directory looking for key words. When all
* keywords are found, true is returned. 
*/


import java.io.IOException;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class ReadFile {
	
	//set by constructor
	private String path;
	private String[] keyWords;
	
	//constructor
 	public ReadFile (String filePath, String[] args) {
		path = filePath;
		keyWords = args;
	}
	
 	//checks file for keywords. If file contains all keywords, true is returned, otherwise false.
	public boolean openFile() throws IOException {
		String line = " ";
		boolean check = false;
		
		BufferedReader textReader = new BufferedReader(new FileReader(path));
		outerloop:  		// allows break statement to exit all loops once file path is added to the files ArrayList
		while ((line = textReader.readLine()) != null ) {		// loops line by line until there are no more lines
			String[] words = line.split(" ");					// breaks line into String array
			
			for (String word : words) {							// loops through words in each line
				for (String key : keyWords) {					// loops through keyWords input by user
					if (word.equals(key)) {						// checks for a match between words in file and user key words input
						ArrayList<String> list = new ArrayList<String>(Arrays.asList(keyWords));	// the next three lines remove keyWord from array since the word has been found
						list.removeAll(Arrays.asList(key));
						keyWords = list.toArray(new String[0]);			
						
						if (keyWords.length == 0) {
							check = true;
							break outerloop;					// breaks out of entire loop sequence. no need to keep looping once path has been added
						}					
					}
				}
			}
		}
			
		textReader.close( );
		return check;		
	}
}

