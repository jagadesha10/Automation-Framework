package com.ui.Browser.Utility;

import com.constants.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {


    public static String readProperty(Env env, String propertyName) {
        System.out.println(System.getProperty("user.dir"));
        File propFile = new File((System.getProperty("user.dir") + "\\config\\"+ env +".properties"));
        FileReader fileReader= null;
        Properties properties= new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value= properties.getProperty(propertyName);
        return value;
    }
}
