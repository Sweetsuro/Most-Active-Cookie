package com.ritu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor {
	private WrapperFile wf;
	private HashMap<String, Integer> cookieCounts;
	
	FileProcessor(WrapperFile wf) {
		this.wf = wf;
		this.cookieCounts = new HashMap<>();
	}

	private boolean validateContent(String[] toks, String cDate) {
		if (toks.length != 2) return false;
		if (!this.wf.validateDate(cDate)) return false;
		return true;
	}

	public void processFile() {
		try {
			Scanner sc = new Scanner(new File(wf.getFName()));
			while (sc.hasNext()) {
				String line = sc.next();

				// grab real token and date only
				String[] toks = line.split(",", 0);
				String token = toks[0];
				toks = toks[1].split("T", 0);
				String cDate = toks[0];
				
				if (!validateContent(toks, cDate)) {
					System.err.println("File content is invalid.");
        			System.exit(1);
				}

				// adds token to HashMap if present
				if (cDate.compareTo(wf.getDate()) < 0) {
					break;
				} else if (cDate.equals(wf.getDate())) {
					cookieCounts.put(token, 
						cookieCounts.getOrDefault(token, 0)+1);
				}
			}   
			sc.close();  
		} catch (FileNotFoundException e) {
			System.err.println("Invalid file path");
			System.exit(1);
		}

		// have HashMap of counts
		if (cookieCounts.size() < 1) {
			System.err.println("Date not found in log");
			System.exit(1);
		}

		// find highest token frequency
		int maxCookieCount = 0;
		for (int val : cookieCounts.values()) {
			if (val > maxCookieCount)
				maxCookieCount = val;
		}

		// print tokens with highest frequency
		for (String key : cookieCounts.keySet()) {
			if (cookieCounts.get(key) == maxCookieCount)
				System.out.println(key);
		}
	}
}