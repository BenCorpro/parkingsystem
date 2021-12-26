package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class App: PArkit Application.
 *
 */
public class App {
  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("App");

  /**
   * main method: initializing application.
   *
   * @param args
   */
  public static void main(String args[]) {
    LOGGER.info("Initializing Parking System");
    InteractiveShell.loadInterface();
  }
}
