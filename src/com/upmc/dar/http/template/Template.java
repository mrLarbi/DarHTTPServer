package com.upmc.dar.http.template;

import java.util.Map;

public interface Template {
	
	public static final String TEMPLATE_ROOT = "./templates/";
	
	public String transform(String filename, Map<String, String> references) throws Exception;

}
