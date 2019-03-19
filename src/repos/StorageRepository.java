package repos;

import DB.DBConnection;
import models.Source;
import models.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StorageRepository {
    private Connection connection;

    public StorageRepository() {
        connection = DBConnection.getConnection();
    }

    public ArrayList<Storage> getAll() {

        ArrayList<Storage> list = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM storages ORDER BY id DESC");
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Long sourceId = resultSet.getLong("source_id");
                list.add(new Storage(id, name, getSourceById(sourceId)));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Storage> getAllBySource(Long sourceId) {

        ArrayList<Storage> list = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM storages where source_id = ? ORDER BY id DESC");
            statement.setLong(1, sourceId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                list.add(new Storage(id, name, getSourceById(sourceId)));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Storage getById(Long id) {

        Storage element = null;

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM storages WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Long sourceId = resultSet.getLong("source_id");
                element = new Storage(id, name, getSourceById(sourceId));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return element;

    }

    public void add(Storage element) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO storages (id, name, source_id) " +
                            "VALUES (NULL, ?,?)"
            );

            statement.setString(1, element.getName());
            statement.setLong(2, element.getSource().getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Source getSourceById(Long id) {
        SourceRepository sourceRepository = new SourceRepository();
        return sourceRepository.getById(id);
    }


    public boolean deleteById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM storages WHERE id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

}