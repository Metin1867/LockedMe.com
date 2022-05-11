package tr.com.macik.tools;

import java.util.HashMap;

public class UserConsoleInputTest {

	public static void main(String[] args) {
		UserConsoleInput userInput = new UserConsoleInput();

		while (true) {
			HashMap<Integer, String> userInputMap = new HashMap<>();
			userInputMap.put(1, "Texteingabe");
			userInputMap.put(2, "Scannereingabe integer value");
			userInputMap.put(3, "Fliesskommazahl");
			userInputMap.put(4, "Ganze Zahl");
			userInputMap.put(5, "Emailliste");
			userInputMap.put(6, "Yes/No");
			userInputMap.put(999, "Exit");
			
			int choice = new Integer(userInput.getSelect(userInputMap));
			System.out.println("Auswahl: >" + choice + "<");
			
			switch (choice) {
			case 1:	String s = userInput.getText("Eingabe ein Text");
					System.out.println("Bestätigung Eingabe: >" + s + "<");
					break;
			case 2:	System.out.print("Eingabe direkt über Scanner: ");
					int i = UserConsoleInput.getScanner().nextInt();
					System.out.println("Bestätigung Eingabe: >" + i + "<");
					break;
			case 3:	Double d = userInput.getNumber("Eingabe Fliesskommazahl");
					System.out.println("Bestätigung Eingabe: >" + d + "<");
					break;
			case 4:	Long w = userInput.getWholeNumber("Eingabe ganze Zahl");
					System.out.println("Bestätigung Eingabe: >" + w + "<");
					break;
			case 5:	String[] emails = userInput.getLines("Email-Liste", ",");
					for (String email : emails)
						System.out.println(">" + email + "<");
					break;
			case 6:	boolean b = userInput.getDecision("Denkst du positiv?");
					System.out.println("Bestätigung Eingabe: >" + b + "<");
					break;
			default:
					;	// exit the main-methode
					System.exit(-1);
			}
		}
	}

}
