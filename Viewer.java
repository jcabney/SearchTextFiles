package fileSearch;

/*This program allows users to search for files in the directory they specify that contain the key words they enter.
 * The file paths are saved in a text file to the location of their choice.  
 * 
 * View class - contains the main method. Creates the GUI and accepts user input - text, directory to search, and location to save results to. 
 * Passes the input keywords and file paths to the WalkTheFileTree class.
 * 
 * WalkTheFileTree Class - model method accepts input keywords and file paths. Searches files in directory specified, and its sub-directories 
 * calling the ReadFile openFile method on each file. If openFile returns true, file is added to list of files. Passes list of files matching criteria to WriteFileListToTextFile class.
 * 
 * ReadFile Class - accepts file path and keywords array. Goes line by line through each text file in directory looking for key words. When all
 * keywords are found, true is returned. 
 * 
 * WriteFileListToTextFile Class - writeFile method accepts file path and ArrayList of strings. Writes files in ArrayList to text file in directory 
 * specified by user. 
 */

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Viewer extends JFrame {

	//set by initViewer method
	private File searchFile = null;
	private File fileSaveLocation = null;
	private String[] keyWords = null;
	
	private static final long serialVersionUID = 1L;
 
	public Viewer() {
		initViewer();
	}
	
	public static void main(String[] args) {
		new Viewer();
	}

	//GUI is created here 
	private void initViewer() {
		//Basic frame setup
		final JFrame frame = new JFrame("Key Word File Search");
		frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Creating panel
        JPanel panel = new JPanel();
        
        //Create labels - key words to search for
        JLabel keyWordsToSearchFor = new JLabel("Enter key words to search files for:");
        
        //Create text area for user to type key words into
        final JTextArea keyWordToSearchForField = new JTextArea(); //changed from JTextField to JTextArea so text will wrap
        keyWordToSearchForField.setLineWrap(true);
        keyWordToSearchForField.setWrapStyleWord(true);
        
        //Create buttons to allow user to select directory to search and to choose location to save results text file to
        JButton directoryButton = new JButton("Choose Search Directory");
        JButton runModelButton = new JButton("Run");
        
        //Creates file/directory search pop-up menu when "Choose Search Directory" button is pressed. Result saved to searchFile variable.
        directoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
              JFileChooser fileChooser = new JFileChooser();
              fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //can search individual file or whole directories
              int returnValue = fileChooser.showOpenDialog(null);
              if (returnValue == JFileChooser.APPROVE_OPTION) {
            	  searchFile = fileChooser.getSelectedFile();
              } 
            }
          });
        
        //Creates directory search pop-up menu when "Run" button is pressed. Result saved to fileSaveLocation variable. 
        //Model is called at this point.
        runModelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		//input text split into array of strings
        		keyWords = keyWordToSearchForField.getText().split(" ");
        		
        		//ensures user has input required fields before continuing
        		if (searchFile == null || keyWords == null) {
        			JOptionPane.showMessageDialog(frame,
        					    "Please make sure you enter key words and choose a search directory before pressing Run"); //reminds user to enter fields
        		} else { 
        			JFileChooser resultsSaveLocation = new JFileChooser();
        			resultsSaveLocation.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //files have to be saved to a directory
        			int returnValue = resultsSaveLocation.showOpenDialog(null);
        			if (returnValue == JFileChooser.APPROVE_OPTION) {
        				fileSaveLocation = resultsSaveLocation.getSelectedFile();
        			}
        		
        			//static model method is called. searches files in specified directory and writes matches to file.
        			WalkTheFileTree.model(keyWords, searchFile, fileSaveLocation);
        			keyWordToSearchForField.setText(null); // after results file is saved, user input variables are cleared.
        			searchFile = null;
        			fileSaveLocation = null;
        		}	
        	}
        });
         
        //Add components to panel using Group Layout for positioning
        //Items displayed in one column
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup firstColumn = layout.createParallelGroup();
        firstColumn.addComponent(keyWordsToSearchFor);
        firstColumn.addComponent(keyWordToSearchForField);
        firstColumn.addComponent(directoryButton);
        firstColumn.addComponent(runModelButton);

        GroupLayout.SequentialGroup topToBottom = layout.createSequentialGroup();
        topToBottom.addComponent(keyWordsToSearchFor);
        topToBottom.addComponent(keyWordToSearchForField);
        topToBottom.addComponent(directoryButton);
        topToBottom.addComponent(runModelButton);
        
        layout.setHorizontalGroup(firstColumn);
        layout.setVerticalGroup(topToBottom);
        
        //Panel with components is added to Frame
        frame.add(panel);
        
        //Display the window.
        frame.setVisible(true);
    }
}
