/**
 * Author: lin
 * Date: 2019/5/1 16:22
 */
package com.prd.approval.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

/**
 * <p></p>
 */
public class ConstantUtil {

    private static Properties properties;
    private static final String RELATIVE_PATH = "static/Constant.properties";
    private static final String KEY = "userStepCode";
    private ConstantUtil() {
    }

    static {
        properties = new Properties();
        try {
            InputStream is = ConstantUtil.class.getClassLoader()
                    .getResourceAsStream(RELATIVE_PATH);
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUserStepCode() {

        Integer value = Integer.valueOf(properties.get(KEY).toString());
        value++;
        properties.put(KEY,value.toString());
        URL url = ConstantUtil.class.getClassLoader().getResource(RELATIVE_PATH);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(url.getFile());
            properties.store(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        value--;
        return value.toString();
    }
}
