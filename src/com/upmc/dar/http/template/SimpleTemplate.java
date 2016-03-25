package com.upmc.dar.http.template;

import java.io.File;
import java.util.Map;

import com.upmc.dar.utilities.Utilities;

public class SimpleTemplate implements Template {

	private String type;
	
	public SimpleTemplate(String type) {
		this.type = type;
	}
	
	public String transform(String filename, Map<String, String> references) throws Exception {
		File f = new File(Template.TEMPLATE_ROOT + type + "/" + filename + "." + type);
		String content = Utilities.readFile(f);
		
		for(String key : references.keySet()) {
			String toFind = "$" + key + "$";
			content = content.replace(toFind, references.get(key));
		}
		
		return content;
	}

}
