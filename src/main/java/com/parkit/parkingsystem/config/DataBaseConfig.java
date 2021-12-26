package com.parkit.parkingsystem.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DatabaseConfig class: connection and interaction with database.
 *
 */
public class DataBaseConfig {

  /**
   * get a Logger from LogManager.
   */
  private static final Logger LOGGER = LogManager.getLogger("DataBaseConfig");

  /**
   * Opening connection with database.
   *
   * @return connection
   * @throws ClassNotFoundException
   * @throws SQLException
   * @throws IOException
   */
  public Connection getConnection()
      throws ClassNotFoundException, SQLException, IOException {

    try (FileInputStream inputProp =
        new FileInputStream("src/main/resources/log4j.properties");) {
      Properties properties = new Properties();
      properties.load(inputProp);
      String url = properties.getProperty("urlprod");
      String user = properties.getProperty("user");
      String password = properties.getProperty("password");
      LOGGER.info("Create DB connection");
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(url, user, password);
    }
  }

  /**
   * closing database connection.
   *
   * @param con connection to be closed
   */
  public void closeConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
        LOGGER.info("Closing DB connection");
      } catch (SQLException e) {
        LOGGER.error("Error while closing connection", e);
      }
    }
  }

  /**
   * closing prepared statement.
   *
   * @param ps prepared statement to be closed
   */
  public void closePreparedStatement(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
        LOGGER.info("Closing Prepared Statement");
      } catch (SQLException e) {
        LOGGER.error("Error while closing prepared statement", e);
      }
    }
  }

  /**
   * closing resultset.
   *
   * @param rs resultset to be closed
   */
  public void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
        LOGGER.info("Closing Result Set");
      } catch (SQLException e) {
        LOGGER.error("Error while closing result set", e);
      }
    }
  }
}
