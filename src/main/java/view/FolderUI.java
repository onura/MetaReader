package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import model.MetaReader.RETCODES;

import controller.MultiReaderController;

@SuppressWarnings("serial")
public class FolderUI extends JPanel implements ActionListener{
	private FileData fileData;
	private JPanel folderPanel;
	private JTextField txtFolderPath;
	private JButton btnChooseFolder;
	private JButton btnGetMetadatas;
	private JComboBox<String> fileNames;
	private MultiReaderController multiReader;
	
	public FolderUI() {
		multiReader  = new MultiReaderController(this);
		
		folderPanel = new JPanel();
		fileData = new FileData();
		
		txtFolderPath = new JTextField();
		txtFolderPath.addActionListener(this);

		btnChooseFolder = new JButton("Choose Folder");		
		btnChooseFolder.addActionListener(this);

		btnGetMetadatas = new JButton("Get Metadatas");
		btnGetMetadatas.addActionListener(this);
		
		fileNames = new JComboBox<String>();
		fileNames.addActionListener(this);
		
		GroupLayout gl_filePanel = new GroupLayout(folderPanel);
		gl_filePanel.setHorizontalGroup(
			gl_filePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_filePanel.createSequentialGroup()
							.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnChooseFolder, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnGetMetadatas, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_filePanel.setVerticalGroup(
			gl_filePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filePanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_filePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChooseFolder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addComponent(btnGetMetadatas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		folderPanel.setLayout(gl_filePanel);

	}


	public JPanel getFolderPanel(){
		return this.folderPanel;
	}

	public void actionPerformed(ActionEvent e) {		
		
		if(e.getSource() == btnChooseFolder){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							
			int returnVal = chooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				txtFolderPath.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource() == btnGetMetadatas){
			fileNames.removeAllItems();	
			if (!txtFolderPath.getText().isEmpty()){
				multiReader.clearMetaFileList();
				multiReader.getFilesMetadata(Paths.get(txtFolderPath.getText()));
				
				if(multiReader.getRetCode() == RETCODES.SUCCESS){
					try{
					fileNames.setSelectedIndex(0);
					}catch (IllegalArgumentException ie) {
						setMsgBox("The file type is unsupported");
					}
				}
			}else{
				fileData.clearText();
				setMsgBox("Choose a folder directory");
			}
		}
		
		if(e.getSource() == fileNames){
			fileData.clearText();
			multiReader.setMetaFile(fileNames.getSelectedIndex());
		}
		
	}

	public void addFileNames(String file, int index) {
		fileNames.insertItemAt(file, index);
	}
	
	public FileData getFileData() {
		return fileData;
	}
	
	public void setMsgBox(String errorMsg){
		fileData.clearText();
		JOptionPane.showMessageDialog(folderPanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}

}
