package org.example.database;

import org.example.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabasePopulateService {
    public static void main(String[] args) throws IOException {
        String insertFileName = new Prefs().getPref(Prefs.INSERT_SQL_FILE_PATH);
        String sql = Files.readString(Paths.get(insertFileName));

        Database database = Database.getInstance();

        try( Connection connection = database.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
        database.close();
    }
}
