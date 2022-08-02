package org.example.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qinfengsa
 * @date 2022/08/01 15:58
 */
public class ParameterUtils {

    private static final DateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterUtils.class);

    public static void printValueOnStack(Object value) {
        if (value == null) {
            LOGGER.info("null");
        } else if (value instanceof String) {
            LOGGER.info("{}", value);
        } else if (value instanceof Date) {
            LOGGER.info("{}", fm.format(value));
        } else if (value instanceof char[]) {
            LOGGER.info("{}", Arrays.toString((char[]) value));
        } else {
            LOGGER.info("{}:{}", value.getClass(), value);
        }
    }

    public static void printText(String str) {
        LOGGER.info("printText:{}", str);
    }
}
