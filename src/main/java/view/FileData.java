package view;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

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
	private JScrollPane scroll;

	/**
	 * Create the panel.
	 */
	public FileData() {

		Font font = new Font("SansSerif", Font.BOLD, 16);
		
		JLabel lblFileInformation = new JLabel("File Information");
		lblFileInformation.setFont(font);
		
		JLabel lblName = new JLabel("Name");
		
		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		
		JLabel lblPath = new JLabel("Path");
		
		textPath = new JTextField();
		textPath.setEditable(false);
		textPath.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		
		textType = new JTextField();
		textType.setEditable(false);
		textType.setColumns(10);
		
		JLabel lblMetadata = new JLabel("Metadata");
		lblMetadata.setFont(font);
		
		JLabel lblOwner = new JLabel("Owner");
		
		textCreation = new JTextField();
		textCreation.setEditable(false);
		textCreation.setColumns(10);
		
		textMdf = new JTextField();
		textMdf.setEditable(false);
		textMdf.setColumns(10);
		
		textOwner = new JTextField();
		textOwner.setEditable(false);
		textOwner.setColumns(10);
		
		JLabel lblLastModification = new JLabel("Modification");
		
		JLabel lblCreation = new JLabel("Creation");
		
		textApp = new JTextField();
		textApp.setEditable(false);
		textApp.setColumns(10);
		
		textPlatform = new JTextField();
		textPlatform.setEditable(false);
		textPlatform.setColumns(10);
		
		textLoc = new JTextField();
		textLoc.setEditable(false);
		textLoc.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		
		JLabel lblPlatform = new JLabel("Platform");
		
		JLabel lblApplication = new JLabel("Application");
		
		JLabel lblExtraData = new JLabel("Extra Data");
		
		extraData = new JTextArea();
		extraData.setEditable(false);
		extraData.setBackground(null);
		extraData.setBorder(null);
		
		scroll = new JScrollPane(extraData);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMetadata)
						.addComponent(lblFileInformation)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCreation)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblLocation, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPlatform, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblExtraData)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
															.addComponent(lblLastModification, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(lblOwner))
														.addComponent(lblName)
														.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
															.addComponent(lblType)
															.addComponent(lblPath)))
													.addGap(27))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lblApplication, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.RELATED)))))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textType, 333, 364, Short.MAX_VALUE)
								.addComponent(textPath, 333, 364, Short.MAX_VALUE)
								.addComponent(textName, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textOwner, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textMdf, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textCreation, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textLoc, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textPlatform, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(textApp, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))))
					.addGap(32))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblFileInformation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblPath))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(textPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType))))
					.addGap(18)
					.addComponent(lblMetadata)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(textMdf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(textCreation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOwner)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblLastModification)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCreation)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textLoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLocation))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textPlatform, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPlatform))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textApp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApplication))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExtraData))
					.addContainerGap(31, Short.MAX_VALUE))
		);
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
