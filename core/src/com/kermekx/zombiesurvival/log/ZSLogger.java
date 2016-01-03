package com.kermekx.zombiesurvival.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ZSLogger {
	
	/**
	 * Instance du logger
	 */
	private final static ZSLogger INSTANCE = new ZSLogger();
	
	/**
	 * logger
	 */
	private final Logger logger;
	
	/**
	 * cr�er le logger
	 */
	private ZSLogger() {
		logger = Logger.getLogger(ZSLogger.class.getName());
		logger.setLevel(Level.ALL);
	}
	
	/**
	 * log une info
	 * @param info message � afficher
	 */
	public static void logInfo(String info) {
		INSTANCE.logger.log(Level.INFO, info);
	}

	/**
	 * log une exception
	 * @param e exception � affacher
	 */
	public static void logException(Exception e) {
		INSTANCE.logger.log(Level.SEVERE, e.getMessage(), e);
	}
}
