package org.drive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login extends Facade {

    Scanner scanner=new Scanner(System.in);
    Buyer buyer=new Buyer();
    Seller seller=new Seller();
    public boolean login()
    {
        System.out.println("Welcome");
        System.out.println("Please choose your user type");
        System.out.println("Type Buyer if you are a buyer");
        System.out.println("Type Seller if you are a seller");
        String type=scanner.next();
        int userType=-1;
        if(type.equalsIgnoreCase("buyer"))
        {
            userType=0;
            setUserType(userType);
            buyerLogin(userType);
        }
        else if(type.equalsIgnoreCase("seller"))
        {
            userType=1;
            setUserType(userType);
            sellerLogin(userType);
        }
        return  true;
    }

    public void buyerLogin(int userType)
    {
        System.out.println("Please Enter your UserName");
        String userName=scanner.next();
        System.out.println("Please Enter your Password");
        String password=scanner.next();
        Connection connection=getConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from buyer where username= '"+userName+"' ");
            String name="";
            String pwd="";
            if(resultSet.next()) {
                name = resultSet.getString("username");
                pwd = resultSet.getString("password");
            }
            if(name.equals(userName))
            {
                if(password.equals(pwd))
                {
                    System.out.println("Login successful! Welcome "+userName);
                    buyer.showMenu(userType);
                }
                else
                {
                    System.out.println("Please Check your username/password you have entered");
                    buyerLogin(userType);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
    public void sellerLogin(int userType)
    {
        System.out.println("Please Enter your UserName");
        String userName=scanner.next();
        System.out.println("Please Enter your Password");
        String password=scanner.next();
        Connection connection=getConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from seller where username= '"+userName+"' ");
            String name="";
            String pwd="";
            if(resultSet.next()) {
                name = resultSet.getString("username");
                pwd = resultSet.getString("password");
            }
            if(name.equals(userName))
            {
                if(password.equals(pwd))
                {
                    System.out.println("Login successful! Welcome "+userName);
                    seller.showMenu(userType);
                }
                else
                {
                    System.out.println("Please Check your username/password you have entered");
                    sellerLogin(userType);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }
    public Connection getConnection()
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/designpattern",
                    "root", "Vamsiking1.");

        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        return connection;
    }

}
