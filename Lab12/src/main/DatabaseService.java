package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final String USER = "sa";
    static final String PASS = "";

    private static DatabaseService databaseService;
    private static Connection connection;

    private DatabaseService() {

    }

    public static DatabaseService getDatabaseService() {
        if (databaseService == null) {
            try {
                databaseService = new DatabaseService();
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch(SQLException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return databaseService;
    }

    public void generateStructure() {
        try {
            Statement statement = connection.createStatement();

            String sql;
            sql = "DROP TABLE IF EXISTS WORKER;";
            statement.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS REPORT;";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE WORKER " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "firstname VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "age INTEGER, " +
                    "position VARCHAR(255), " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE REPORT " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "rate INTEGER, " +
                    "time INTEGER, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(sql);

            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Ivan', 'Ivanov', 34, 'Director');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Petr', 'Petrov', 24, 'Programmer');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Dima', 'Dimov', 26, 'Saler');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Sanya', 'Sanev', 15, 'Just Sanya');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Serega', 'Sergeev', 24, 'Worker');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO WORKER(firstname, lastname, age, position) VALUES('Alexey', 'Alexeev', 24, 'Watcher');";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(5, 160);";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(6, 168);";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(4, 176);";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(1, 80);";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(5, 180);";
            statement.executeUpdate(sql);
            sql = "INSERT INTO REPORT(rate, time) VALUES(4, 220);";
            statement.executeUpdate(sql);

            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Worker> getWorkerList() {
        List<Worker> workerList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM WORKER";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                Worker worker = new Worker();
                worker.setId(rs.getInt( "id"));
                worker.setFirstname(rs.getString("firstname"));
                worker.setLastname(rs.getString("lastname"));
                worker.setAge(rs.getInt( "age"));
                worker.setPosition(rs.getString("position"));
                workerList.add(worker);
            }

            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return workerList;
    }

    public Report getReport(int id) {
        Report report = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM report " +
                            "WHERE (id = ?)");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                report = new Report();
                report.setId(resultSet.getInt( "id"));
                report.setRate(resultSet.getInt("rate"));
                report.setTime(resultSet.getInt("time"));
            }
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return report;
    }
}
