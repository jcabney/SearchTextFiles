package fileSearch;

/*WalkTheFileTree Class - model method accepts input keywords and file paths. Searches files in directory specified, and its sub-directories 
* calling the ReadFile openFile method on each file. If openFile returns true, file is added to list of files. Passes list of files matching criteria 
* to WriteFileListToTextFile class.
*/ 

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class WalkTheFileTree extends SimpleFileVisitor<Path> {
	
	private String[] keyWords;
	private static ArrayList<String> filesWithKeyWord = new ArrayList<String>(); 	
	private boolean check = false;
	
	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	//overrides SimpleFileVisitor method
	//calls ReadFile class method openFile to determine if file contains specified keywords
	@Override 
	public FileVisitResult visitFile(Path path, BasicFileAttributes arg1) throws IOException {
		if (path.toString().endsWith(".txt")) { // program only examines text files
		    ReadFile fileToRead = new ReadFile(path.toString(), keyWords);
		    check = fileToRead.openFile();
		    if (check) {
		    	filesWithKeyWord.add(path.toString());					// if all input key words have been matched the file path is added
		    }
		}
		return FileVisitResult.CONTINUE;
	}

	//called by Viewer class
	public static void model(String[] args, File searchDirectory, File fileSaveLocation) {
		try {
			Path path = Paths.get(searchDirectory.toString());
			WalkTheFileTree printFiles = new WalkTheFileTree();
			printFiles.setKeyWords(args);
			Files.walkFileTree(path, printFiles); // walkFileTree automatically calls visitFile method, which is overwritten above
			WriteFileListToTextFile create = new WriteFileListToTextFile(); //creates instance of WriteFileListToTextFile class
			create.writeFile(fileSaveLocation, filesWithKeyWord); //uses its writeFile method to save results of walkFileTree to text file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
