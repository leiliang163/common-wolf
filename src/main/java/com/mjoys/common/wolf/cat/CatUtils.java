package com.mjoys.common.wolf.cat;

import com.dianping.cat.Cat;
import com.mjoys.advert.biz.utils.BlowfishUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunjiangrong . 16/11/15 .
 */
public class CatUtils {

    /** The logger. */
    private static Logger logger = LoggerFactory.getLogger(BlowfishUtils.class);

    public static void log(String serviceName) {
        try {
            Cat.logMetricForCount(serviceName);

        } catch (Exception e) {
            logger.warn("Cat.logMetricForCount error", e);
        }
    }
}
