/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eqa.formula.utilidades;

/**
 *
 * @author Abdullah_PC
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
public class UsersGetPropertyValues {
    String result = "";
    InputStream inputStream;
    public Properties getPropValues() throws IOException {
              Properties prop = new Properties();
		try {
			
			String propFileName = ".\\resources\\config.properties";
                        FileInputStream ip= new FileInputStream(propFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (ip != null) {
				prop.load(ip);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			
 
			
		} catch (Exception e) {
                        System.out.println(new File(".").getAbsolutePath());
			System.out.println("Exception: " + e);
		} 
		return prop;
	}
    
}

