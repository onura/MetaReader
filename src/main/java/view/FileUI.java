package view;

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

import controller.MetaReaderController;

import model.metafile.FileType;


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
		
		GroupLayout gl_filePanel = new GroupLayout(filePanel);
		gl_filePanel.setHorizontalGroup(
			gl_filePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetMetadata, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_filePanel.createSequentialGroup()
							.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnChooseFile, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addComponent(fileTypes, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
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
					.addComponent(btnGetMetadata, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
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
			if (!txtFilePath.getText().isEmpty()){
				MetaReaderController reader = new MetaReaderController(this);
				
				reader.getFileMetadata(Paths.get(txtFilePath.getText()));
				radioFileType.clearSelection();
			}else{
				fileData.clearText();
				setMsgBox("Choose a file");
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
			rdbtnFileType[t.ordinal()].addActionListener(this);
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
	public void setMsgBox(String errorMsg){fileData.clearText();
		JOptionPane.showMessageDialog(filePanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}
}

	
