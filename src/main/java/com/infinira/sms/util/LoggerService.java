package com.infinira.sms.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import java.io.InputStream;
import java.io.IOException;
import java.text.MessageFormat;

public class LoggerService {

    private static Logger logger;
	private static final String LOGGER_CONFIG_FILE = "sms_log_config.xml";
    private static LoggerService getInstance;   

    public static LoggerService getInstance() {
        if (getInstance == null) {
            synchronized(LoggerService.class) {
                if (getInstance == null) {
                    getInstance = new LoggerService();
                }
            }
        }
        return getInstance;
    }

    private LoggerService() {
		
		InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(LOGGER_CONFIG_FILE);
			if (inputStream == null) {
                throw new RuntimeException(MessageFormat.format(MSG_001, LOGGER_CONFIG_FILE));
            }
			ConfigurationSource configSource = new ConfigurationSource(inputStream);
            Configurator.initialize(null, configSource);
			this.logger = LogManager.getLogger();
		} catch (IOException e) {
            throw new RuntimeException(MessageFormat.format(MSG_002, LOGGER_CONFIG_FILE), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) { }
            }
        }
	}
	
    public void info(String msg) {
		logger.info(msg);
	}
	
	public void error(String msg, Throwable th) {
		logger.error(msg,th);
	}
	
	public static final String MSG_001 = "Logger Config file [{0}] is not found.";
    public static final String MSG_002 = "Failed to load logger config file [{0}].";
}