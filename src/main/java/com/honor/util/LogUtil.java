package com.honor.util;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 日志工具
 */
public class LogUtil {
    private static Logger logger = Logger.getLogger(LogUtil.class);

    /**
     * info级别
     *
     * @param msg
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * debug级别
     *
     * @param msg
     */
    public static void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * error级别
     *
     * @param msg
     */
    public static void error(String msg) {
        logger.error(msg);
    }

    /**
     * warn及别
     *
     * @param msg
     */
    public static void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * map转为字符串
     *
     * @param logMap
     * @return
     */
    public static String mapToStr(Map<String, Object> logMap) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : logMap.entrySet()) {
            sb.append(entry.getKey() + ":" + entry.getValue().toString());
            sb.append("; ");
        }
        return sb.toString();
    }
}
