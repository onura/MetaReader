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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

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
	private JComboBox<String> pageRank;
	private JLabel lblSearchSomething;
	private JLabel lblPage;
	
	public RealTimeUI() {		
		realTimeController = new RealTimeController(this);
		
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
		
		pageRank = new JComboBox<String>();
		pageRank.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		pageRank.setToolTipText("");
		pageRank.setEditable(true);
		
		lblSearchSomething = new JLabel("Search Something");
		
		lblPage = new JLabel("Page Number");
		
		GroupLayout gl_realTimePanel = new GroupLayout(realTimePanel);
		gl_realTimePanel.setHorizontalGroup(
			gl_realTimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_realTimePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(fileData, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSearchSomething)
								.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSetFolder)
								.addComponent(lblPage)
								.addComponent(pageRank, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
							.addGap(26)))
					.addGap(0))
		);
		gl_realTimePanel.setVerticalGroup(
			gl_realTimePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_realTimePanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFolderPath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetFolder, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_realTimePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_realTimePanel.createSequentialGroup()
							.addComponent(lblSearchSomething)
							.addGap(2)
							.addGroup(gl_realTimePanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textSearch, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(pageRank, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblPage))
					.addGap(16)
					.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fileNames, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
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
			fileNames.removeAllItems();	
			if (!textSearch.getText().isEmpty() && !txtFolderPath.getText().isEmpty()){	
				
				realTimeController.getRealTimeMetadas(Paths.get(txtFolderPath.getText()),
						textSearch.getText(), pageRank.getSelectedItem().toString() );

				
			}else{
				setMsgBox("Set a Directory");
			}
		}
		
		if (e.getSource() == fileNames){
			fileData.clearText();
			realTimeController.setRealTimeMetadatas(fileNames.getSelectedIndex());
		}
				
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
	
	public void setMsgBox(String errorMsg){
		fileData.clearText();
		JOptionPane.showMessageDialog(realTimePanel, errorMsg, null, JOptionPane.INFORMATION_MESSAGE);
	}


}
