package com.ceng316.view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class FileData extends JPanel {	
	private JTextField textName;
	private JTextField textPath;
	private JTextField textType;
	private JTextField textCreation;
	private JTextField textMdf;
	private JTextField textOwner;
	private JTextField textApp;
	private JTextField textPlatform;
	private JTextField textLoc;
	private JTextArea extraData;

	/**
	 * Create the panel.
	 */
	public FileData() {		
		JTabbedPane fileTab = new JTabbedPane(JTabbedPane.TOP);		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(fileTab, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(fileTab, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		JPanel fileInfoTab = new JPanel();
		fileTab.addTab("File Information", null, fileInfoTab, null);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblPath = new JLabel("Path");
		
		JLabel lblType = new JLabel("Type");
		
		textType = new JTextField();
		textType.setEditable(false);
		textType.setColumns(10);
		
		textPath = new JTextField();
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		GroupLayout gl_fileInfoTab = new GroupLayout(fileInfoTab);
		gl_fileInfoTab.setHorizontalGroup(
			gl_fileInfoTab.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_fileInfoTab.createSequentialGroup()
					.addGroup(gl_fileInfoTab.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_fileInfoTab.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblType)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_fileInfoTab.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPath)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_fileInfoTab.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblName)))
					.addGap(49)
					.addGroup(gl_fileInfoTab.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textType)
						.addComponent(textPath)
						.addComponent(textName, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
					.addGap(23))
		);
		gl_fileInfoTab.setVerticalGroup(
			gl_fileInfoTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fileInfoTab.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_fileInfoTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_fileInfoTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(textPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPath))
					.addGap(15)
					.addGroup(gl_fileInfoTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(textType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblType))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		fileInfoTab.setLayout(gl_fileInfoTab);
		
		JPanel fileMetaDataTab = new JPanel();
		fileTab.addTab("File MetaData", null, fileMetaDataTab, null);
		
		JLabel lblOwner = new JLabel("Owner");
		
		JLabel lblLastModification = new JLabel("Modification");
		
		JLabel lblCreation = new JLabel("Creation");
		
		JLabel lblLocation = new JLabel("Location");
		
		JLabel lblPlatform = new JLabel("Platform");
		
		JLabel lblApplication = new JLabel("Application");
		
		JLabel lblExtraData = new JLabel("Extra Data");
		
		
		textOwner = new JTextField();
		textOwner.setEditable(false);
		textOwner.setColumns(10);
		
		textMdf = new JTextField();
		textMdf.setEditable(false);
		textMdf.setColumns(10);
		
		textCreation = new JTextField();
		textCreation.setEditable(false);
		textCreation.setColumns(10);
		
		textLoc = new JTextField();
		textLoc.setEditable(false);
		textLoc.setColumns(10);
		
		textPlatform = new JTextField();
		textPlatform.setEditable(false);
		textPlatform.setColumns(10);
		
		textApp = new JTextField();
		textApp.setEditable(false);
		textApp.setColumns(10);
		
		extraData = new JTextArea();
		extraData.setEditable(false);
		extraData.setBackground(null);
		
		JScrollPane scroll = new JScrollPane(extraData);
		
		
		GroupLayout gl_fileMetaDataTab = new GroupLayout(fileMetaDataTab);
		gl_fileMetaDataTab.setHorizontalGroup(
			gl_fileMetaDataTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fileMetaDataTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApplication, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOwner)
						.addComponent(lblLastModification)
						.addComponent(lblCreation)
						.addComponent(lblLocation)
						.addComponent(lblPlatform, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExtraData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.LEADING)
						.addComponent(textPlatform, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(textLoc, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(textCreation, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(textMdf, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(textOwner, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(textApp, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		gl_fileMetaDataTab.setVerticalGroup(
			gl_fileMetaDataTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fileMetaDataTab.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOwner)
						.addComponent(textOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastModification)
						.addComponent(textMdf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreation)
						.addComponent(textCreation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocation)
						.addComponent(textLoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlatform)
						.addComponent(textPlatform, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApplication)
						.addComponent(textApp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_fileMetaDataTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExtraData)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(174))
		);
		fileMetaDataTab.setLayout(gl_fileMetaDataTab);
		setLayout(groupLayout);

	}

	public JTextField getTextName() {
		return textName;
	}

	public JTextField getTextPath() {
		return textPath;
	}

	public JTextField getTextType() {
		return textType;
	}

	public JTextField getTextCreation() {
		return textCreation;
	}

	public JTextField getTextMdf() {
		return textMdf;
	}

	public JTextField getTextOwner() {
		return textOwner;
	}

	public JTextField getTextApp() {
		return textApp;
	}

	public JTextField getTextPlatform() {
		return textPlatform;
	}

	public JTextField getTextLoc() {
		return textLoc;
	}	
	public JTextArea getTextExtraData() {
		return extraData;
	}	
	
	public void clearText(){
		textName.setText("");
		textPath.setText("");
		textType.setText("");
		textCreation.setText("");
		textMdf.setText("");
		textOwner.setText("");
		textApp.setText("");
		textPlatform.setText("");
		textLoc.setText("");
		extraData.setText("");		
	}
}