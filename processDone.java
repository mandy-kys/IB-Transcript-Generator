package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

public class processDone extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					processDone frame = new processDone(10,8384);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public processDone() {
		setTitle("Done");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProcessDone = new JLabel("Process Done!");
		lblProcessDone.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblProcessDone.setBounds(137, 85, 164, 68);	
		
		contentPane.add(lblProcessDone);
		
	}
	
	public processDone(int i,long processtime) {
		setTitle("Done");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 750, 200);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String ptStr =String.format("%d min, %d sec",TimeUnit.MILLISECONDS.toMinutes(processtime), TimeUnit.MILLISECONDS.toSeconds(processtime) -
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(processtime)));
		String message="Done! " + "Totoal processed:" + String.valueOf(i) + " reports. " + "Time used: " + ptStr; 
		
		JLabel lblProcessDone = new JLabel();
		lblProcessDone.setText(message);
		lblProcessDone.setFont(new Font("Calibri", Font.PLAIN,20));
		lblProcessDone.setBounds(80, 55, 600, 68);	
		
		contentPane.add(lblProcessDone);
		
		
		
	}
}
