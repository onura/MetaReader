package com.ceng316.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import com.ceng316.controller.RealTimeController;
import com.ceng316.model.metafile.FileType;



import java.nio.file.Paths;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class RealTimeUI extends JPanel implements ActionListener {

	private FileData fileData;
	private RealTimeController realTimeController;
	
	private JPanel realTimePanel;
	private JTextField txtFolderPath;
	private JButton btnSetFolder;
	private JButton btnSearch;
	private JComboBox<String> fileNames;
	private JTextField textSearch;
	private JComboBox<String> fileType;
	private JLabel lblSearchSomething;
	private JLabel lblPage;
	private JTextField textPageNr;
	private JLabel lblChooseFile;
	private JButton btnSaveMetadata;
	private JCheckBox chckbxSaveAll;
	private JLabel lblLoading;
	private JLabel lblFileInfo;
	
	public RealTimeUI() {		
		
		realTimePanel = new JPanel();
		
		fileData = new FileData();
		
		txtFolderPath = new JTextField();
		txtFolderPath.setText("/tmp");
		txtFolderPath.addActionListener(this);

		btnSetFolder = new JButton("Set Folder");		
		btnSetFolder.addActionListener(this);

		btnSearch = new JButton("Search Metadata");
		btnSearch.addActionListener(this);
		
		fileNames = new JComboBox<String>();
		fileNames.addActionListener(this);
		
		textSearch = new JTextField();
		textSearch.setColumns(10);
		textSearch.addActionListener(this);
		
		fileType = new JComboBox<String>();
		setFileTypes();
		
		lblSearchSomething = new JLabel("Search Something");
		
		lblPage = new JLabel("Page Number");
		
		textPageNr = new JTextField();
		textPageNr.setColumns(10);
		textPageNr.setText("1");
		
		JLabel lblType = new JLabel("Type");
		
		lblChooseFile = new JLabel("Choose File");
		
		btnSaveMetadata = new JButton("Save Metadata");
		btnSaveMetadata.addActionListener(this);
		
		chckbxSaveAll = new JCheckBox("Save ALL");
		
		 ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/loader.gif"));
		 lblLoading = new JLabel();
		 lblLoading.setIcon(imageIcon);	
		 lblLoading.setVisible(false);
		
		lblFileInfo = new JLabel("");
		
		
		
		GroupLayout gl_realTimePanel = new GroupLayout(realTimePanel);
		gl_realTimePanel.setHorizontalGroup(
			gl_realTimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_realTimePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(fileNames, 0, GroupLayout.DEFAULT_SIZE, 380)
								.addComponent(txtFolderPath, GroupLayout.DEFAULT_SIZE, 380, GroupLayout.DEFAULT_SIZE)
								.addGroup(gl_realTimePanel.createSequentialGroup()
									.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSearchSomething))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblType)
										.addComponent(fileType, 0, 0, Short.MAX_VALUE))))
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_realTimePanel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblPage)
										.addComponent(textPageNr)
										.addComponent(btnSetFolder, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_realTimePanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxSaveAll)
										.addComponent(btnSaveMetadata, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLoading)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFileInfo))
						.addComponent(lblChooseFile))
					.addGap(165))
		);
		gl_realTimePanel.setVerticalGroup(
			gl_realTimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_realTimePanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetFolder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_realTimePanel.createSequentialGroup()
									.addComponent(lblSearchSomething)
									.addGap(2))
								.addGroup(gl_realTimePanel.createSequentialGroup()
									.addComponent(lblType)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileType, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addComponent(lblPage)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPageNr, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoading)
								.addComponent(lblFileInfo))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblChooseFile)
							.addGap(1))
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addComponent(chckbxSaveAll)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveMetadata, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		realTimePanel.setLayout(gl_realTimePanel);

	}


	public JPanel getFolderPanel(){
		return this.realTimePanel;
	}

	public void actionPerformed(ActionEvent e) {		
		
		if(e.getSource() == btnSetFolder){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							
			int returnVal = chooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				txtFolderPath.setText(chooser.getSelectedFile().getAbsolutePath());				
			}
		}
		
		if(e.getSource() == btnSearch){
			if(textPageNr.getText().isEmpty() || textPageNr.getText().equalsIgnoreCase("0")){
				setMsgBox("Page rank cannot be empty or zero");
			}else if (!textSearch.getText().isEmpty() && !txtFolderPath.getText().isEmpty()){	
				btnSearch.setEnabled(false);
				fileNames.removeAllItems();
				lblLoading.setVisible(true);
				lblFileInfo.setVisible(true);

				realTimeController = new RealTimeController(this);
				realTimeController.analyseRealTimeFiles(Paths.get(txtFolderPath.getText()),
							textSearch.getText(), textPageNr.getText() );					
			}else{
					setMsgBox("Please, fill neccessary parts");
			}
			btnSearch.setEnabled(true);
		}
		
		if (e.getSource() == fileNames){
			fileData.clearText();
			realTimeController.fillFileData(getFileData(), fileNames.getSelectedIndex());
		}
		
		if (e.getSource() == btnSaveMetadata){
			if(realTimeController != null){
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					if(chckbxSaveAll.getSelectedObjects() != null)
						realTimeController.saveAllMetadata(chooser.getSelectedFile().getAbsolutePath());
					else
						realTimeController.saveMetadata(chooser.getSelectedFile().getAbsolutePath(), fileNames.getSelectedIndex());
				}
			}
		}
				
	}
	
	public JLabel getLblLoading(){
		return lblLoading;
	}
	
	public JLabel getLblFileInfo(){
		return lblFileInfo;
	}
	
	public FileData getFileData() {
		return fileData;
	}
	
	public JPanel getRealTimePanel(){
		return realTimePanel;
	}
	
	public JComboBox<String> getFileComboBox(){
		return fileNames;
	}
	
	public void setFileTypes(){
		for( FileType t : FileType.values()){
			fileType.addItem(t.name());
		}
	}
	
	public JComboBox<String> getFileType(){
		return fileType;
	}
	
	public void setMsgBox(String errorMsg){
		fileData.clearText();
		fileNames.removeAllItems();
		lblFileInfo.setVisible(false);
		JOptionPane.showMessageDialog(realTimePanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}
}
