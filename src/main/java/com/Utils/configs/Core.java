package com.Utils.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Utils.reports.Log;

public class Core {
	private static final Logger logger = LogManager.getLogger(Log.class);
	
	/**
	 * method to read input from config properties file
	 * @param property
	 * @return
	 * @throws IOException
	 */
	public static String readProperty(String property) throws IOException {
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
	
	/**
	 * method to create a file
	 * @param fileDir
	 * @param filePath
	 * @throws IOException
	 */
	public static void createFile(String fileDir, String filePath) throws IOException {
		try {
			File dir = new File(fileDir);
	        if (!dir.exists()) {
	            dir.mkdirs(); // Create Reports folder if it doesn't exist
	        }
	        
	        File generateFile = new File(filePath);
	        if (generateFile.exists()) {
	            if (generateFile.delete()) {
	                logger.info("🗑 Old report deleted: " + filePath);
	            } else {
	                logger.error("⚠ Failed to delete old report. Check file permissions.");
	            }
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
