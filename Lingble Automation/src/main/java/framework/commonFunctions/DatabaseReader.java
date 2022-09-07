package framework.commonFunctions;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import framework.environment.BaseTestCase;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;

/**
 * Database reader
 * Please do not modify or if needed kindly communicate with the author
 @author alexander.v.pangilinan
 */


public class DatabaseReader {
    protected static final Logger LOGGER = BaseTestCase.getlog();
    private static Connection connection = null;
    private static Session session = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    private static int forwardedPort;

    public void connectSSH(String sshHost, String sshuser, String sshpassword, int sshport, String DBHostUrl, int DBport){
        try {
            java.util.Properties config = new java.util.Properties();
            JSch jsch = new JSch();
            session = jsch.getSession(sshuser, sshHost, sshport);
//            jsch.addIdentity(SshKeyFilepath);
            session.setPassword(sshpassword);
            config.put("StrictHostKeyChecking", "no");
            config.put("Compression", "yes");
            config.put("ConnectionAttempts", "3");
            session.setConfig(config);
            session.connect();

            LOGGER.info("SSH Connected");

            forwardedPort = session.setPortForwardingL(0, DBHostUrl, DBport);
            LOGGER.info("localhost:" + forwardedPort + " -> " + DBHostUrl + ":" + DBport);
            LOGGER.info("Port Forwarded");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void connectDataBaseSSH(String DBTable, String DBusername, String DBpassword ) {
        String url = "jdbc:mysql://localhost:" + forwardedPort+"/"+DBTable;

        try {
            Class.forName(identifyDriver(url)).newInstance();
        }
        catch(ClassNotFoundException ex) {
            LOGGER.info("Error: unable to load driver class!");
        }
        catch(IllegalAccessException ex) {
            LOGGER.info("Error: access problem while loading!");
        }
        catch(InstantiationException ex) {
            LOGGER.info("Error: unable to instantiate driver!");
        }
        try {

            connection = DriverManager.getConnection(url, DBusername, DBpassword);
//            connection.setAutoCommit(false);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            LOGGER.info("Connection to server successful!:" + connection + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectDataBase(String DBUrl, int DBport, String DBTable, String DBusername, String DBpassword) {

        String url = "jdbc:mysql://"+DBUrl+":"+DBport+"/"+DBTable;
        try {
            Class.forName(identifyDriver(url)).newInstance();
        }
        catch(ClassNotFoundException ex) {
            LOGGER.info("Error: unable to load driver class!");
        }
        catch(IllegalAccessException ex) {
            LOGGER.info("Error: access problem while loading!");
        }
        catch(InstantiationException ex) {
            LOGGER.info("Error: unable to instantiate driver!");
        }
        try {
            connection = DriverManager.getConnection(url, DBusername, DBpassword);
//            connection.setAutoCommit(false);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            LOGGER.info("Connection to server successful!:" + connection + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void connectDataBase(String DBUrl, int DBport, String DBTable, String DBusername, String DBpassword, String Query) {

        String url = "jdbc:mysql://"+DBUrl+":"+DBport+"/"+DBTable;
        try {
            Class.forName(identifyDriver(url)).newInstance();
        }
        catch(ClassNotFoundException ex) {
            LOGGER.info("Error: unable to load driver class!");
        }
        catch(IllegalAccessException ex) {
            LOGGER.info("Error: access problem while loading!");
        }
        catch(InstantiationException ex) {
            LOGGER.info("Error: unable to instantiate driver!");
        }
        try {
            connection = DriverManager.getConnection(url, DBusername, DBpassword);
//            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(Query);
            preparedStatement.executeUpdate();
            LOGGER.info("Connection to server successful!:" + connection + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void closeDataBaseConnections() {
//        try {
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            if (session != null && session.isConnected()) {
                LOGGER.info("Closing SSH Connection");
                session.disconnect();
            }
        }   catch(Exception x){
            x.printStackTrace();
        }
        try {
            if (preparedStatement != null && session.isConnected()) {
                LOGGER.info("Closing SSH Connection");
                session.disconnect();
            }
        }   catch(Exception x){
            x.printStackTrace();
        }
        try {
            if (connection != null && !connection.isClosed()) {
                LOGGER.info("Closing Database Connection");
                connection.close();
                statement.close();
                resultSet.close();
            }
        }        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void writeDBQuery(String query) {

        try {
            // Execute the SQL Query. Store results in ResultSet
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public String readDBDataPerCell(String query, String columnName, int rowNumber) {
        String cellValue = "";
       try {
           resultSet = statement.executeQuery(query);
//            // While Loop to iterate through all data and print results
            resultSet.next();
            resultSet.absolute(rowNumber);
            cellValue = resultSet.getString(columnName);
       } catch (SQLException e) {

      }

        return cellValue;
    }

    public String readDBDataPerRow(String query, int columnCount, int rowNumber) {
        String rowValue = "";
        try {
            // Execute the SQL Query. Store results in ResultSet
            resultSet = statement.executeQuery(query);
            // While Loop to iterate through all data and print results
            for (int i = 1; i <= columnCount; ++i) {
                resultSet.next();
                resultSet.absolute(rowNumber);
                if (i == columnCount) {
                    if (resultSet.getString(i) == null) {
                        break;
                    } else {
                        rowValue += resultSet.getString(i).trim();
                        break;
                    }
                }
                if (resultSet.getString(i) == null) {
                    rowValue += ",";
                } else {
                    rowValue += resultSet.getString(i).trim() + ",";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowValue;
    }

    public String readDBDataPerColumn(String query, String columnName, int rowCount) {

        String columnValue = "";
        try{
            // Execute the SQL Query. Store results in ResultSet
            resultSet = statement.executeQuery(query);
            // While Loop to iterate through all data and print results
            for (int i = 1; i <= rowCount; ++i) {
                resultSet.next();
                resultSet.absolute(i);
                if (i == rowCount) {
                    columnValue += resultSet.getString(columnName).trim();
                    break;
                }
                columnValue += resultSet.getString(columnName).trim() + ",";
            }
        } catch (SQLException e) {
        }

        return columnValue;
    }

    public String identifyDriver(String dbUrl) {
        String[] arrStr = dbUrl.split(":");
        HashMap<String, String> map = new HashMap<String, String>(){{}};
        map.put("oracle","oracle.jdbc.map.OracleDriver");
        map.put("sqlserver","com.microsoft.sqlserver.jdbc.SQLServerDriver");
        map.put("db2","COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver");
        map.put("as400","com.ibm.as400.access.AS400JDBCDriver");
        map.put("postgresql","org.postgresql.Driver");
        map.put("mysql","com.mysql.cj.jdbc.Driver");
        return map.get(arrStr[1]);
    }
}
