package test;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.ceng316.controller.RealTimeFinish;
import com.ceng316.controller.RealTimePrint;
import com.ceng316.model.metafile.FileType;
import com.ceng316.model.realtime.RealTimeReader;
import com.ceng316.model.util.google.GoogleFileSearch;




public class TestRealTime {
	public static void main(String[] args) {
		
		Path dd = Paths.get("/tmp");		
		RealTimeReader rtr = new RealTimeReader(dd, new RealTimePrint(), new RealTimeFinish());
		GoogleFileSearch gfs = new GoogleFileSearch();
		rtr.process(gfs.search("iyte", FileType.PDF, 2));
				
	}
}
