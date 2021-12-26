package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;

/**
 * class that represents a parking spot.
 *
 */
public class ParkingSpot {
  /**
   * parking spot id.
   */
  private int number;
  /**
   * vehicle type of the spot.
   */
  private ParkingType parkingType;
  /**
   * availability of the spot.
   */
  private boolean isAvailable;

  /**
   * constructor.
   *
   * @param number id
   * @param parkingType vehicle type
   * @param isAvailable availability
   */
  public ParkingSpot(int number, ParkingType parkingType, boolean isAvailable) {
    this.number = number;
    this.parkingType = parkingType;
    this.isAvailable = isAvailable;
  }

  /**
   * parkingSpot id getter.
   *
   * @return parking spot number
   */
  public int getId() {
    return number;
  }

  /**
   * parking spot id setter.
   *
   * @param number of parking spot
   */
  public void setId(int number) {
    this.number = number;
  }

  /**
   * parking type getter.
   *
   * @return parking type of the spot
   */
  public ParkingType getParkingType() {
    return parkingType;
  }

  /**
   * parking type setter.
   *
   * @param parkingType of the spot
   */
  public void setParkingType(ParkingType parkingType) {
    this.parkingType = parkingType;
  }

  /**
   * availability getter.
   *
   * @return availability of parking spot
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * availability setter.
   *
   * @param available
   */
  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ParkingSpot that = (ParkingSpot) o;
    return number == that.number;
  }

  @Override
  public int hashCode() {
    return number;
  }
}
