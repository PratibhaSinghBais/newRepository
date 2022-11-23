package com.testVagrant.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    public String readProperties(String property) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
        Properties prop = new Properties();
        prop.load(fis);

    return prop.getProperty(property);
    }


}
