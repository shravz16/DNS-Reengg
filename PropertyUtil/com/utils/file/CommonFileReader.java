package com.utils.file;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class CommonFileReader {
    private static CommonFileReader commonFileReader =new CommonFileReader();
    private static final String PROPERTIES_FILE_PATH ="/home/shrav/Documents/DNS/DNS/Properties/";
    private static final String JSON_FILE_PATH="/home/shrav/Documents/DNS/DNS/jsons/";
    private CommonFileReader(){}

    public static CommonFileReader getInstance(){
        return commonFileReader;
    }

    public Properties getProperty(String filePath){
        try {
            InputStream inputStream=new FileInputStream(PROPERTIES_FILE_PATH + File.separator+filePath);
            Properties properties=new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getValueFromKey(String key,Properties properties){
        return properties.getProperty(key);
    }

    public JSONObject getJsonObject(String filePath){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(JSON_FILE_PATH+ File.separator+filePath);
            StringBuilder k=new StringBuilder();
            int m;
            while(inputStream.available()>0){
                byte[] b=new byte[8192];
                m=inputStream.read(b);
                k.append(new String(b));
            }

            return new JSONObject(k.toString().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
