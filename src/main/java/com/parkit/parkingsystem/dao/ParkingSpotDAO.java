package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Parking Database access class: interactions with database, parking table.
 *
 */
public class ParkingSpotDAO {

  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("ParkingSpotDAO");

  /**
   * dataBaseConfig instance.
   */
  public DataBaseConfig dataBaseConfig = new DataBaseConfig();

  /**
   * method to get next available parking spot.
   *
   * @param parkingType match the vehicule type
   * @return number of parking spot available
   */
  public int getNextAvailableSlot(ParkingType parkingType) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int result = -1;
    try {
      con = dataBaseConfig.getConnection();
      ps = con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
      ps.setString(1, parkingType.toString());
      rs = ps.executeQuery();
      if (rs.next()) {
        result = rs.getInt(1);
      }
    } catch (Exception ex) {
      LOGGER.error("Error fetching next available slot", ex);
    } finally {
      dataBaseConfig.closeResultSet(rs);
      dataBaseConfig.closePreparedStatement(ps);
      dataBaseConfig.closeConnection(con);
    }
    return result;
  }

  /**
   * method to update a parking spot availability.
   *
   * @param parkingSpot to set availability
   * @return false if error
   */
  public boolean updateParking(ParkingSpot parkingSpot) {
    // update the availability fo that parking slot
    try (Connection con = dataBaseConfig.getConnection(); PreparedStatement ps =
        con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);) {
      ps.setBoolean(1, parkingSpot.isAvailable());
      ps.setInt(2, parkingSpot.getId());
      int updateRowCount = ps.executeUpdate();
      return (updateRowCount == 1);
    } catch (Exception ex) {
      LOGGER.error("Error updating parking info", ex);
      return false;
    }
  }

}
