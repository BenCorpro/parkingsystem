package com.parkit.parkingsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * class to get input from user.
 *
 */
public class InputReaderUtil {

  /**
   * Scanner instance.
   */
  private static Scanner scan = new Scanner(System.in, "UTF-8");
  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("InputReaderUtil");

  /**
   * method to read user's selection (of the menu).
   *
   * @return menu line
   */
  public int readSelection() {
    try {
      int input = Integer.parseInt(scan.nextLine());
      return input;
    } catch (Exception e) {
      LOGGER.error("Error while reading user input from Shell", e);
      System.out.println(
          "Error reading input. Please enter valid number for proceeding further");
      return -1;
    }
  }

  /**
   * method to read vehicle registration number from user.
   *
   * @return vehicle registration number
   * @throws Exception
   */
  public String readVehicleRegistrationNumber() throws Exception {
    try {
      String vehicleRegNumber = scan.nextLine();
      if (vehicleRegNumber == null || vehicleRegNumber.trim().length() == 0) {
        throw new IllegalArgumentException("Invalid input provided");
      }
      return vehicleRegNumber;
    } catch (Exception e) {
      LOGGER.error("Error while reading user input from Shell", e);
      System.out.println(
          "Error reading input. Please enter a valid string for vehicle registration number");
      throw e;
    }
  }

}
