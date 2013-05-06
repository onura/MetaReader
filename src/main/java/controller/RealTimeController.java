package controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import model.metafile.FileType;
import model.metafile.MetaFile;
import model.realtime.RealTimeReader;
import model.util.google.GoogleFileSearch;

import view.RealTimeUI;

public class RealTimeController {
	private RealTimeUI realTimeUI;
	private ArrayList<MetaFile> realMetaFiles;
	
	public RealTimeController(RealTimeUI realTimeUI){
		this.realTimeUI = realTimeUI;
		this.realMetaFiles = new ArrayList<MetaFile>();
	}
	
	public void getRealTimeMetadas(Path tempDir,String search, String rank){
		
		if(addFileType() != null){
			RealTimeReader rtr = new RealTimeReader(tempDir, new RealTimePrint(this));	
			GoogleFileSearch gfs = new GoogleFileSearch();
			rtr.process(gfs.search(search, addFileType(), new Integer(rank)));
		}else{
			realTimeUI.setMsgBox("Check file type");
		}
			
	}
	
	public void setRealTimeMetadatas(int index){
		String extra = "";

		if(index >= 0){
			realTimeUI.getFileData().getTextName().setText(control(realMetaFiles.get(index).getFileInfo().getFileName()));
			realTimeUI.getFileData().getTextPath().setText(control(realMetaFiles.get(index).getFileInfo().getFilePath().toString()));
			realTimeUI.getFileData().getTextType().setText(control(realMetaFiles.get(index).getFileInfo().getType().toString()));
			
			realTimeUI.getFileData().getTextOwner().setText(control(realMetaFiles.get(index).getMetaData().getOwner()));
			realTimeUI.getFileData().getTextMdf().setText(dateControl(realMetaFiles.get(index).getMetaData().getModificationDate()));
			realTimeUI.getFileData().getTextCreation().setText(dateControl(realMetaFiles.get(index).getMetaData().getCreationDate()));
			//realTimeUI.getFileData().getTextPlatform().setText(control(realMetaFiles.get(index).getMetaData().getPlatform()));
			realTimeUI.getFileData().getTextApp().setText(control(realMetaFiles.get(index).getMetaData().getApplication()));

			for ( Entry<String, String> extraData :realMetaFiles.get(index).getMetaData().getExtraData().entrySet()){
				extra += extraData.getKey() + ":" + control(extraData.getValue()) + "\n";
			}
			realTimeUI.getFileData().getTextExtraData().setText(extra);
		}
				
	}
	
	public String control(String text){
		if (text == null)
			return "Unknown";
		return text;
	}
	
	public String dateControl(Date date){		
		if (date != null)
			return date.toString();
		return "Unknown";
	}
	
	public ArrayList<MetaFile> getRealMeta(){
		return realMetaFiles;
	}
	
	public void addFileNames(String metaFile){
		realTimeUI.getFileComboBox().addItem(metaFile);
	}
	
	public FileType addFileType(){
		for( FileType t : FileType.values()){
			if (t.name().equals(realTimeUI.getFileType().getSelectedItem().toString())){
				return t;
			}
		}
		return null;

	}

}
