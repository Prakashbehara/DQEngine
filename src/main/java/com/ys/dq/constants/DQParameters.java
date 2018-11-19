package com.ys.dq.constants;

public enum DQParameters {
	FILE_PATH ("--filepath"),
	HEADER("--headercount"),
	TRAILER("--trailercount"),
	COLUMNS_COUNT("--columnscount"),
	DELIMITER("--delimiter"),
	FILE_TYPE("--filetype");
	String value;
	DQParameters(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
