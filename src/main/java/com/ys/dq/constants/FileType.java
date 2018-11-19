package com.ys.dq.constants;

public enum FileType {
	CSV("csv") , EXCEL("excel") , ZIP("zip");
	
	String value = "";
	FileType(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
