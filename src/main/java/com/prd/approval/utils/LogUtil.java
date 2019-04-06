/**
 * Author: lin
 * Date: 2019/4/3 10:45
 */
package com.prd.approval.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtil {

    public static void log(String msg){
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        Logger logger = Logger.getLogger(methodName);
        logger.log(Level.WARNING,methodName+"\n"+msg);
    }
}
