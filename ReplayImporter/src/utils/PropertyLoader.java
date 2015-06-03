package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	private Properties properties;
	
	public PropertyLoader(String fileLocation){
		try{
			File file = new File(fileLocation);
			FileInputStream fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key){
		String value = properties.getProperty(key);
		return value;
	}
	
}
