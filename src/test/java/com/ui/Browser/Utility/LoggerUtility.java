package com.ui.Browser.Utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private static Logger logger;

    public static Logger getLogger(Class<?> clazz) {
        if (logger == null) {
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }

}
