package tr.com.macik.lockedme;

import java.util.Arrays;
import java.util.HashMap;

import tr.com.macik.tools.Log;
import tr.com.macik.tools.UserConsoleInput;

public class LockedMeApp {
	private HashMap<Character, String> menuMap = new HashMap<>();
	private UserConsoleInput userInput = new UserConsoleInput();
	private int screenWidth = 80;
	
	public static void main(String[] args) {
		Log.debug = true;
		LockedMeApp app = new LockedMeApp();
		app.welcomeScreen();
		app.nl();
		app.showMenu();
		app.userInput();

		System.exit(0);

	}

	private void userInput() {
		// TODO Auto-generated method stub
		System.out.println("Eingabe? ");
		
	}

	private void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("1: report files");
		System.out.println("2: business operations");
		System.out.println("3: close");
		
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
