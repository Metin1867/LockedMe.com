package tr.com.macik.tools;

final public class Info {
	/*Info oneClass;

	private Info() {
		oneClass = this;
	}*/

	public static void main(String[] args) {
		Info.printOut();
	}

	private static void printOut() {
		System. out. println("Current JVM version - " + javaVersion());
	}

	private static String javaVersion() {
		// return Runtime.class.getPackage().getImplementationVersion();
		return System.getProperty("java.version");
	}

}
