package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class of user interface: display a menu with different options for the user to choose.
 *
 */
public class InteractiveShell {

  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("InteractiveShell");

  /**
   * method to read the user selection.
   */
  public static void loadInterface() {
    LOGGER.info("App initialized!!!");
    System.out.println("Welcome to Parking System!");

    boolean continueApp = true;
    InputReaderUtil inputReaderUtil = new InputReaderUtil();
    ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
    TicketDAO ticketDAO = new TicketDAO();
    ParkingService parkingService =
        new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

    while (continueApp) {
      loadMenu();
      int option = inputReaderUtil.readSelection();
      switch (option) {
      case 1: {
        parkingService.processIncomingVehicle();
        break;
      }
      case 2: {
        parkingService.processExitingVehicle();
        break;
      }
      case 3: {
        System.out.println("Exiting from the system!");
        continueApp = false;
        break;
      }
      default:
        System.out.println(
            "Unsupported option. Please enter a number corresponding to the provided menu");
      }
    }
  }

  /**
   * method to display a menu to the user.
   */
  private static void loadMenu() {
    System.out.println(
        "Please select an option. Simply enter the number to choose an action");
    System.out.println("1 New Vehicle Entering - Allocate Parking Space");
    System.out.println("2 Vehicle Exiting - Generate Ticket Price");
    System.out.println("3 Shutdown System");
  }

}
