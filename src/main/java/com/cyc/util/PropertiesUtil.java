package com.cyc.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName = "global.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常", e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key.trim()).trim();
    }

    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim()).trim();
        return StringUtils.isBlank(value) ? defaultValue : value;
    }

    public static int getIntegerProperty(String key) {
        return Integer.valueOf(getProperty(key));
    }

    public static int getIntegerProperty(String key, String defaultValue) {
        return Integer.valueOf(getProperty(key, defaultValue));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.valueOf(getProperty(key));
    }
}
