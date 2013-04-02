package model.extractors;

import java.io.File;

import model.metafile.MetaData;

public interface IExtractor {

	public MetaData extract(File file);
}
