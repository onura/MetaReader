package com.ceng316.model.realtime;

import com.ceng316.model.metafile.MetaFile;

/*
 * Interface to define what to do with processed data in real time. 
 */

public interface IRealTimeResult {

	public void use(MetaFile metafile);
}
