package com.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Core {
	
	/**
	 * method to read input from config properties file
	 * @param property
	 * @return
	 * @throws IOException
	 */
	public String readProperty(String property) throws IOException {
		InputStream input = null;
		String value = null;
		try {
			Properties prop = new Properties();
			input = new FileInputStream("./src/main/resources/config.properties");
			
			if(input!=null) {
				prop.load(input);
				value = prop.getProperty(property);
			}
			return value;
			
		}catch(Exception e) {
			e.printStackTrace();
			return value;
		}finally {
			if(input!=null) {
				input.close();
			}
		}
	}
}
