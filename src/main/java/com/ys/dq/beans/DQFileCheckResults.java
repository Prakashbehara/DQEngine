package com.ys.dq.beans;

public class DQFileCheckResults {
	private long fileSize;
	private long totalLines;
	private boolean emptyFile;
	private boolean columnCheckStatus;
	private long linesWithoutHeaderTrailer;
	private long validRowsCount;
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getTotalLines() {
		return totalLines;
	}
	public void setTotalLines(long totalLines) {
		this.totalLines = totalLines;
	}
	public boolean isEmptyFile() {
		return emptyFile;
	}
	public void setEmptyFile(boolean emptyFile) {
		this.emptyFile = emptyFile;
	}
	
	public long getLinesWithoutHeaderTrailer() {
		return linesWithoutHeaderTrailer;
	}
	public void setLinesWithoutHeaderTrailer(long linesWithoutHeaderTrailer) {
		this.linesWithoutHeaderTrailer = linesWithoutHeaderTrailer;
	}
	public long getValidRowsCount() {
		return validRowsCount;
	}
	public void setValidRowsCount(long validRowsCount) {
		this.validRowsCount = validRowsCount;
	}
	
	public boolean isColumnCheckStatus() {
		return columnCheckStatus;
	}
	public void setColumnCheckStatus(boolean columnCheckStatus) {
		this.columnCheckStatus = columnCheckStatus;
	}
	@Override
	public String toString() {
		return "fileSize="+this.fileSize + ";totalLines="+this.totalLines+";emptyFile="+
	           this.emptyFile+";colCheckStatus="+this.columnCheckStatus+";linesWithoutHeaderTrailer"+linesWithoutHeaderTrailer+";validRowsCount"+
			   validRowsCount;
	}
	
}
