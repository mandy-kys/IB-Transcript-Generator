package IA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;

public class test {
	
    public static final String XLSX_FILE_PATH_STDID = "C:/Transcript generator/input/student_info_template.xlsx";
	public static final String [] XLSX_SEME_PATH = {("C:/Transcript generator/input/diploma_9_1_grades.xlsx"),("C:/Transcript generator/input/diploma_9_2_grades.xlsx"),("C:/Transcript generator/input/diploma_10_1_grades.xlsx"),
			("C:/Transcript generator/input/diploma_10_2_grades.xlsx"),("C:/Transcript generator/input/diploma_11_1_grades.xlsx"),("C:/Transcript generator/input/diploma_11_2_grades.xlsx"),("C:/Transcript generator/input/diploma_12_1_grades.xlsx"),("C:/Transcript generator/input/diploma_12_2_grades.xlsx")};
	public static final String [] XLSX_SEME = {("9_1"),("9_2"),("10_1"),("10_2"),("11_1"),("11_2"),("12_1"),("12_2")};
	public static final String XLSX_STDEXTRAINFO= "C:/Transcript generator/input/student_extra_info.xlsx";
	public static final String XLSX_COMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/complete/poi-generated-file";
	public static final String XLSX_INCOMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/incomplete/poi-generated-file";
	private final static HashMap hsStdScore = new HashMap<String, List<ScoreRecord>>();
	private final static int SCORE_SIZE=62;
	public static void main(String[] args) throws IOException {		
		
		try {
			//System.out.println("main start...");
			HashMap <String, studentID> hsStudentID = new HashMap <String,studentID>(); // Student Information
			HashMap <String, studentExtraInfo> hsstdExtra = new HashMap <String, studentExtraInfo>(); // Student Extra Information
			//System.out.println("readStudentID start...");
			hsStudentID = readStudentID(XLSX_FILE_PATH_STDID,hsStudentID);
			hsstdExtra = readStudentExtraInfo(XLSX_STDEXTRAINFO,hsstdExtra);
			
			if(hsStudentID.size()>0)
			{
				
				//ArrayList scoreArry = new ArrayList(hsStudentID.size()); //scoreArry contains HashMap hsStdScore
				hsStudentID.forEach((k,v) -> {
					
					try {
						processScore((String) k);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//scoreArry.add(hsStdScore);
				});
				//System.out.println("outputReports start...");
				outputReports(hsStudentID, hsstdExtra);
			}
			
		   

		}catch(Exception e)
		{
			e.printStackTrace();
		}       
	}
	
	public static HashMap readStudentID(String std_path,HashMap<String, studentID> hs) throws Exception{ // studentID-->對應String的studentID的attribute. 他的Value是studentID的attribute. 所有這個class裡面的instance
		Workbook wbstdid = WorkbookFactory.create(new File(std_path));
        Iterator<Sheet> sheetIterator = wbstdid.sheetIterator();    
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();       
	        for (Row row: sheet) {
	        	    if(row.getRowNum()>0)
	        	    {
	        	    	studentID ID = new studentID();
	        	    	DataFormatter dfm = new DataFormatter();	        	    	
	        	    	ID.setID((String)dfm.formatCellValue(row.getCell(1)));        	    	
	        	    	ID.setSLast(row.getCell(3).getStringCellValue());
	        	    	ID.setSFirst(row.getCell(4).getStringCellValue());	        	    	
	        	    	ID.setSPreferred(row.getCell(5).getStringCellValue());
	        	    	ID.setSChinese(row.getCell(2).getStringCellValue());
	        	    	ID.setSGender(row.getCell(7).getStringCellValue());
	        	    	ID.setSBday((String)dfm.formatCellValue(row.getCell(10)));
	        	    	ID.setSNationality(row.getCell(11).getStringCellValue());
	        	    	ID.setPChinese(row.getCell(13).getStringCellValue());
	        	    	ID.setPEnglish(row.getCell(14).getStringCellValue());
	        	    	String info = ID.getID()+"|"+ID.getSLast() + "|" + ID.getSFirst() + "|" + ID.getSChinese() + "|" + ID.getSGender() + "|" + ID.getSBday() + "|" + ID.getSNationality() + "|" + ID.getPChinese() + "|" + ID.getPEnglish();
	        	    	hs.put(ID.getID(), ID);
	        	    }	                
	            }        
        }
        wbstdid.close();
       
		return hs;
		
	}
	
	public static HashMap readStudentExtraInfo(String stdextrainfo_path, HashMap<String, studentExtraInfo> hsstdExtra) throws Exception{
		Workbook stdextrainfo = WorkbookFactory.create(new File(stdextrainfo_path));
		 Iterator<Sheet> sheetIterator = stdextrainfo.sheetIterator();
		{
			 while (sheetIterator.hasNext()) {
		            Sheet sheet = sheetIterator.next();   
			        for (Row row: sheet) {
			        	    if(row.getRowNum()>=0)
			        	    {
			        	    	studentExtraInfo IFO = new studentExtraInfo();
			        	    	DataFormatter dfm = new DataFormatter();	        	    	
			        	    	IFO.setID((String)dfm.formatCellValue(row.getCell(0)));
			        	    	IFO.setEntered(row.getCell(2).getStringCellValue());
			        	    	IFO.setDeparted(row.getCell(3).getStringCellValue());
			        	    	IFO.setGraduation(row.getCell(4).getStringCellValue());
			        	    	IFO.setCounselor(row.getCell(5).getStringCellValue());			        	    	
			        	    	hsstdExtra.put(IFO.getID(), IFO);	        	    	
			        	    		
			        	    }	                
			            }        
			 }
		}
		return hsstdExtra;
	}
	
	public static void processScore(String stdID) throws Exception{
		String seme_score_file_path="";
//		
		String seme="";
		List<ScoreRecord> scordRecordList = new ArrayList<ScoreRecord>();
		for(int i=0;i<8;i++)
		{
			seme_score_file_path = XLSX_SEME_PATH[i];
			seme=XLSX_SEME[i];
			Workbook wbsemi = WorkbookFactory.create(new File(seme_score_file_path));	        
	        Iterator<Sheet> sheetIterator = wbsemi.sheetIterator();
	        ScoreRecord sr = null;
	        while (sheetIterator.hasNext()) {
	        	Sheet sheet = sheetIterator.next();
	        	for (Row row: sheet) {
	        		for(Cell cell:row)
	        		{
	        			if(row.getCell(8) != null) //student id != null
	        			{
	        				DataFormatter dfmID = new DataFormatter(); 					
	        				String cid = dfmID.formatCellValue(cell);
	        				if(cid.contentEquals(stdID))
	        				{
	        					String className=dfmID.formatCellValue(row.getCell(1));
	        					String score=dfmID.formatCellValue(row.getCell(10));
	        					sr = new ScoreRecord(stdID,seme,className,score);		        					
	        					scordRecordList.add(i,sr);
	        					//System.out.println("StdID: " + stdID + " " + className +" " +" "+ seme +" "+ score);
	        				}	        				
	        			}
	        		}	        		
	        	}	        	
	        }
	        
	        wbsemi.close();	        
	        hsStdScore.put(stdID, scordRecordList);
		}			

	}
	
	
		
	public static void outputReports(HashMap hsStudentID, HashMap hsstdExtra) throws Exception{
		Set keys = hsStudentID.keySet();
		FileSystem system = FileSystems.getDefault();
        Path original = system.getPath("C:\\Transcript generator\\input\\transcript_template.xlsx");
        
		for(Object id : keys) {
			String strID = (String)id;
			studentID stdIDObj=null;
			if(hsStudentID.get(strID) != null)
			{
				stdIDObj= (studentID)hsStudentID.get(strID);
				studentExtraInfo ifo = (studentExtraInfo)hsstdExtra.get(strID);
				List<ScoreRecord> screcList = (List)hsStdScore.get(strID);
				String file_output = XLSX_COMPLETE_FILE_PATH_OUT + "_" + strID + ".xlsx";
				if((stdIDObj.getCompleteness() ==null || stdIDObj.getCompleteness().equalsIgnoreCase("NG")) || ifo.checkBlankAttribute().equalsIgnoreCase("FAIL")
						|| screcList.size()<SCORE_SIZE)
					file_output = XLSX_INCOMPLETE_FILE_PATH_OUT + "_" + strID + ".xlsx";
				Path target = system.getPath(file_output);
				Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);

				FileInputStream inputStream = new FileInputStream(new File(file_output));
	            Workbook workbook = WorkbookFactory.create(inputStream);
				Sheet sheet = workbook.getSheetAt(0);
			
				Cell cell = sheet.getRow(4).getCell(1);
				cell.setCellValue(stdIDObj.getSLast());
				sheet.getRow(4).getCell(5).setCellValue(stdIDObj.getPEnglish());
				sheet.getRow(5).getCell(1).setCellValue(stdIDObj.getSFirst());
				sheet.getRow(5).getCell(5).setCellValue(stdIDObj.getPChinese());                 // sheet method needs to be changed				
				sheet.getRow(6).getCell(1).setCellValue(stdIDObj.getSPreferred());                                                                     
				sheet.getRow(7).getCell(1).setCellValue(stdIDObj.getSChinese());
				sheet.getRow(9).getCell(1).setCellValue(stdIDObj.getSGender());
				sheet.getRow(10).getCell(1).setCellValue(stdIDObj.getID());
				sheet.getRow(11).getCell(1).setCellValue(stdIDObj.getSBday());
				sheet.getRow(12).getCell(1).setCellValue(stdIDObj.getSNationality());            //sheet.getRow(6).getCell(5).setCellValue(stdIDObj.getAddress());
			
				sheet.getRow(12).getCell(5).setCellValue(ifo.getCounselor());
				sheet.getRow(9).getCell(5).setCellValue(ifo.getEntered());
				sheet.getRow(10).getCell(5).setCellValue(ifo.getDeparted());
				sheet.getRow(11).getCell(5).setCellValue(ifo.getGraduation()); 
				sheet.getRow(12).getCell(5).setCellValue(ifo.getCounselor()); 
				
				//Write score
				HashMap<String,List<DeltaRecord>> scoreHS = processDelta(screcList);                         //HashMap<String,List<DeltaRecord>> scoreHS = new HashMap<String, List<DeltaRecord>>();
				
				//Write Grade 9 score
				List grade9 = scoreHS.get("9");	
				Collections.reverse(grade9);
				for(int a=0;a<grade9.size();a++)
				{					
					DeltaRecord drtmp=(DeltaRecord)grade9.get(a);
					sheet.getRow(a+16).getCell(0).setCellValue(drtmp.getClassName());
					try {
						sheet.getRow(a+16).getCell(4).setCellValue(Integer.parseInt(drtmp.getScore1()));
					}catch(NumberFormatException e){
						sheet.getRow(a+16).getCell(4).setCellValue(drtmp.getScore1());
					}
					try {
						sheet.getRow(a+16).getCell(5).setCellValue(Integer.parseInt(drtmp.getScore2()));
					}catch(NumberFormatException e){
						sheet.getRow(a+16).getCell(5).setCellValue(drtmp.getScore2());
					}
				}
				//Write Grade 10 score
				List grade10 = scoreHS.get("10");	
				Collections.reverse(grade10);
				for(int b=0;b<grade10.size();b++)
				{					
					DeltaRecord drtmp=(DeltaRecord)grade10.get(b);
					sheet.getRow(b+26).getCell(0).setCellValue(drtmp.getClassName());
					try {
						sheet.getRow(b+26).getCell(4).setCellValue(Integer.parseInt(drtmp.getScore1()));
					}catch(NumberFormatException e){
						sheet.getRow(b+26).getCell(4).setCellValue(drtmp.getScore1());
					}
					try {
						sheet.getRow(b+26).getCell(5).setCellValue(Integer.parseInt(drtmp.getScore2()));
					}catch(NumberFormatException e){
						sheet.getRow(b+26).getCell(5).setCellValue(drtmp.getScore2());
					}
				}
				
				//Write Grade 11 score
				List grade11 = scoreHS.get("11");	
				Collections.reverse(grade11);
				for(int c=0;c<grade11.size();c++)
				{					
					DeltaRecord drtmp=(DeltaRecord)grade11.get(c);
					sheet.getRow(c+35).getCell(0).setCellValue(drtmp.getClassName());
					try {
						sheet.getRow(c+35).getCell(4).setCellValue(Integer.parseInt(drtmp.getScore1()));
					}catch(NumberFormatException e){
						sheet.getRow(c+35).getCell(4).setCellValue(drtmp.getScore1());
					}
					try {
						sheet.getRow(c+35).getCell(5).setCellValue(Integer.parseInt(drtmp.getScore2()));
					}catch(NumberFormatException e){
						sheet.getRow(c+35).getCell(5).setCellValue(drtmp.getScore2());
					}
					
				}
					
				
				//Write Grade 12 score
				List grade12 = scoreHS.get("12");	
				Collections.reverse(grade12);
				for(int d=0;d<grade12.size();d++)
				{					
					DeltaRecord drtmp=(DeltaRecord)grade12.get(d);
					
					sheet.getRow(d+43).getCell(0).setCellValue(drtmp.getClassName());
					try {
						sheet.getRow(d+43).getCell(4).setCellValue(Integer.parseInt(drtmp.getScore1()));
					}catch(NumberFormatException e)
					{
						sheet.getRow(d+43).getCell(4).setCellValue(drtmp.getScore1());
					}
					try {
						sheet.getRow(d+43).getCell(5).setCellValue(Integer.parseInt(drtmp.getScore2()));
					}catch(NumberFormatException e) {
						sheet.getRow(d+43).getCell(5).setCellValue(drtmp.getScore2());
					}
				}
				
				sheet.getRow(16).getCell(6).setCellFormula("IF(E17<3,0,0.5)+IF(F17<3,0,0.5)");
				sheet.getRow(17).getCell(6).setCellFormula("IF(E18<3,0,0.5)+IF(F18<3,0,0.5)");
				sheet.getRow(18).getCell(6).setCellFormula("IF(E19<3,0,0.5)+IF(F19<3,0,0.5)");
				sheet.getRow(19).getCell(6).setCellFormula("IF(E20<3,0,0.5)+IF(F20<3,0,0.5)");
				sheet.getRow(20).getCell(6).setCellFormula("IF(E21<3,0,0.5)+IF(F21<3,0,0.5)");
				sheet.getRow(21).getCell(6).setCellFormula("IF(E22<3,0,0.5)+IF(F22<3,0,0.5)");
				sheet.getRow(22).getCell(6).setCellFormula("IF(E23<3,0,0.5)+IF(F23<3,0,0.5)");
				sheet.getRow(23).getCell(6).setCellFormula("IF(E24<3,0,0.5)+IF(F24<3,0,0.5)");
				sheet.getRow(24).getCell(6).setCellFormula("IF(E25<3,0,0.5)+IF(F25<3,0,0.5)");
				sheet.getRow(26).getCell(6).setCellFormula("IF(E27<3,0,0.5)+IF(F27<3,0,0.5)");
				sheet.getRow(27).getCell(6).setCellFormula("IF(E28<3,0,0.5)+IF(F28<3,0,0.5)");
				sheet.getRow(28).getCell(6).setCellFormula("IF(E29<3,0,0.5)+IF(F29<3,0,0.5)");
				sheet.getRow(29).getCell(6).setCellFormula("IF(E30<3,0,0.5)+IF(F30<3,0,0.5)");
				sheet.getRow(30).getCell(6).setCellFormula("IF(E31<3,0,0.5)+IF(F31<3,0,0.5)");
				sheet.getRow(31).getCell(6).setCellFormula("IF(E32<3,0,0.5)+IF(F32<3,0,0.5)");
				sheet.getRow(32).getCell(6).setCellFormula("IF(E33<3,0,0.5)+IF(F33<3,0,0.5)");
				sheet.getRow(33).getCell(6).setCellFormula("IF(E34<3,0,0.5)+IF(F34<3,0,0.5)");
				sheet.getRow(35).getCell(6).setCellFormula("IF(E36<3,0,0.5)+IF(F36<3,0,0.5)");
				sheet.getRow(36).getCell(6).setCellFormula("IF(E37<3,0,0.5)+IF(F37<3,0,0.5)");
				sheet.getRow(37).getCell(6).setCellFormula("IF(E38<3,0,0.5)+IF(F38<3,0,0.5)");
				sheet.getRow(38).getCell(6).setCellFormula("IF(E39<3,0,0.5)+IF(F39<3,0,0.5)");
				sheet.getRow(39).getCell(6).setCellFormula("IF(E40<3,0,0.5)+IF(F40<3,0,0.5)");
				sheet.getRow(40).getCell(6).setCellFormula("IF(E41<3,0,0.5)+IF(F41<3,0,0.5)");
				sheet.getRow(41).getCell(6).setCellFormula("IF(E42<3,0,0.5)+IF(F42<3,0,0.5)");
				sheet.getRow(43).getCell(6).setCellFormula("IF(E44<3,0,0.5)+IF(F44<3,0,0.5)");
				sheet.getRow(44).getCell(6).setCellFormula("IF(E45<3,0,0.5)+IF(F45<3,0,0.5)");
				sheet.getRow(45).getCell(6).setCellFormula("IF(E46<3,0,0.5)+IF(F46<3,0,0.5)");
				sheet.getRow(46).getCell(6).setCellFormula("IF(E47<3,0,0.5)+IF(F47<3,0,0.5)");
				sheet.getRow(47).getCell(6).setCellFormula("IF(E48<3,0,0.5)+IF(F48<3,0,0.5)");
				sheet.getRow(48).getCell(6).setCellFormula("IF(E49<3,0,0.5)+IF(F49<3,0,0.5)");
				sheet.getRow(49).getCell(6).setCellFormula("IF(E50<3,0,0.5)+IF(F50<3,0,0.5)");
				sheet.getRow(51).getCell(6).setCellFormula("SUM(G17:G50)");
				
				
				
				inputStream.close();
				
	            FileOutputStream outputStream = new FileOutputStream(file_output);
	            FormulaEvaluator  evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	            evaluator.evaluateAll();				
	            workbook.write(outputStream);	
	            
	            outputStream.close();
				
			}
					
		}
	} 
	public static HashMap<String,List<DeltaRecord>> processDelta(List<ScoreRecord> allrecord) {
		HashMap<String,List<DeltaRecord>> deltaResult = new HashMap<String,List<DeltaRecord>>();
		for(int j=0;j<allrecord.size();j++)
		{
			ScoreRecord srtmp = (ScoreRecord)allrecord.get(j);
			String seme = srtmp.getSeme();
			StringTokenizer stk = new StringTokenizer(seme,"_");
			String grade = stk.nextToken();
			String seq = stk.nextToken();
			String className=srtmp.getClassName();
			boolean classExist=false;
			if(deltaResult.get(grade) != null) // check if grade exists
			{
				List<DeltaRecord> drlist = deltaResult.get(grade);
				for(int k=0;k<drlist.size();k++)
				{
					DeltaRecord tmpObj = drlist.get(k);
					if(tmpObj.getClassName().equalsIgnoreCase(className))       //if current class name equals a class name that already exists
					{
						classExist=true;
							tmpObj.setScore2(srtmp.getScore());
					}
				}
				if(!classExist)                                                    //if that class does not equal any class name that already exists
				{
					DeltaRecord drObj = new DeltaRecord();
					drObj.setClassName(className);
	
						drObj.setScore1(srtmp.getScore());
					drlist.add(drObj);
					
				}
				
				deltaResult.put(grade, drlist);
			}else {                                                                  //grade does not already exist 直接放 因為是根據年級順序跑的
				List<DeltaRecord> drlist = new ArrayList<DeltaRecord>();
				DeltaRecord drObj = new DeltaRecord();
				drObj.setClassName(className);
				drObj.setScore1(srtmp.getScore());
				drlist.add(drObj);
				deltaResult.put(grade, drlist);
			}		
				
		}
		return deltaResult;
	}
	
	
}
