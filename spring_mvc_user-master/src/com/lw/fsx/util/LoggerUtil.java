package com.lw.fsx.util;

import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.*;


public class LoggerUtil {
    private static Logger logger = null;
    static {
        logger = Logger.getLogger(LoggerUtil.class.getName());
    }

    public static void debug(Object s) {
        logger.debug(s);
    }

    public static void debug(Collection<?> v) {
        log(Level.DEBUG, v);
    }

    public static void debug(Map<?, ?> map) {
        log(Level.DEBUG, map);
    }

    public static void debug(Object o, Exception e) {
        logger.debug(o, e);
    }

    public static void info(Object s) {
        logger.info(s);
    }

    public static void info(Collection<?> v) {
        log(Level.INFO, v);
    }

    public static void info(Map<?, ?> map) {
        log(Level.INFO, map);
    }

    public static void info(Object o, Exception e) {
        logger.info(o, e);
    }

    public static void warn(Object s) {
        logger.warn(s);
    }

    public static void warn(Collection<?> v) {
        log(Level.WARN, v);
    }

    public static void warn(Map<?, ?> map) {
        log(Level.WARN, map);
    }

    public static void warn(Object o, Exception e) {
        logger.warn(o, e);
    }

    public static void error(Object o) {
        logger.error(o);
    }

    public static void error(Collection<?> v) {
        log(Level.ERROR, v);
    }

    public static void error(Map<?, ?> map) {
        log(Level.ERROR, map);
    }

    public static void error(Object o, Exception e) {
        logger.error(o, e);
    }

    private static void log(Level level, Collection<?> v) {
        if (level.isGreaterOrEqual(logger.getEffectiveLevel())) {
            StringBuffer s = new StringBuffer();
            if (null == v) {
                s.append("NULL");
            } else if (v.size() == 0) {
                s.append("[]");
            } else {
                s.append("[");
                Iterator<?> it = v.iterator();
                while (it.hasNext()) {
                    Object o = it.next();

                    if (o instanceof Collection<?>) {
                        log(level, (Collection<?>) o);
                    }
                    s.append(BaseUtil.toString(o));
                    s.append(":");
                }
                s.append("]");
            }
            logger.log(level, s.toString());
        }

    }

    private static void log(Level level, Map<?, ?> e) {
        if (level.isGreaterOrEqual(logger.getEffectiveLevel())) {
            Iterator<?> iter = e.entrySet().iterator();
            StringBuffer s = new StringBuffer();
            while (iter.hasNext()) {
                Entry<?, ?> entry = (Entry<?, ?>) iter.next();
                s.append(entry.getKey() + " = " + entry.getValue()).append("\n");

            }
            logger.log(level, s.toString());
        }

    }
}
