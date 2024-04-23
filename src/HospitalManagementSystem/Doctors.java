package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctors {
    // Interfaces used for connection and fetching input
    private Connection connection;

    public Doctors(Connection connection){
        this.connection = connection;

    }

    public void viewDoctors(){
        String query = "select * from doctor";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //It holds the table fetched from database and set a next pointer to it which prints the value.
            ResultSet resultSet = preparedStatement.executeQuery();
            //Format specifier - in table form
            System.out.println("Doctors: ");
            System.out.println("+------------+---------------+----------------+");
            System.out.println("| Doctor Id | Name          | Specialization  |");
            System.out.println("+------------+---------------+----------------+");
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String  specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-13s | %-14s |\n",id,name,specialization);
                System.out.println("+------------+---------------+------+---------+");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getDoctorById(int id){
        String query = "SELECT * from doctor where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){ // If row exists the true
                return true;
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
