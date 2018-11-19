package com.ys.dq.engine;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ys.dq.beans.DQFileCheckResults;

@Service
public class CSVFileQualityChecker {

    public DQFileCheckResults check( String filePath , int header , int trailer , int colCount , String delimiter) throws IOException {
    	DQFileCheckResults fileCheckResults = new DQFileCheckResults();
    	
    	Path path = Paths.get(filePath);
    	//computing file size
    	FileChannel imageFileChannel = FileChannel.open(path);
        fileCheckResults.setFileSize(imageFileChannel.size());    	
    	
    	//computing total lines in a file
    	try( Stream<String>  fileStream = Files.lines(path)){
    		fileCheckResults.setTotalLines(fileStream.count());
    	}
    	//empty file check
    	if( 0 ==  fileCheckResults.getTotalLines()) {
    		fileCheckResults.setEmptyFile(true);
    	}    	
    	//skip header and trailer
    	try (Stream<String>  fileStream = Files.lines(path).skip(header).limit(fileCheckResults.getTotalLines()-header-trailer)) {
    		fileCheckResults.setLinesWithoutHeaderTrailer(fileStream.count());
    		if ( 0 == fileCheckResults.getLinesWithoutHeaderTrailer() ) {
    			fileCheckResults.setEmptyFile(true);
    		}else {
    			//check the column count count of each row
    			try (Stream<Integer>  validRecordsStream = Files.lines(path).skip(header).limit(fileCheckResults.getTotalLines()-header-trailer).map(line -> line.split(delimiter).length).filter( length -> length.equals(colCount) ) ) {
    				fileCheckResults.setValidRowsCount(validRecordsStream.count());
    				if( fileCheckResults.getLinesWithoutHeaderTrailer() ==  fileCheckResults.getValidRowsCount() ) {
    					fileCheckResults.setColumnCheckStatus(true);
    				}else {
    					fileCheckResults.setColumnCheckStatus(false);
    				}
    			}
    		}
		}
    	return fileCheckResults;
    }
	
	
	@Value("${name:unknown}")
    private String name;

    

}

