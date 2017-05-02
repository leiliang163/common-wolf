package com.mjoys.common.wolf.cat;

import com.dianping.cat.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunjiangrong . 16/11/15 .
 */
public class CatUtils {

    /** The logger. */
    private static Logger logger = LoggerFactory.getLogger(CatUtils.class);

    public static void log(String serviceName) {
        try {
            Cat.logMetricForCount(serviceName);

        } catch (Exception e) {
            logger.warn("Cat.logMetricForCount error", e);
        }
    }
}
