package tr.com.macik.lockedme;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import tr.com.macik.tools.FileOp;
import tr.com.macik.tools.Log;
import tr.com.macik.tools.UserConsoleInput;

public class LockedMeApp {
	private HashMap<Character, String> menuMap = new LinkedHashMap<>();
	private static UserConsoleInput userInput = new UserConsoleInput();
	private int screenWidth = 80;
	private int menuLevel = 0;
	private int currentLevel = -1;
	private static FileOp file;
	
	
	public static void main(String[] args) {
		String directory = "C:\\Dev\\workspace\\java\\LockedMe.com\\data";
		Log.debug = true;
		LockedMeApp app = new LockedMeApp();
		app.welcomeScreen();
		file = new FileOp(directory);
		while(true) {
			try {
				app.nl();
				app.setMenu(app.menuLevel);
				String select = app.userInput.getSelect(app.menuMap);
				if (app.menuLevel==0) {
					switch (select) {
					case "1":
						reportFiles();	break;
					case "2":
						app.menuLevel = 2;	break;
					case "x":
						break;
					default:
						
					}
					if ("x".equals(select))
						break;
				} else if (app.menuLevel==2) {
					String filename;
					switch (select) {
					case "1":
						filename = app.retrieveFilename("New filename");
						addFile(filename);	break;
					case "2":
						filename = app.retrieveFilename("Filename to delete");
						deleteFile(filename);	break;
					case "3":
						filename = app.retrieveFilename("Search filename");
						searchFile(filename);	break;
					case "r":
						app.menuLevel=0; break;
					default:
						
					}
					
				}
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				// Try to catch all exceptions if possible and 
				// provide a message to the user
				e.printStackTrace();
			}
		}
		
		System.out.println("Application is finished.");
		System.exit(0);

	}

	private String retrieveFilename(String prompt) {
			String input = "";
			while (!FileOp.checkFilename(input)) {
				input = userInput.getText(prompt);
				if ("".equals(input))
					throw new RuntimeException("Current action canceled!");
			}

			return input;
	}

	private static void searchFile(String filename) {
		System.out.println("Search a file with given file name.");
		if (file.search(filename)) {
			System.out.println("File found!");
			boolean yes = userInput.getDecision("Do you will see the file content?");
			if (yes) {
				List<String> content = file.getContent(filename);
				StringBuilder sb = new StringBuilder();
				for(String line : content) {
					if (!(line == null || "".equals(line) || "\n".equals(line))) {
						sb.append(line);
						sb.append('\n');
					}
				}
				System.out.println("____________________________________________________________________________________________________");
				System.out.print(sb);
				System.out.println("____________________________________________________________________________________________________");
			}
		} else {
			System.out.println("File not found!");			
		};
	}

	private static void deleteFile(String filename) {
		System.out.println("Delete a file with given file name.");
		if (file.exist(filename)) {
			boolean yes = userInput.getDecision("Are you sure?");
			if (yes)
				file.delete(filename);
		} else {
			System.out.println("Given file name doesn't exists.");			
		}
	}

	private static void addFile(String filename) {
		System.out.println("Add a file with given file name.");
		if (!file.exist(filename))
			file.touch(filename);
		boolean yes = userInput.getDecision("Would you append some text?");
		if (yes) {
			StringBuilder sb = new StringBuilder();
			String line="start";
			while (true) {
				line = userInput.getText("Line");
				if (line.equals(""))
					break;
				sb.append(line);
				sb.append('\n');
			};
			if (sb.length()>0)
				file.append(filename, sb.toString());
		}
	}

	private static void reportFiles() {
		File[] fList = new File(file.getDirectory()).listFiles();
		System.out.println("Report files in ascending order.");
		Arrays.sort(fList, new SortByFilename());
		//Arrays.sort(fList);
		for (File f : fList) {
			Date yourDate = new Date(f.lastModified());
			DateFormat formatter =  new SimpleDateFormat("dd-MM-yyyy hh-MM-ss");
			String formattedDate = formatter.format(yourDate);
			System.out.println(formattedDate + " " + f.getName());
		}
	}

	private void setMenu(int level) {
		if (currentLevel != level) {
			menuMap.clear();
			switch(level) {
			case 0: 
				menuMap.put('1', "Report Files");
				menuMap.put('2', "Business Operations");
				menuMap.put('x', "Exit");
				break;
			case 2: 
				menuMap.put('1', "Add File");
				menuMap.put('2', "Delete File");
				menuMap.put('3', "Search File");
				menuMap.put('r', "Return");
				break;
			default:
				throw new RuntimeException("Menu not available!");
			}
			currentLevel = level;
		}
	}

	private void welcomeScreen() {
		title("Welcome to LockedMe.com");
		line(18, "Application Name", "Virtual Key Repository");
		nl();
		title("Developer Details");
		line(18, "Name", "Metin Acikalin");
		line(18, "Designation", "Full Stack Developer");
		line(18, "Date", "05th May 2022");
		
	}

	private void nl() {
		System.out.println();
		
	}

	private void line(int promptMaxLength, String prompt, String value) {
		int spaces = promptMaxLength - prompt.length() - 2;
		System.out.print(prompt);
		System.out.print(": ");
		System.out.print(getRepeatedCharacter(' ', spaces));
		System.out.println(value);

	}

	private void title(String text) {
		char charToAppend = '*';
		int lenDecorator = (int) Math.ceil((screenWidth-text.length())/2- 1);
		
		String decorator = getRepeatedCharacter(charToAppend, lenDecorator);

		StringBuilder title = new StringBuilder();
		title.append(decorator);
		title.append(' ');
		title.append(text);
		title.append(' ');
		title.append(decorator);
		title.append(charToAppend);
		
		System.out.println(title.substring(0, screenWidth));
	}

	private String getRepeatedCharacter(char charToAppend, int lenDecorator) {
		char[] charArray = new char[lenDecorator];
		Arrays.fill(charArray, charToAppend);

		return new String(charArray);
	}

}

class SortByFilename implements Comparator<File> {
	@Override
	public int compare(File aFile, File bFile) {
		String aFilenameOrig = aFile.getName();
		String aFilename = aFilenameOrig;
		int aDotIndex = aFilename.indexOf('.');
		if (aDotIndex==0)
			aFilename = "";
		else if (aDotIndex>0)
			aFilename = aFilename.substring(0, aDotIndex);
		else
			aFilenameOrig += '.';

		String bFilenameOrig = bFile.getName();
		String bFilename = bFilenameOrig;
		int bDotIndex = bFilename.indexOf('.');
		if (bDotIndex==0)
			bFilename = "";
		else if (bDotIndex>0)
			bFilename = bFilename.substring(0, bDotIndex);
		else
			bFilenameOrig += '.';
		
		int compareIndex = aFilename.compareTo(bFilename);
		if (compareIndex == 0)
			compareIndex = aFilenameOrig.compareTo(bFilenameOrig);
		return compareIndex;
	}
}