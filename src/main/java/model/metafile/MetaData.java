package model.metafile;

import java.util.Date;
import java.util.HashMap;


public class MetaData {
	private String owner;
	private Date modificationDate;
	private Date creationDate;
	private Location location;
	private String platform;
	private String application;
	private HashMap<String, String> extraData;
	
	public MetaData(String owner, Date modificationDate, Date creationDate,
			Location location,
			String platform, String application,
			HashMap<String, String> extraData) {
		super();
		this.owner = owner;
		this.modificationDate = modificationDate;
		this.creationDate = creationDate;
		this.location = location;
		this.platform = platform;
		this.application = application;
		this.extraData = extraData;
	}

	public MetaData() {
		this(null, null, null, null, null, null, null);
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Date getModificationDate() {
		return modificationDate;
	}	

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public HashMap<String, String> getExtraData() {
		return extraData;
	}

	public void setExtraData(HashMap<String, String> extraData) {
		this.extraData = extraData;
	}
	
	@Override
	public String toString() {
		return "Owner: " + this.owner + "\n" +
				"Last Modification: " + this.modificationDate + "\n" +
				"Creation: " + this.creationDate + "\n" +
				"Location: " + this.location+ "\n" +
				"Platform: " + this.platform+ "\n" +
				"Application: " + this.application+ "\n" +
				"ExtraData: " + this.extraData+ "\n";
	}
	
}
