# SearchTextFiles
This program allows users to search for files in the directory they specify that contain the key words they enter.
The file paths are saved in a text file to the location of their choice.  
 
View class - contains the main method. Creates the GUI and accepts user input - text, directory to search, and location to save results to. 
Passes the input keywords and file paths to the WalkTheFileTree class.
 
WalkTheFileTree Class - model method accepts input keywords and file paths. Searches files in directory specified, and its sub-directories 
calling the ReadFile openFile method on each file. If openFile returns true, file is added to list of files. Passes list of files matching criteria to WriteFileListToTextFile class.
 
ReadFile Class - accepts file path and keywords array. Goes line by line through each text file in directory looking for key words. When all
keywords are found, true is returned. 
 
WriteFileListToTextFile Class - writeFile method accepts file path and ArrayList of strings. Writes files in ArrayList to text file in directory 
specified by user. 
