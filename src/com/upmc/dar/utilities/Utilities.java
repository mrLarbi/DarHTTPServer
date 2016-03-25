package com.upmc.dar.utilities;

import com.upmc.dar.http.HttpResponse;

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

	public static void textResponse(boolean valid, String message, HttpResponse response) {
		StringBuilder builder = new StringBuilder();
		builder.append(valid);
		builder.append('\n');
		builder.append(message + "\n");

		response.setBody(builder.toString());
	}

}
