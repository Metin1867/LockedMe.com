package tr.com.macik.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOp {
	private String directory;
    private String filename;
    public final boolean valid;
	
	public FileOp(String directory) {
	    Path path;
	    this.filename = null;
		try {
		    File f = new File(directory);
		    if (f.isDirectory()) 
		    	;
		    else if (f.isFile()){
		    	path = Paths.get(directory);
		    	directory = path.getParent().toString();
		    	this.filename = path.getFileName().getName(path.getFileName().getNameCount()-1).toString();
		    } else {
		    	path = Paths.get(directory);
		    	String temp = path.getParent().toString();
		    	this.filename = path.getFileName().toString();
		    	System.out.println("Create this directory "
		    						+ this.filename
		    						+ " or something is wrong in the flow!");
		    }
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			e.printStackTrace();
			throw new RuntimeException("Some file operation error occured!");
		}

		this.directory = directory;
		if (this.filename == null)
			valid = true;
		else
			valid = false;
	}

	public boolean delete(String filename) {
		try {
			Files.delete(Paths.get(getFullFilename(filename)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean flush(String filename) {
		try {
			new FileWriter(getFullFilename(filename), false).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean append(String filename, String line) {
		/*if (line.charAt(line.length()-1) != '\n')
			line += line + '\n';*/
		try {
			Files.write(Paths.get(getFullFilename(filename)), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean touch(String filename) {
		File newFile = new File(getFullFilename(filename));
	    try {
	    	return newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    return false;
	}

	public boolean search(String filename) {
		List<String> filelist;
		try {
			filelist = getFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return filelist.contains(filename);		
	}

	public List<String> getContent(String filename) {
	    Path path = Paths.get(getFullFilename(filename));
	    try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return null;
	}

	public boolean exist(String filename) {
		return search(filename);
	}

	private String getFullFilename(String filename) {
		return this.directory+File.separator+filename;
	}

	public List<String> getFiles() throws FileNotFoundException {
		List<String> results = new ArrayList<String>();

		File[] files = new File(this.directory).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		if (files == null)
			throw new FileNotFoundException("Directory not exists!");
			
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		
		return results;
	}

	/*
	 * getFilename returns a valid filename.
	 * If the path exists in the filename it will be separated and validated to the existing directory.
	 * If the directories are not equal will be returned a null String.
	 * If the String is empty it will returned also a null String.
	 * In regular cases a filename will be returned.
	 * A file validation can be implemented also in this method.
	 */
	public String getFilename(String filename) {
		if ("".equals(filename.trim()))
			return null;
		String d = splitDirectory(filename);
		if (d != null && !this.directory.equals(d))
			return null;
		String f = splitFilename(filename);
		// System.out.println(d + " ||| " + f);
		if (f == null || d == null && filename.length()>f.length())
			return null;
		return f;
	}

	protected String splitFilename(String filename) {
		if ("".equals(filename.trim()))
			return null;
		
	    File f = new File(filename);
	    if (f.isDirectory())
	    	;
	    else {
	    	Path path = Paths.get(filename);
	    	return path.getFileName().getName(path.getFileName().getNameCount()-1).toString();
	    }

    	return null;
	}

	protected String splitDirectory(String directory) {
		if ("".equals(directory.trim()))
			return null;

		File f = new File(directory);
	    if (f.isDirectory())
	    	return directory;
	    else if (f.isFile()){
	    	Path path = Paths.get(directory);
	    	directory = path.getParent().toString();
	    	return directory;
	    }

		return null;
	}
	
	public static boolean checkFilename(String filename) {
		String regex = "^[a-zA-Z0-9_-]+\\.([a-z]{2,3}|json)$";
		Pattern pattern = Pattern.compile(regex);
	      //Retrieving the matcher object
	      Matcher matcher = pattern.matcher(filename);

	      return matcher.find();
	}

	public String getDirectory() {
		return directory;
	}
	
}
