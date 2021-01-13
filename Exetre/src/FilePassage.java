import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FilePassage {


	private	static final Charset CHARSET = Charset.forName("UTF-8"); 
	private static final String FIND_REPLACE_XLSX ="C:\\Users\\C.KARTHIK/Downloads\\Exetre premedia\\french_dictionary";
	private static final int FIND =0;
	private static final int REPLACE=1;
	
	public void replaceTextFile(String infile,String outFile) throws Exception{
	List<FindReplaceStr> list = getFindReplaceList();	
	Path path=Paths.get(infile);
	String str= readFile(path);
	
	Scanner txtFile = new Scanner(new File("C:\\Users\\C.KARTHIK/Downloads\\Exetre premedia\\t8.shakespeare1.txt"));
	TreeMap<String,Integer> map = new TreeMap<String,Integer>();
	while(txtFile.hasNext()){
		String word =txtFile.next();
		int count=1;
		if(map.containsKey(word)){
			count=map.get(word)+1;
		}
		map.put(word, count);
	}
	txtFile.close();
	System.out.println("Frequency of words");
	for(Map.Entry<String, Integer> entry : map.entrySet()){
		System.out.println(entry);
	}
	for(FindReplaceStr item : list){
		str=str.replace(item.findStr, item.replaceStr);
	}
	Path toPath =Paths.get(outFile);
	BufferedWriter Writer = Files.newBufferedWriter(toPath, CHARSET);
	Writer.write(str);
	Writer.close();
	}
	private String readFile(Path path) throws IOException{
		byte[] bytes = Files.readAllBytes(path);
		return new String(bytes,CHARSET);
	}
	private List<FindReplaceStr> getFindReplaceList() throws EncryptedDocumentException, IOException{
		Workbook workbook = WorkbookFactory.create(new File(FIND_REPLACE_XLSX));
		Sheet sheet =(Sheet) workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		int rows = ((org.apache.poi.ss.usermodel.Sheet) sheet).getPhysicalNumberOfRows();
		List<FindReplaceStr> list =new ArrayList<FindReplaceStr>();
		for(int i=0;i<rows;i++){
			Row row=((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(i);
			list.add(new FindReplaceStr(dataFormatter.formatCellValue(row.getCell(FIND)).concat(null)
					dataFormatter.formatCellValue(row.getCell(REPLACE))));
		}
	
	return list;
	}
	}


