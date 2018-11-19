package com.ys.dq.engine;

import static java.lang.System.exit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ys.dq.beans.DQFileCheckResults;
import com.ys.dq.constants.DQParameters;
import com.ys.dq.constants.FileType;
@SpringBootApplication
public class DqEngineApplication implements CommandLineRunner{

	@Autowired
    private CSVFileQualityChecker csvfileQualityChecker;
	
	public static void main(String[] args) {
		SpringApplication.run(DqEngineApplication.class, args);
        SpringApplication app = new SpringApplication(DqEngineApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "";
	  	int header=0;
	  	int trailer=0;
	  	int columnCount=0;
	  	String delimiter=",";
	  	String fileType = "";
		if(args.length !=12) {
			throw new IllegalArgumentException("Invalid usage , it should be  --filetype csv --filepath c:/localfiles --headercount 2 --trailercount 1 --columnscount 10 --delimiter ,");
		}
	  	for(int i=0;i<args.length;i++) {
	  		if(args[i].equalsIgnoreCase(DQParameters.FILE_PATH.toString())) {
				filePath = args[i+1];
			}else if(args[i].equalsIgnoreCase(DQParameters.HEADER.toString())) {
				header = Integer.parseInt(args[i+1]);
			}else if(args[i].equalsIgnoreCase(DQParameters.TRAILER.toString())) {
				trailer = Integer.parseInt(args[i+1]);
			}else if(args[i].equalsIgnoreCase(DQParameters.COLUMNS_COUNT.toString())) {
				columnCount = Integer.parseInt(args[i+1]);
			}else if(args[i].equalsIgnoreCase(DQParameters.DELIMITER.toString())) {
				delimiter = args[i+1];
			}else if(args[i].equalsIgnoreCase(DQParameters.FILE_TYPE.toString())) {
				fileType = args[i+1];
			}else {
				throw new IllegalArgumentException(args[i] + " is a invalid parameter");
			}
	  		i++;
	  	}
	  	if(fileType .equalsIgnoreCase(FileType.CSV.toString())) {
	  		DQFileCheckResults results = csvfileQualityChecker.check(filePath, header, trailer, columnCount , delimiter);
	  		System.out.println(results);
	  	}else {
	  		throw new IllegalArgumentException(fileType + "- filetype not supported.");
	  	}
        exit(0);
		
	}
	
	
}
