package org.drive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class MeatProductMenu implements ProductMenu {

    Login login=new Login();
    Scanner scanner=new Scanner(System.in);
    public void showMenu(int userType) {
        Connection connection=login.getConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from MeatList");
            String name="";
            int count=0;
            System.out.println("Menu of MeatProducts");
            System.out.println("ProductName          Quantity");
            while(resultSet.next()) {
                name = resultSet.getString("productname");
                count = resultSet.getInt("quantity");
                System.out.println(name+"                     "+count);
            }
            showAddButton(userType);
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }

    public void showAddButton(int userType) {


    }

    public void showViewButton() {

    }

    public void showRadioButton() {

    }

    public void showLabels() {

    }

    public void showComboxes() {

    }

}
