package com.example.raspunsurimultiple.Repository;

import com.example.raspunsurimultiple.Domain.Intrebare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLRepository<T extends Intrebare> extends MemoryRepository<T> {
    private final String dbLocation;

    private Connection connection = null;
    public SQLRepository(String dbLocation) {
        this.dbLocation = "jdbc:sqlite:" + dbLocation;
        initDbConnection();
        createSchema();
        loadData();
    }


    private void initDbConnection() {
        try {
            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(dbLocation);
            if (connection == null || connection.isClosed())
                connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating DB connection " + e.getMessage());
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS intrebari(" +
                        "id int PRIMARY KEY, " +
                        "text varchar(200)," +
                        "raspunsA varchar(200), " +
                        "raspunsB varchar(200), " +
                        "raspunsC varchar(200), " +
                        "raspunsCorect varchar(200), " +
                        "dificultate varchar(200));"
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating DB schema " + e.getMessage());
        }
    }

    public void loadData() {
        for (T produs : getAll()) {
            data.add(produs);
        }
    }

    public List<T> getAll()
    {
        List<Intrebare> produses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM intrebari")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                produses.add(new Intrebare(
                        rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("raspunsA"),
                        rs.getString("raspunsB"),
                        rs.getString("raspunsC"),
                        rs.getString("raspunsCorect"),
                        rs.getString("dificultate")
                        )
                );
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (List<T>) produses;
    }

    @Override
    public void add(T entity) {
        super.add(entity);
        try {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO intrebari VALUES (?,?,?,?,?,?,?)")) {
                statement.setInt(1, entity.getID());
                statement.setString(2, entity.getText());
                statement.setString(3, entity.getRaspunsA());
                statement.setString(4, entity.getRaspunsB());
                statement.setString(5, entity.getRaspunsC());
                statement.setString(6, entity.getRaspunsCorect());
                statement.setString(7, entity.getDificultate());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
