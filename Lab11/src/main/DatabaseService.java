package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    public void generateStructure() throws Exception {
        Connection connection = ConnectionService.getConnection();
        Statement statement = connection.createStatement();

        String sql;
        sql = "DROP TABLE IF EXISTS city;";
        statement.executeUpdate(sql);
        sql = "DROP TABLE IF EXISTS citizen;";
        statement.executeUpdate(sql);

        sql = "CREATE TABLE city " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "year INTEGER, " +
                "area INTEGER, " +
                "PRIMARY KEY (id))";
        statement.executeUpdate(sql);

        sql = "CREATE TABLE citizen " +
                "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                "type VARCHAR(255), " +
                "language VARCHAR(255), " +
                "population INTEGER, " +
                "city_id INTEGER, " +
                "FOREIGN KEY (city_id) references city(id), " +
                "PRIMARY KEY (id))";
        statement.executeUpdate(sql);

        sql = "INSERT INTO city(name, year, area) VALUES('New York', 1624, 1215);";
        statement.executeUpdate(sql);
        sql = "INSERT INTO city(name, year, area) VALUES('Moscow', 1795, 606);";
        statement.executeUpdate(sql);
        sql = "INSERT INTO citizen(type, language, population, city_id) VALUES('Native', 'English', 17000000, 1);";
        statement.executeUpdate(sql);
        sql = "INSERT INTO citizen(type, language, population, city_id) VALUES('Foreign', 'German', 3000000, 1);";
        statement.executeUpdate(sql);
        sql = "INSERT INTO citizen(type, language, population, city_id) VALUES('Native', 'Russian', 18000000, 2);";
        statement.executeUpdate(sql);
        sql = "INSERT INTO citizen(type, language, population, city_id) VALUES('Foreign', 'French', 1000000, 2);";
        statement.executeUpdate(sql);

        statement.close();
    }

    public List<String> getInfoByCityAndLanguage(String cityName, String language) throws Exception {
        Connection connection = ConnectionService.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT type, language, population FROM citizen " +
                        "JOIN city on city_id = city.id " +
                        "WHERE (city.name = ?) AND (citizen.language = ?)");
        preparedStatement.setString(1, cityName);
        preparedStatement.setString(2, language);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> list = new ArrayList<>();

        while(resultSet.next()) {
            list.add("Type: " + resultSet.getString(1)
                    + ", language: " + resultSet.getString(2)
                    + ", population: " + resultSet.getString(3));
        }

        return list;
    }

    public List<String> getInfoByType(String type) throws Exception {
        Connection connection = ConnectionService.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT city.name, city.year, city.area FROM citizen " +
                        "JOIN city on city_id = city.id " +
                        "WHERE citizen.type = ?");
        preparedStatement.setString(1, type);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> infoList = new ArrayList<>();

        while(resultSet.next()) {
            infoList.add("Name: " + resultSet.getString(1)
                    + ", year: " + resultSet.getString(2)
                    + ", area: " + resultSet.getString(3));
        }

        preparedStatement.close();

        return infoList;
    }

    public List<String> getInfoByPopulation(long population) throws Exception {
        Connection connection = ConnectionService.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT city.name, city.year, city.area FROM citizen " +
                        "JOIN city on city_id = city.id " +
                        "GROUP BY city.name " +
                        "HAVING SUM(population) = ?");
        preparedStatement.setLong(1, population);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> cityNameList = new ArrayList<>();

        List<String> infoList = new ArrayList<>();

        while(resultSet.next()) {
            infoList.add("Name: " + resultSet.getString(1)
                    + ", year: " + resultSet.getString(2)
                    + ", area: " + resultSet.getString(3));

            cityNameList.add(resultSet.getString(1));
        }

        cityNameList.forEach(x -> {
            try {
                PreparedStatement preparedStatement1 = connection.prepareStatement(
                         "SELECT type, language, population FROM citizen " +
                                "JOIN city on city_id = city.id " +
                                "WHERE city.name = ?");
                preparedStatement1.setString(1, x);

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while(resultSet1.next()) {
                    infoList.add("Type: " + resultSet1.getString(1)
                            + ", language: " + resultSet1.getString(2)
                            + ", population: " + resultSet1.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        preparedStatement.close();

        return infoList;
    }

    public List<String> getInfoOldest() throws Exception {
        Connection connection = ConnectionService.getConnection();
        Statement statement = connection.createStatement();

        String sql;
        sql = "SELECT type, language, population FROM citizen " +
                        "JOIN city on city_id = city.id " +
                        "WHERE city.year = (SELECT MIN(year) FROM city)";

        List<String> infoList = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            infoList.add("Type: " + resultSet.getString(1)
                    + ", language: " + resultSet.getString(2)
                    + ", population: " + resultSet.getString(3));
        }

        statement.close();

        return infoList;
    }
}
