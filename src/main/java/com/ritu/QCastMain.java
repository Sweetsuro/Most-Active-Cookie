package com.ritu;

public class QCastMain {

	public static void commandLineError() {
		System.err.println("usage: ./QCastMain <fileName.csv> [-d] <arg>");
        System.exit(1);
	}

	public static void main(String[] args) {
		// comand line parsing
		if (args.length < 1) commandLineError();
		// check if valid # of args
		if (args.length%2 == 0) commandLineError();

		// get file name
		String fName = args[0];

		// optional args
		boolean dateFlag = false;
		String date = "";

		if (args.length == 1) {
			System.out.println("File " + fName + " recieved.");
		} else {
			// parse rest of args
			for (int i = 1; i < args.length; i+=2) {
				String opt = args[i];
				switch (opt) {
					case "-d":
						dateFlag = true;
						date = args[i+1];
						break;
					default: 
						commandLineError();
				}
			}

			WrapperFile file;
			if (dateFlag) {
				 file = new WrapperFile(fName, date);
				 if (!file.isValid()) {
					System.err.println("File and/or date given is invalid.");
        			System.exit(1);
				 }
				 FileProcessor fp = new FileProcessor(file);
				 fp.processFile();
			}
		}
	}
}