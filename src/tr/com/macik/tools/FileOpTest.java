package tr.com.macik.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class FileOpTest extends FileOp{

	public FileOpTest(String directory) {
		super(directory);

	}

	public static void main(String[] fullFilename) throws FileNotFoundException {
		String directory = "C:\\Dev\\workspace\\java\\LockedMe.com\\data";
		FileOp file = new FileOp(directory);
		if (!file.valid) {
			new File(file.getDirectory()).mkdirs();
			file = new FileOp(directory);
		}
			
		System.out.println("****************************************************************************");
		System.out.print("Test file touch: ");
		System.out.println(file.touch("1.txt"));
		System.out.print("Test append a line to file: ");
		System.out.println(file.append("1.txt", "eine neue Zeile\n"));
		System.out.print("Test file flush: ");
		System.out.println(file.flush("1.txt"));
		System.out.print("Test file touch: ");
		System.out.println(file.touch("2.txt"));
		System.out.print("Test file touch: ");
		System.out.println(file.touch("3.txt"));
		System.out.print("Test file delete: ");
		System.out.println(file.delete("1.txt"));
		List<String> files = file.getFiles();
		System.out.print("Test list of files: ");
		System.out.println(files);
		System.out.println("Directory: "+file.getDirectory());
		// System.out.println("File: "+file.filename);
		
		FileOpTest testInternal = new FileOpTest(directory);		
		System.out.println("****************************************************************************");
		System.out.print("Test splitDirectory 1 (only dir): ");
		System.out.println(testInternal.splitDirectory("C:\\Dev\\workspace\\java\\LockedMe.com\\data"));
		System.out.print("Test splitDirectory 2 (dir+file): ");
		System.out.println(testInternal.splitDirectory("C:\\Dev\\workspace\\java\\LockedMe.com\\data\\2.txt"));
		System.out.print("Test splitDirectory 3 (other dir+file): ");
		System.out.println(testInternal.splitDirectory("C:\\Dev\\workspace\\java\\LockedMe.com\\2.txt"));
		System.out.print("Test splitDirectory 4 (only existing file): ");
		System.out.println(testInternal.splitDirectory("2.txt"));
		System.out.print("Test splitDirectory 5 (only not existing file): ");
		System.out.println(testInternal.splitDirectory("5.txt"));
		System.out.print("Test splitDirectory 6 (empty dir): ");
		System.out.println(testInternal.splitDirectory("   "));
		System.out.println("****************************************************************************");
		System.out.print("Test splitFilename 1 (only dir): ");
		System.out.println(testInternal.splitFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\data"));
		System.out.print("Test splitFilename 2 (dir+file): ");
		System.out.println(testInternal.splitFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\data\\2.txt"));
		System.out.print("Test splitFilename 3 (other dir+file): ");
		System.out.println(testInternal.splitFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\2.txt"));
		System.out.print("Test splitFilename 4 (only existing file): ");
		System.out.println(testInternal.splitFilename("2.txt"));
		System.out.print("Test splitFilename 5 (only not existing file): ");
		System.out.println(testInternal.splitFilename("5.txt"));
		System.out.print("Test splitFilename 6 (empty dir): ");
		System.out.println(testInternal.splitFilename("   "));
		System.out.println("****************************************************************************");
		System.out.print("Test getFilename 1 (only dir): ");
		System.out.println(file.getFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\data"));
		System.out.print("Test getFilename 2 (dir+file): ");
		System.out.println(file.getFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\data\\2.txt"));
		System.out.print("Test getFilename 3 (other dir+file): ");
		System.out.println(file.getFilename("C:\\Dev\\workspace\\java\\LockedMe.com\\2.txt"));
		System.out.print("Test getFilename 4 (only existing file): ");
		System.out.println(file.getFilename("2.txt"));
		System.out.print("Test getFilename 5 (only not existing file): ");
		System.out.println(file.getFilename("5.txt"));
		System.out.print("Test getFilename 6 (empty dir): ");
		System.out.println(file.getFilename("   "));
		System.out.println("****************************************************************************");

		System.out.println("Test checkFilename abc: " + file.checkFilename("abc"));
		System.out.println("Test checkFilename ABC: " + file.checkFilename("ABC"));
		System.out.println("Test checkFilename 123: " + file.checkFilename("123"));
		System.out.println("Test checkFilename _-: " + file.checkFilename("_-"));
		System.out.println("Test checkFilename abc.txt: " + file.checkFilename("abc.txt"));
		System.out.println("Test checkFilename ABC.csv: " + file.checkFilename("ABC.csv"));
		System.out.println("Test checkFilename 123.json: " + file.checkFilename("123.json"));
		System.out.println("Test checkFilename _-.pk: " + file.checkFilename("_-.pk"));
		System.out.println("Test checkFilename xyz-123_ABC.pk: " + file.checkFilename("xyz-123_ABC.pk"));
		System.out.println("Test checkFilename xyz 123 ABC.json: " + file.checkFilename("xyz 123 ABC.json"));
		System.out.println("Test checkFilename xyz!123!ABC.json: " + file.checkFilename("xyz!123!ABC.json"));
		System.out.println("Test checkFilename xyz.123.ABC.json: " + file.checkFilename("xyz.123.ABC.json"));
		System.out.println("Test checkFilename Such_FileName_is_allowed-Extension_should_2-3_Character_or_this_one_at_the_end.json: " + file.checkFilename("Such_FileName_is_allowed-Extension_should_2-3_Character_or_this_one_at_the_end.json"));
		System.out.println("Test checkFilename 1_or_more_allowedCharacters_build_a_FileName.xml: " + file.checkFilename("1_or_more_allowedCharacters_build_a_FileName.xml"));
		System.out.println("Test checkFilename .xml: " + file.checkFilename(".xml"));
		System.out.println("****************************************************************************");
	}

}
