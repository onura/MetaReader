package com.ceng316.view;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.*;

public class MetaReaderUI{

	private JFrame frame;

	public MetaReaderUI() {

		frame = new JFrame();		
		initialize();
	}
	
	private void initialize() {
	    frame.setBounds(50, 10, 600, 710);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		frame.setTitle("MetaReader v1.0");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		FileUI fileUI = new FileUI();
		tabbedPane.addTab("File", null, fileUI.getFilePanel(), null);
		
		FolderUI folderUI = new FolderUI();
		tabbedPane.addTab("Folder", null, folderUI.getFolderPanel(), null);	
		
		RealTimeUI realTimeUI = new RealTimeUI();
		tabbedPane.addTab("Real Time", null, realTimeUI.getRealTimePanel(), null);	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MetaReaderUI window = new MetaReaderUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
}
