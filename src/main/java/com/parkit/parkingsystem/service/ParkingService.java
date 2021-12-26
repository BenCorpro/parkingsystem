package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * class that processes vehicle and parking spots.
 *
 *
 */
public class ParkingService {

  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("ParkingService");

  /**
   * instance of FareCalculatorService.
   */
  private static FareCalculatorService fareCalculatorService =
      new FareCalculatorService();

  /**
   * InputReaderUtil declaration.
   */
  private InputReaderUtil inputReaderUtil;
  /**
   * ParkingSpotDAO declaration.
   */
  private ParkingSpotDAO parkingSpotDAO;
  /**
   * TicketDAO declaration.
   */
  private TicketDAO ticketDAO;

  /**
   * constructor.
   *
   * @param inputReaderUtil
   * @param parkingSpotDAO
   * @param ticketDAO
   */
  public ParkingService(InputReaderUtil inputReaderUtil,
      ParkingSpotDAO parkingSpotDAO, TicketDAO ticketDAO) {
    this.inputReaderUtil = inputReaderUtil;
    this.parkingSpotDAO = parkingSpotDAO;
    this.ticketDAO = ticketDAO;
  }

  /**
   * Entering vehicle method.
   */
  public void processIncomingVehicle() {
    try {
      ParkingSpot parkingSpot = getNextParkingNumberIfAvailable();
      if (parkingSpot != null && parkingSpot.getId() > 0) {
        String vehicleRegNumber = getVehichleRegNumber();
        if (ticketDAO.getTicket(vehicleRegNumber) != null)
          System.out.println(
              "Welcome back! As a recurring user of our parking lot, you'll benefit from a 5% discount.");
        parkingSpot.setAvailable(false);
        parkingSpotDAO.updateParking(parkingSpot); // allot this parking space and mark it's
                                                  // availability as false

        Date inTime = new Date();
        Ticket ticket = new Ticket();
        // ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
        // ticket.setId(ticketID);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(vehicleRegNumber);
        ticket.setPrice(0);
        ticket.setInTime(inTime);
        ticket.setOutTime(null);
        ticketDAO.saveTicket(ticket);
        System.out.println("Generated Ticket and saved in DB");
        System.out.println(
            "Please park your vehicle in spot number:" + parkingSpot.getId());
        System.out.println("Recorded in-time for vehicle number:"
            + vehicleRegNumber + " is:" + inTime);
      }
    } catch (Exception e) {
      LOGGER.error("Unable to process incoming vehicle", e);
    }
  }

  /**
   * get the vehicle registration number from the user.
   *
   * @return vehicle registration number
   * @throws Exception
   */
  private String getVehichleRegNumber() throws Exception {
    System.out.println(
        "Please type the vehicle registration number and press enter key");
    return inputReaderUtil.readVehicleRegistrationNumber();
  }

  /**
   * get parking availability.
   *
   * @return a parking spot instance (if) available
   */
  public ParkingSpot getNextParkingNumberIfAvailable() {
    int parkingNumber = 0;
    ParkingSpot parkingSpot = null;
    try {
      ParkingType parkingType = getVehichleType();
      parkingNumber = parkingSpotDAO.getNextAvailableSlot(parkingType);
      if (parkingNumber > 0) {
        parkingSpot = new ParkingSpot(parkingNumber, parkingType, true);
      } else {
        throw new Exception(
            "Error fetching parking number from DB. Parking slots might be full");
      }
    } catch (IllegalArgumentException ie) {
      LOGGER.error("Error parsing user input for type of vehicle", ie);
    } catch (Exception e) {
      LOGGER.error("Error fetching next available parking slot", e);
    }
    return parkingSpot;
  }

  /**
   * get the vehicle type from the user .
   *
   * @return a parking type matching the vehicle
   */
  private ParkingType getVehichleType() {
    System.out.println("Please select vehicle type from menu");
    System.out.println("1 CAR");
    System.out.println("2 BIKE");
    int input = inputReaderUtil.readSelection();
    switch (input) {
    case 1: {
      return ParkingType.CAR;
    }
    case 2: {
      return ParkingType.BIKE;
    }
    default: {
      System.out.println("Incorrect input provided");
      throw new IllegalArgumentException("Entered input is invalid");
    }
    }
  }

  /**
   * Exiting vehicle method.
   */
  public void processExitingVehicle() {
    try {
      String vehicleRegNumber = getVehichleRegNumber();
      Ticket ticket = ticketDAO.getTicket(vehicleRegNumber);
      Date outTime = new Date();
      ticket.setOutTime(outTime);
      fareCalculatorService.calculateFare(ticket);
      if (ticketDAO.getTicketCount(vehicleRegNumber) > 1) {
        BigDecimal bd = new BigDecimal(ticket.getPrice() / 100 * 95).setScale(2,
            RoundingMode.HALF_UP);
        double price = bd.doubleValue();
        ticket.setPrice(price);
      }
      if (ticketDAO.updateTicket(ticket)) {
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setAvailable(true);
        parkingSpotDAO.updateParking(parkingSpot);
        System.out.println("Please pay the parking fare:" + ticket.getPrice());
        System.out.println("Recorded out-time for vehicle number:"
            + ticket.getVehicleRegNumber() + " is:" + outTime);
      } else {
        System.out
            .println("Unable to update ticket information. Error occurred");
      }
    } catch (Exception e) {
      LOGGER.error("Unable to process exiting vehicle", e);
    }
  }
}
