package com.mycompany.mockme;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoDbStuff {

//  private DataSource source;
  private String dbUrl = "jdbc:derby://localhost:1527/Trivial";
  private Driver driver;
  {
    try {
      driver = DriverManager.getDriver(dbUrl);
    } catch (SQLException ex) {
      Logger.getLogger(DoDbStuff.class.getName()).log(Level.SEVERE, null, ex);
    } 
  }
  private Properties dbProperties = new Properties();
  {
    dbProperties.put("user", "simon");
    dbProperties.put("password", "simon");
  }
  
  public String lookupData() throws Throwable {
    System.out.println("driver is a " + driver.getClass().getName());
    Connection con = driver.connect(dbUrl, dbProperties);
    System.out.println("con is a " + con.getClass().getName());
    Statement stat = con.createStatement();
    System.out.println("stat is a " + stat.getClass().getName());
    ResultSet rs = stat.executeQuery("SELECT * FROM SIMON.STUFF");
    System.out.println("rs is a " + rs.getClass().getName());
    StringBuilder sb = new StringBuilder("results: ");
    while (rs.next()) {
      if (rs.getString(1).equals("Valid")) {
        sb.append(rs.getString(2)).append(", ");
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) throws Throwable {
    System.out.println("Call returns:\n" + new DoDbStuff().lookupData());
  }
}
