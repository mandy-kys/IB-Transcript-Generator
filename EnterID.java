package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.sun.glass.events.MouseEvent;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.ws.api.server.Container;

import javafx.scene.control.ComboBox;

import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;



import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
public class EnterID{
	public static final String XLSX_FILE_PATH_STDID = "C:/Transcript generator/input/student_info_template.xlsx";
	public static final String [] XLSX_SEME_PATH = {("C:/Transcript generator/input/diploma_9_1_grades.xlsx"),("C:/Transcript generator/input/diploma_9_2_grades.xlsx"),("C:/Transcript generator/input/diploma_10_1_grades.xlsx"),
			("C:/Transcript generator/input/diploma_10_2_grades.xlsx"),("C:/Transcript generator/input/diploma_11_1_grades.xlsx"),("C:/Transcript generator/input/diploma_11_2_grades.xlsx"),("C:/Transcript generator/input/diploma_12_1_grades.xlsx"),("C:/Transcript generator/input/diploma_12_2_grades.xlsx")};
	public static final String [] XLSX_SEME = {("9_1"),("9_2"),("10_1"),("10_2"),("11_1"),("11_2"),("12_1"),("12_2")};
	public static final String XLSX_STDEXTRAINFO= "C:/Transcript generator/input/student_extra_info.xlsx";
	public static final String XLSX_COMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/complete/poi-generated-file";
	public static final String XLSX_INCOMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/incomplete/poi-generated-file";
	

	private final static HashMap hsStdScore = new HashMap<String, List<ScoreRecord>>();
	private static HashMap <String, studentID> hsStudentIDFinal = new HashMap <String,studentID>(); // Student Information
  /**
   * @wbp.parser.entryPoint
   */
  public static void main(String[] args) {
  //new EnterID(null);
  }
  /**
   * @wbp.parser.entryPoint
   */
  public EnterID(HashMap<String,studentID> hsStudentID){
  JFrame frame = new JFrame("Choose Student(s)");
  JPanel panel = new JPanel();
  String data1[][]= new String[hsStudentID.size()][10];
  Set entries = hsStudentID.keySet();
  Object[] arr=entries.toArray();
  Arrays.sort(arr);
 
  ArrayList<studentID> valueArry = new ArrayList<studentID>();
 
  for(Object key:arr){
      valueArry.add((studentID)hsStudentID.get((String)key));
  } 
  
  for(int i=0;i<valueArry.size();i++)
  {
	  studentID stdId = (studentID)valueArry.get(i);
	  data1[i][0]= stdId.getID();
	  data1[i][1]= stdId.getSLast();
	  data1[i][2]= stdId.getSFirst();
	  data1[i][3]= stdId.getSPreferred();
	  data1[i][4]= stdId.getSChinese();
	  data1[i][5]= stdId.getSGender();
	  data1[i][6]= stdId.getSBday();
	  data1[i][7]= stdId.getSNationality();
	  data1[i][8]= stdId.getPChinese();
	  data1[i][9]= stdId.getPEnglish();
	 
  }
  
  
  
  String  column0[] = {"ID", "SLast", "SFirst", "SPreferred", "SChinese", "SGender", "SBday", "SNationality", "PChinese", "PEnglish"};
  JTable table = new JTable(data1,column0);
  DefaultTableModel tableModel = new DefaultTableModel(data1, column0) {

      @Override
      public boolean isCellEditable(int row, int column) {
          return false;
      }
  };

  table.setModel(tableModel);
  JTableHeader header = table.getTableHeader();
  header.setBackground(Color.yellow);
  JScrollPane pane = new JScrollPane(table);
  int horizontalPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
  int verticalPolicy =JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  table.setRowSelectionAllowed(true);
  table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

 
  panel.add(pane);

  JPanel contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(150,10,100,20));
  
  frame.getContentPane().add(panel,BorderLayout.WEST);
  frame.getContentPane().add(contentPane,BorderLayout.CENTER);
  contentPane.setLayout(null);
  
  JButton btnGenerate = new JButton("Generate");
	btnGenerate.setFont(new Font("Calibri", Font.PLAIN, 12));
	btnGenerate.setBounds(26, 545, 90 ,30);
	contentPane.add(btnGenerate);
	
	btnGenerate.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	if(table.getSelectedRowCount()<1)
	    	{
	    		JDialog d = new JDialog(frame,"Must select at least one ID!", true);
	
	    		d.setLocationRelativeTo(frame);	    	
	    		d.setVisible(true);
	    		
	    	}else {
	    		try {
		    		HashMap <String, studentExtraInfo> hsstdExtra = new HashMap <String, studentExtraInfo>(); 
	    			hsstdExtra = test.readStudentExtraInfo(XLSX_STDEXTRAINFO,hsstdExtra);
	    			int[] selectIntArray = table.getSelectedRows();
		    		for(int i=0;i<table.getSelectedRowCount(); i++)
		    		{	    		
		    			int rowid = selectIntArray[i];
		    			String strstdid = table.getModel().getValueAt(rowid,0).toString();
		    			studentID stdID = hsStudentID.get(strstdid);
		    			hsStudentIDFinal.put(strstdid, stdID);
		    		}
		    		if(hsStudentIDFinal.size()>0)
	    			{
	    				
		    			hsStudentIDFinal.forEach((k,v) -> {
	    					
	    					try {
	    						test.processScore((String) k);
	    					} catch (Exception e1) {
	    						// TODO Auto-generated catch block
	    						e1.printStackTrace();
	    					}
	    				});
	    				test.outputReports(hsStudentIDFinal, hsstdExtra);
	    				processDone processDone = new processDone();
				    	processDone.setVisible(true);
		    		}
	    		}catch(Exception exp)
		    	{
		    		exp.printStackTrace();
		    	}       
	    			
	    		}	    		
	    		
	    	}
	     
	    });

  JButton btnClear = new JButton("Clear");
  btnClear.setFont(new Font("Calibri", Font.PLAIN, 12));
  btnClear.setBounds(26, 495, 90 ,30);
  contentPane.add(btnClear);
	
  btnClear.addActionListener(new ActionListener() 
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  Object JDialog;
		if(table.getSelectedRowCount()>0) {
			  table.clearSelection();
		  }else {
		JDialog d2 = new JDialog(frame,"Already clear", true);
		d2.setLocationRelativeTo(frame);	    	
		d2.setVisible(true);
	  }
	 }
  });

  
	
  frame.setSize(620,650);
  frame.setUndecorated(true);  
  frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setVisible(true);
  }
}



