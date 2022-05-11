package tr.com.macik.lockedme;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import tr.com.macik.tools.FileOp;
import tr.com.macik.tools.UserConsoleInput;

public class LockedMeActions {
	static UserConsoleInput userInput = new UserConsoleInput();
	private static FileOp file;

	public static void setDirectory(String directory) {
		file = new FileOp(directory);
		
	}

	private LockedMeActions() {

	}

	static String retrieveFilename(String prompt) {
			String input = "";
			while (!FileOp.checkFilename(input)) {
				input = userInput.getText(prompt);
				if ("".equals(input))
					throw new RuntimeException("Current action canceled!");
			}

			return input;
	}

	static void searchFile(String filename) {
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

	static void deleteFile(String filename) {
		System.out.println("Delete a file with given file name.");
		if (file.exist(filename)) {
			boolean yes = userInput.getDecision("Are you sure?");
			if (yes)
				file.delete(filename);
		} else {
			System.out.println("Given file name doesn't exists.");			
		}
	}

	static void addFile(String filename) {
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

	static void reportFiles() {
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