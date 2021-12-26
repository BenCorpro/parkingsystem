package com.parkit.parkingsystem.model;

import java.util.Date;

/**
 *
 * class that represents a parking ticket.
 *
 */
public class Ticket {
  /**
   * ticket id.
   */
  private int id;
  /**
   * parking spot for the ticket.
   */
  private ParkingSpot parkingSpot;
  /**
   * vehicle registration number of the vehicle parked.
   */
  private String vehicleRegNumber;
  /**
   * price.
   */
  private double price;
  /**
   * time of entry.
   */
  private Date inTime;
  /**
   * time of exit.
   */
  private Date outTime;

  /**
   * ticket id getter.
   *
   * @return ticket number
   */
  public int getId() {
    return id;
  }

  /**
   * ticket id setter.
   *
   * @param id ticket number
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * parking spot getter.
   *
   * @return parking spot of the ticket
   */
  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  /**
   * parking spot setter.
   *
   * @param parkingSpot for the ticket
   */
  public void setParkingSpot(ParkingSpot parkingSpot) {
    this.parkingSpot = parkingSpot;
  }

  /**
   * ticket vehicleRegNumber getter.
   *
   * @return vehicleRegNumber
   */
  public String getVehicleRegNumber() {
    return vehicleRegNumber;
  }

  /**
   * ticket vehicleRegNumber setter.
   *
   * @param vehicleRegNumber
   */
  public void setVehicleRegNumber(String vehicleRegNumber) {
    this.vehicleRegNumber = vehicleRegNumber;
  }

  /**
   * price getter.
   *
   * @return price
   */
  public double getPrice() {
    return price;
  }

  /**
   * price setter.
   *
   * @param price of ticket
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * vehicle in time getter.
   *
   * @return as Date
   */
  public Date getInTime() {
    if (inTime != null)
      return new Date(inTime.getTime());
    else {
      return null;
    }
  }

  /**
   * vehicle in time setter.
   *
   * @param inTime as Date
   */
  public void setInTime(Date inTime) {
    if (inTime != null)
      this.inTime = new Date(inTime.getTime());
    else {
      this.inTime = null;
    }
  }

  /**
   * vehicle out time getter.
   *
   * @return as Date
   */
  public Date getOutTime() {
    if (outTime != null)
      return new Date(outTime.getTime());
    else {
      return null;
    }
  }

  /**
   * vehicle out time setter.
   *
   * @param outTime as Date
   */
  public void setOutTime(Date outTime) {
    if (outTime != null)
      this.outTime = new Date(outTime.getTime());
    else {
      this.outTime = null;
    }
  }
}
