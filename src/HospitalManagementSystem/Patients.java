package HospitalManagementSystem;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patients {

    // Interfaces used for connection and fetching input
    private Connection connection;
    private Scanner scanner;

    public Patients(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    public void addPatient() {
        System.out.println("Enter Patient Name: ");
        String name = scanner.next();
        System.out.println("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.println("Enter Patient Gender: ");
        String gender = scanner.next();

        try {
            String query = "INSERT INTO patient(name,age,gender) VALUES (?,?,?)";
            //PreparedStatement used to pass the sql query in java
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully!!");
            } else {
                System.out.println("Failed to add Patient!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewPatients(){
            String query = "select * from patient";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                //It holds the table fetched from database and set a next pointer to it which prints the value.
                ResultSet resultSet = preparedStatement.executeQuery();
                //Format specifier - in table form
                System.out.println("Patients: ");
                System.out.println("+------------+---------------+------+---------+");
                System.out.println("| Patient Id | Name          | Age  | Gender  |");
                System.out.println("+------------+---------------+------+---------+");
                 while(resultSet.next()) {
                     int id = resultSet.getInt("id");
                     String name = resultSet.getString("name");
                     int age = resultSet.getInt("age");
                     String gender = resultSet.getString("gender");
                     System.out.printf("| %-10s | %-13s | %-5s | %-6s |\n",id,name,age,gender);
                     System.out.println("+------------+---------------+------+---------+");
                 }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
    }
    public boolean getPatientById(int id){
        String query = "SELECT * from patient where id = ?";
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
