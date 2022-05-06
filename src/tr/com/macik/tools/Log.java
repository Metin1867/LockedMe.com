package tr.com.macik.tools;

public class Log {
	private static Log instance;
	public static boolean debug = false;
	private static String itemDlm = ",";
	private static int[][] dataSets = {{3, 1, 6, 2}, {0, 3, 1, 6, 2, 2, 7},
			{10, 9, 2, 6, 3, 7, 101, 18},
			{3, 10, 2, 1, 10}};

	private Log() {
	}

	public static void main(String[] args) {
		print(dataSets[0]);
		nl();
		print(dataSets);
		
		debug = true;
		int i=0, j=3;
		int[] data = dataSets[2]; 
		Log.debug(new String[][] {{"Row/Column; Data[idx]", ""+i, "/", ""+j,
			"; "+data[j+i], "[", ""+(j+i), "]"}
 						 });
	}

	public static <T> Log print(T text) {
		System.out.print(text);
		return instance;
	}

	public static void nl() {
		System.out.println();
	}

	private static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}

	public static Log print(int[] arr) {
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			if (i<arr.length-1)
				System.out.print(", ");
		}
		return instance;
	}

	private static Log print(int[][] arr) {
		for (int i=0; i<arr.length; i++) {
			System.out.print(i + "row: ");
			print(arr[i]);
			System.out.println();
		}
		return instance;
	}

	public static Log set() {
		itemDlm = ",";
		return instance;
	}
	
	public static Log set(String option, String optionValue) {
		switch (option) {
		case "dlm": itemDlm = optionValue;
		default:
				;	// no error please!
		}
		return instance;
	}
	
	public static <T> Log print(T[] arr) {
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]);
			if (i<arr.length-1)
				System.out.print(itemDlm);
		}
		return instance;
	}

	public static void debug(String key, String value) {
		if (debug) {
			System.out.print(key);
			System.out.print(": ");
			System.out.println(value);
		}
	}

	public static void debug(String key, long value) {
		debug(key, ""+value);
		
	}

	public static void debug(String[][] data) {
		if (debug) {
			for (int i=0; i<data.length; i++) {
				System.out.print(data[i][0]);
				System.out.print(": ");
				for (int j=1; j<data[i].length; j++) {
					System.out.print(data[i][j]);
					/*if (j<data[i].length-1)
						System.out.print("; ");*/
				}
				nl();
			}
		}
	}

	public static <T> void debug(String key, int[] values) {
		if (debug) {
			System.out.print(key);
			System.out.print(": ");
			boolean start = true;
			for (int item : values)
				if (start) {
					System.out.print(item);
					start = false;
				} else
					System.out.print(", "+item);
			nl();
		}

	}

}
