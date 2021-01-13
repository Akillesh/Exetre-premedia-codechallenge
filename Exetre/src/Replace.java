import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Replace {
	static long before = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	static long start =System.currentTimeMillis();
	private static final String IN_FILE= "C:\\Users\\C.KARTHIK/Downloads\\Exetre premedia\\t8.shakespeare.txt";
	private static final String OUT_FILE ="C:\\Users\\C.KARTHIK/Downloads\\Exetre premedia\\output.txt";
	
	public static void main(String[] args) throws Exception{
		FilePassage filetxt =new FilePassage();
		filetxt.replaceTextFile(IN_FILE,OUT_FILE);
		
		long after =Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long current =after-before;
		long end = System.currentTimeMillis();
		NumberFormat Formatter = new  DecimalFormat("#0.00000");
		System.out.println("Memory Usage: "+(double)current*1024*1024+"mb");
		System.out.println("Running Time: "+Formatter.format((end-start)/1000d)+" seconds");
	}

}
