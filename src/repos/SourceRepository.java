package repos;

import DB.DBConnection;
import models.Source;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SourceRepository {

    private Connection connection;
    public SourceRepository(){
        connection = DBConnection.getConnection();
    }

    public ArrayList<Source> getAll(){

        ArrayList<Source> list = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sources ORDER BY id DESC");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String longitude = resultSet.getString("longitude");
                String latitude = resultSet.getString("latitude");
                list.add(new Source(id, name, longitude, latitude));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;

    }

    public Source getById(Long id){

        Source element = null;

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sources where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String name = resultSet.getString("name");
                String longitude = resultSet.getString("longitude");
                String latitude = resultSet.getString("latitude");
                element = new Source(id, name, longitude, latitude);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return element;

    }

    public void add(Source element){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO sources (id, name, longitude, latitude) " +
                    "VALUES (NULL, ?, ?, ?)"
            );

            statement.setString(1, element.getName());
            statement.setString(2, element.getLongitude());
            statement.setString(3, element.getLatitude());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean deleteById(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "delete from sources where id = ?"
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
