package com.parkit.parkingsystem.constants;

/**
 *
 * DBCOnstants class: SQL Queries as final Strings.
 *
 */
public class DBConstants {

  /**
   * Query for next available parking spot.
   */
  public static final String GET_NEXT_PARKING_SPOT =
      "select min(PARKING_NUMBER) from parking where AVAILABLE = true and TYPE = ?";

  /**
   * Query to set availability on parking spot.
   */
  public static final String UPDATE_PARKING_SPOT =
      "update parking set available = ? where PARKING_NUMBER = ?";

  /**
   * Query to save a ticket into ticket table.
   */
  public static final String SAVE_TICKET =
      "insert into ticket(PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME) values(?,?,?,?,?)";

  /**
   * Query to update exit information on ticket.
   */
  public static final String UPDATE_TICKET =
      "update ticket set PRICE=?, OUT_TIME=? where ID=?";

  /**
   * Query to get a ticket from database.
   */
  public static final String GET_TICKET =
      "select t.PARKING_NUMBER, t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t,parking p where p.parking_number = t.parking_number and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME desc limit 1";

  /**
   * Query to count numbers of ticket of a specific vehicule.
   */
  public static final String GET_TICKET_COUNT =
      "SELECT COUNT(*) FROM ticket WHERE VEHICLE_REG_NUMBER=?";
}
