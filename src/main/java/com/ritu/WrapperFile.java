package com.ritu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WrapperFile {
	private String fName;
	private String date;
	
	WrapperFile (String name, String date) {
		this.fName = name;
		this.date = date;
	}

	public String getFName() {
		return this.fName;
	}

	public String getDate() {
		return this.date;
	}

	public boolean isValid() {
		// does the fileName have CSV token
		if (!this.getFileExt().equals(".csv")) return false;
		// is date given valid
		if (!this.validateDate(this.date)) return false;
		return true;
	}

	private String getFileExt() {
		int dotIndex = this.fName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : this.fName.substring(dotIndex);
	}

	public boolean validateDate(String value) {
		LocalDate ld = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ISO_LOCAL_DATE;
		try {
			ld = LocalDate.parse(value, fomatter);
			String result = ld.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
