package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import model.metafile.FileType;

import controller.RealTimeController;

import java.nio.file.Paths;

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
	private JLabel lblFileInfo;
	
	public RealTimeUI() {		
		
		realTimePanel = new JPanel();
		
		fileData = new FileData();
		
		txtFolderPath = new JTextField();
		txtFolderPath.setText("/tmp");
		txtFolderPath.addActionListener(this);

		btnSetFolder = new JButton("Set Folder");		
		btnSetFolder.addActionListener(this);

		btnSearch = new JButton("Search Metadatas");
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
		
		lblFileInfo = new JLabel("");
		lblFileInfo.setVisible(false);
		
		GroupLayout gl_realTimePanel = new GroupLayout(realTimePanel);
		gl_realTimePanel.setHorizontalGroup(
			gl_realTimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_realTimePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFileInfo))
						.addComponent(lblChooseFile)
						.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(fileNames, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_realTimePanel.createSequentialGroup()
								.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_realTimePanel.createSequentialGroup()
										.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
											.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblSearchSomething))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblType)
											.addComponent(fileType, 0, 0, Short.MAX_VALUE))))
								.addGap(18)
								.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
									.addComponent(textPageNr, 0, 0, Short.MAX_VALUE)
									.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnSetFolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPage)))))))
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
							.addComponent(textPageNr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFileInfo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblChooseFile)
					.addGap(1)
					.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
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
			}else{
				btnSearch.setEnabled(false);
				fileNames.removeAllItems();
				lblFileInfo.setVisible(false);

				realTimeController = new RealTimeController(this);

				if (!textSearch.getText().isEmpty() && !txtFolderPath.getText().isEmpty()){	
					lblFileInfo.setVisible(true);
					realTimeController.getRealTimeMetadas(Paths.get(txtFolderPath.getText()),
							textSearch.getText(), textPageNr.getText() );
					
					
				}else{
					setMsgBox("Please, fill neccessary parts");
				}
				btnSearch.setEnabled(true);
			}
		}
		
		if (e.getSource() == fileNames){
			fileData.clearText();
			realTimeController.setRealTimeMetadatas(fileNames.getSelectedIndex());
		}
				
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
		JOptionPane.showMessageDialog(realTimePanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}
}
