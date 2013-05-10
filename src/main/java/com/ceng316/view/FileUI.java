package com.ceng316.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import com.ceng316.controller.MetaReaderController;
import com.ceng316.model.metafile.FileType;




@SuppressWarnings("serial")
public class FileUI extends JPanel implements ActionListener{
	private JPanel filePanel;
	private FileData fileData;
	private JButton btnChooseFile;
	private JButton btnGetMetadata;
	private JPanel fileTypes;
	private ButtonGroup radioFileType;
	private JRadioButton[] rdbtnFileType;
	private JTextField txtFilePath;
	private JButton btnSaveMetadata;
	private MetaReaderController reader;
	
	
	public FileUI() {		
		filePanel = new JPanel();
		fileData = new FileData();
		
		txtFilePath = new JTextField();
		txtFilePath.addActionListener(this);

		btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(this);
		btnGetMetadata = new JButton("Get Metadata");
		btnGetMetadata.addActionListener(this);

		fileTypes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) fileTypes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel lblFileTypes = new JLabel("File Types :");
		fileTypes.add(lblFileTypes);
		
		radioFileType = new ButtonGroup();
		rdbtnFileType = new JRadioButton[FileType.values().length];
		setRadioBtns();				
		
		btnSaveMetadata = new JButton("Save Metadata");
		btnSaveMetadata.addActionListener(this);
		btnSaveMetadata.setVisible(false);
		
		GroupLayout gl_filePanel = new GroupLayout(filePanel);
		gl_filePanel.setHorizontalGroup(
			gl_filePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_filePanel.createSequentialGroup()
							.addComponent(btnGetMetadata, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnSaveMetadata))
						.addGroup(gl_filePanel.createSequentialGroup()
							.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnChooseFile, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addComponent(fileTypes, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_filePanel.setVerticalGroup(
			gl_filePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filePanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_filePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChooseFile, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(fileTypes, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_filePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGetMetadata, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveMetadata))
					.addGap(18)
					.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		filePanel.setLayout(gl_filePanel);
	}

	public JPanel getFilePanel(){
		return this.filePanel;
	}

/* Actions */
	public void actionPerformed(ActionEvent e) {		
		/* chooses file directory */		
		if (e.getSource() == btnChooseFile){
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				txtFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		/* getting Metadata information*/
		if(e.getSource() == btnGetMetadata){
			fileData.clearText();
			if (!txtFilePath.getText().isEmpty()){
				btnSaveMetadata.setVisible(true);
				reader = new MetaReaderController(this);
				
				reader.getFileMetadata(Paths.get(txtFilePath.getText()));
				radioFileType.clearSelection();

			}else{
				setMsgBox("Choose a file");
			}
		}	
		
		if (e.getSource() == btnSaveMetadata){
			if(reader != null){
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					reader.saveFileMetadata(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		}
		
	}
	
	public FileData getFileData() {
		return fileData;
	}

	/* set radio buttons according to FileTypes enum*/
	private void setRadioBtns(){
		for( FileType t : FileType.values()){
			rdbtnFileType[t.ordinal()] = new JRadioButton(t.name());
			//rdbtnFileType[t.ordinal()].addActionListener(this);
			radioFileType.add(rdbtnFileType[t.ordinal()]);
			fileTypes.add(rdbtnFileType[t.ordinal()]);
		}
	}
	
	public JRadioButton getRadioBtn(int index){
		return rdbtnFileType[index];
	}
	
	/* gives selected radio button index */
	public int controlRdtBtn () {
		for (int i = 0; i < FileType.values().length; i++){
			if (rdbtnFileType[i].isSelected())
				return i;
		}		
		return -1;
	}

	/* shows message */
	public void setMsgBox(String errorMsg){
		fileData.clearText();
		btnSaveMetadata.setVisible(false);
		JOptionPane.showMessageDialog(filePanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}
}

	
