package model.realtime;

import model.metafile.MetaFile;

/*
 * Interface to define what to do with processed data in real time. 
 */

public interface IRealTimeResult {

	public void use(MetaFile metafile);
}
