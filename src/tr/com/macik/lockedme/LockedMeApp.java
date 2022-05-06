package tr.com.macik.lockedme;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import tr.com.macik.tools.Log;
import tr.com.macik.tools.UserConsoleInput;

public class LockedMeApp {
	private HashMap<Character, String> menuMap = new LinkedHashMap<>();
	private UserConsoleInput userInput = new UserConsoleInput();
	private int screenWidth = 80;
	private int menuLevel = 0;
	
	public static void main(String[] args) {
		Log.debug = true;
		LockedMeApp app = new LockedMeApp();
		app.welcomeScreen();
		while(true) {
			try {
				app.nl();
				app.setMenu(app.menuLevel);
				String choice = app.userInput.getChoice(app.menuMap);
				if (app.menuLevel==0) {
					switch (choice) {
					case "1":
						reportFiles();	break;
					case "2":
						app.menuLevel = 2;	break;
					case "x":
						break;
					default:
						
					}
					if ("x".equals(choice))
						break;
				} else if (app.menuLevel==2) {
					switch (choice) {
					case "1":
						addFile();	break;
					case "2":
						deleteFile();	break;
					case "3":
						searchFile();	break;
					case "r":
						app.menuLevel=0; break;
					default:
						
					}
					
				}
			} catch (Exception e) {
				// Try to catch all exceptions if possible and 
				// provide a message to the user
				e.printStackTrace();
			}
		}
		
		System.out.println("Application is finished.");
		System.exit(0);

	}

	private static void searchFile() {
		// TODO Auto-generated method stub
		System.out.println("Search a file with given file name.");
	}

	private static void deleteFile() {
		// TODO Auto-generated method stub
		System.out.println("Delete a file with given file name.");
	}

	private static void addFile() {
		// TODO Auto-generated method stub
		System.out.println("Add a file with given file name.");
	}

	private static void reportFiles() {
		// TODO Auto-generated method stub
		System.out.println("Report files in ascending order.");
	}

	/*private UserConsoleInput userInput() {
		// TODO Auto-generated method stub
		System.out.println("Eingabe? ");
		
	}*/

	private void setMenu(int level) {
		switch(level) {
		case 0: 
			menuMap.clear();
			menuMap.put('1', "Report Files");
			menuMap.put('2', "Business Operations");
			menuMap.put('x', "Exit");
			break;
		case 2: 
			menuMap.clear();
			menuMap.put('1', "Add File");
			menuMap.put('2', "Delete File");
			menuMap.put('3', "Search File");
			menuMap.put('r', "Return");
			break;
		default:
			throw new RuntimeException("Menu not available!");
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
