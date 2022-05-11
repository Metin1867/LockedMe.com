package tr.com.macik.lockedme;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

import tr.com.macik.tools.Log;

public class LockedMeApp {
	private HashMap<Character, String> menuMap = new LinkedHashMap<>();
	private int screenWidth = 80;
	private int menuLevel = 0;
	private int currentLevel = -1;
	// private static FileOp file;
	
	
	public static void main(String[] args) {
		String directory = "C:\\Dev\\workspace\\java\\LockedMe.com\\data";
		Log.debug = true;
		LockedMeApp app = new LockedMeApp();
		app.welcomeScreen();
		// file = new FileOp(directory);
		LockedMeActions.setDirectory(directory);
		while(true) {
			try {
				app.nl();
				app.setMenu(app.menuLevel);
				String select = LockedMeActions.userInput.getSelect(app.menuMap);
				if (app.menuLevel==0) {
					switch (select) {
					case "1":
						LockedMeActions.reportFiles();	break;
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
						filename = LockedMeActions.retrieveFilename("New filename");
						LockedMeActions.addFile(filename);	break;
					case "2":
						filename = LockedMeActions.retrieveFilename("Filename to delete");
						LockedMeActions.deleteFile(filename);	break;
					case "3":
						filename = LockedMeActions.retrieveFilename("Search filename");
						LockedMeActions.searchFile(filename);	break;
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