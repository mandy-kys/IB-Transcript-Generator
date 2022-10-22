package IA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Event;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ui1 {
	
	public static final String XLSX_FILE_PATH_STDID = "C:/Transcript generator/input/student_info_template.xlsx";
	public static final String [] XLSX_SEME_PATH = {("C:/Transcript generator/input/diploma_9_1_grades.xlsx"),("C:/Transcript generator/input/diploma_9_2_grades.xlsx"),("C:/Transcript generator/input/diploma_10_1_grades.xlsx"),
			("C:/Transcript generator/input/diploma_10_2_grades.xlsx"),("C:/Transcript generator/input/diploma_11_1_grades.xlsx"),("C:/Transcript generator/input/diploma_11_2_grades.xlsx"),("C:/Transcript generator/input/diploma_12_1_grades.xlsx"),("C:/Transcript generator/input/diploma_12_2_grades.xlsx")};
	public static final String [] XLSX_SEME = {("9_1"),("9_2"),("10_1"),("10_2"),("11_1"),("11_2"),("12_1"),("12_2")};
	public static final String XLSX_STDEXTRAINFO= "C:/Transcript generator/input/student_extra_info.xlsx";
	public static final String XLSX_COMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/complete/poi-generated-file";
	public static final String XLSX_INCOMPLETE_FILE_PATH_OUT = "C:/Transcript generator/output/incomplete/poi-generated-file";
	
	private final static HashMap hsStdScore = new HashMap<String, List<ScoreRecord>>();
	private static HashMap <String, studentID> hsStudentID = new HashMap <String,studentID>(); // Student Information
	private static HashMap <String, studentExtraInfo> hsstdExtra = new HashMap <String, studentExtraInfo>(); // Student Extra Information
	private JFrame frmTranscriptProcessor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ui1 window = new ui1();
					window.frmTranscriptProcessor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public ui1() {
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmTranscriptProcessor = new JFrame();
		frmTranscriptProcessor.setTitle("Transcript processor");
		frmTranscriptProcessor.setBounds(100, 100, 733, 502);
		frmTranscriptProcessor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTranscriptProcessor.getContentPane().setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(new Color(0, 0, 255));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBounds(547, 362, 111, 31);
		frmTranscriptProcessor.getContentPane().add(btnNext);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Process all ");
		rdbtnNewRadioButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(241, 147, 151, 31);
		frmTranscriptProcessor.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnProcessSpecificS = new JRadioButton("Process designated item(s)");
		rdbtnProcessSpecificS.setFont(new Font("Calibri", Font.PLAIN, 20));
		rdbtnProcessSpecificS.setBounds(241, 228, 264, 31);
		frmTranscriptProcessor.getContentPane().add(rdbtnProcessSpecificS);	
		
		ButtonGroup bg =  new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnProcessSpecificS);
		
		JButton btnTrans;
		
		
		JLabel label_8 = new JLabel("12_1");
		label_8.setFont(new Font("Calibri", Font.PLAIN, 18));
		label_8.setBounds(-42, 399, 165, 23);
		frmTranscriptProcessor.getContentPane().add(label_8);
		
		btnNext.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnProcessSpecificS.isSelected()) { 
				    	EnterID IDs = null;
				    	
						try {
							hsStudentID = test.readStudentID(XLSX_FILE_PATH_STDID,hsStudentID);
							IDs = new EnterID(hsStudentID);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				} else if (rdbtnNewRadioButton.isSelected()) {
						long start = System.currentTimeMillis();
						int studentRecord=0;
							try {
								
								hsStudentID = test.readStudentID(XLSX_FILE_PATH_STDID,hsStudentID);
								hsstdExtra = test.readStudentExtraInfo(XLSX_STDEXTRAINFO,hsstdExtra);
								
								if(hsStudentID.size()>0)
								{
									
									hsStudentID.forEach((k,v) -> {
										
										try {
											test.processScore((String) k);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									});
									studentRecord=hsStudentID.size();
									test.outputReports(hsStudentID, hsstdExtra);
								}
								
							   

							}catch(Exception e)
							{
								e.printStackTrace();
							}       
						long end = System.currentTimeMillis();	
					    //System.out.println("rec:" + studentRecord + " time:" + String.valueOf(end-start));
				    	processDone processDone = new processDone(studentRecord,end-start);
				    	processDone.setVisible(true);
				 
				}
			}
		});
	}
}
	


	
		
	