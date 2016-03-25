package com.upmc.dar.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Utilities {

	public static String readFile(File f) throws Exception {

		String res = "";

		BufferedReader br = null;	
		br = new BufferedReader( new FileReader(f) );
		String line = null;

		while((line = br.readLine()) != null) {
			res += line;
		}
		
		try {
			br.close();
		} catch (Exception e){ }
		
		return res;
	}

}
