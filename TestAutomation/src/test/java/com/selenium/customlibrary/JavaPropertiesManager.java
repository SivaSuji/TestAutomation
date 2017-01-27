package com.selenium.customlibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JavaPropertiesManager {
	
	final static Logger logger = Logger.getLogger(JavaPropertiesManager.class); //copy from BasePage and change the class name
	private String propertiesFile;
	private Properties prop;
	private OutputStream output;
	private InputStream input;

	public JavaPropertiesManager(String propertiesFilepath) {
		propertiesFile = propertiesFilepath;
		prop = new Properties();
	}

	// read property
	public String readProperty(String key) throws IOException {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally { // finally always gets executed
			if (input != null) {
				input.close();
			}
		}
		return value;
	}

	
	// set property
	public void setProperty(String key, String value) {
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (Exception e) {
			//e.printStackTrace();//all the stackable line by line gets printed
			logger.error(e.getMessage(), e);
		}
	}
}
