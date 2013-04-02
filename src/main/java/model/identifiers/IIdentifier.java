package model.identifiers;

import java.io.File;

import model.metafile.FileType;

public interface IIdentifier {

	public FileType identify(File file);
}
