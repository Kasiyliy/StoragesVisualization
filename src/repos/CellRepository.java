package repos;

import DB.DBConnection;
import models.Cell;
import models.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class CellRepository {

    private Connection connection;
    public CellRepository(){
        connection = DBConnection.getConnection();
    }

    public ArrayList<Cell> getAll(){

        ArrayList<Cell> list = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cells ORDER BY id DESC");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String invNumber = resultSet.getString("inv_number");
                Date expireDate = resultSet.getDate("expire_date");
                Date ownDate = resultSet.getDate("own_date");
                Double crn = resultSet.getDouble("crn");
                Double costPrice = resultSet.getDouble("cost_price");
                Long storageId = resultSet.getLong("storage_id");
                String imagePath = resultSet.getString("image_path");
                list.add(new Cell(id, name, invNumber, expireDate, ownDate, crn, costPrice, getStorageById(storageId), imagePath));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }
    public ArrayList<Cell> getAllByStorage(Long storageId){

        ArrayList<Cell> list = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cells where storage_id = ? ORDER BY id DESC");
            statement.setLong(1, storageId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String invNumber = resultSet.getString("inv_number");
                Date expireDate = resultSet.getDate("expire_date");
                Date ownDate = resultSet.getDate("own_date");
                Double crn = resultSet.getDouble("crn");
                Double costPrice = resultSet.getDouble("cost_price");
                String imagePath = resultSet.getString("image_path");
                list.add(new Cell(id, name, invNumber, expireDate, ownDate, crn, costPrice, getStorageById(storageId), imagePath));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public Cell getById(Long id){

        Cell element = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cells where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                String name = resultSet.getString("name");
                String invNumber = resultSet.getString("inv_number");
                Date expireDate = resultSet.getDate("expire_date");
                Date ownDate = resultSet.getDate("own_date");
                Double crn = resultSet.getDouble("crn");
                Double costPrice = resultSet.getDouble("cost_price");
                Long storageId = resultSet.getLong("storage_id");
                String imagePath = resultSet.getString("image_path");
                element = new Cell(id, name, invNumber, expireDate, ownDate, crn, costPrice, getStorageById(storageId), imagePath);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return element;

    }

    public void add(Cell element){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cells (id, name, inv_number, storage_id, crn, own_date, expire_date, cost_price, image_path) " +
                            "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, element.getName());
            statement.setString(2, element.getInvNumber());
            statement.setLong(3, element.getStorage().getId());
            statement.setDouble(4, element.getCrn());
            statement.setDate(5, new java.sql.Date(element.getOwnDate().getTime()));
            statement.setDate(6, new java.sql.Date(element.getExpireDate().getTime()));
            statement.setDouble(7, element.getCostPrice());
            statement.setString(8, element.getImagePath());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Storage getStorageById(Long id){
        StorageRepository storageRepository = new StorageRepository();
        return  storageRepository.getById(id);
    }

    public boolean deleteById(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "delete from cells where id = ?"
            );
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
