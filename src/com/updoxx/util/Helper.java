package com.updoxx.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * Created by Bryan on 5/30/2017.
 */
public class Helper {
    private static final Logger LOGGER = LogManager.getLogger(Helper.class);

    public static void setLog4JLogLevels(Level level){
        LOGGER.info("Debug Mode Detected -- Setting Logging Levels to {}", level.toString());
        LoggerContext ctx          = (LoggerContext) LogManager.getContext(false);
        Configuration config       = ctx.getConfiguration();
        LoggerConfig  loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(level);
        ctx.updateLoggers();
    }
}
