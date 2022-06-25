
package com.example.librarymanagementsystem;
import java.sql.*;

public class DBUtil {

    //requesting permission from admin to create user
    public static Boolean requestUser(String username, String password_1, String contact) throws SQLException {
        Connection connection = null;PreparedStatement psInsert = null;PreparedStatement psCheckUserExists = null;ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM request_user WHERE username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                return false;
            }

            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                return false;
            }
            //System.out.println("No username previously exists, creating account.....");
            psInsert = connection.prepareStatement("INSERT INTO request_user (username,password,contact) VALUES (?,?,?)");
            psInsert.setString(1,username);
            psInsert.setString(2,password_1);
            psInsert.setString(3,contact);
            psInsert.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("ERRORRR");
            e.printStackTrace();}
        finally{
            if(resultSet != null){try{resultSet.close();} catch (SQLException e){e.printStackTrace();};}
            if(psInsert != null){try{psInsert.close();} catch (SQLException e){e.printStackTrace();};}
            if(psCheckUserExists != null){try{psCheckUserExists.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        throw new SQLException();
    }

    public static Boolean signUpUser(String username, String password_1, String contact) throws SQLException
    {

        Connection connection = null; PreparedStatement psInsert = null; PreparedStatement psCheckUserExists = null; ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1,username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                return false;
            }
            //System.out.println("No username previously exists, creating account.....");
            psInsert = connection.prepareStatement("INSERT INTO users (username,password,contact) VALUES (?,?,?)");
            psInsert.setString(1,username);
            psInsert.setString(2,password_1);
            psInsert.setString(3,contact);
            psInsert.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();}
        finally{
            if(resultSet != null){try{resultSet.close();} catch (SQLException e){e.printStackTrace();};}
            if(psInsert != null){try{psInsert.close();} catch (SQLException e){e.printStackTrace();};}
            if(psCheckUserExists != null){try{psCheckUserExists.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        return false;
    }

    public static boolean logInAdmin(String username, String password) throws Exception {
        Connection connection = null; PreparedStatement psCheckUserExists = null; ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM admin WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    String retrievedPassord = resultSet.getString("password");
                    if(retrievedPassord.equals(password)){
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        }
        finally{
            if(resultSet != null){try{resultSet.close();} catch (SQLException e){e.printStackTrace();};}
            if(psCheckUserExists != null){try{psCheckUserExists.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        return false;
    }

    public static boolean logInUser(String username, String password) throws SQLException {
        Connection connection = null;PreparedStatement psCheckUserExists = null;ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "admin");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                    String retrievedPassord = resultSet.getString("password");
                    if(retrievedPassord.equals(password)){
                        return true;
                    }
                }
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if(resultSet != null){try{resultSet.close();} catch (SQLException e){e.printStackTrace();};}
            if(psCheckUserExists != null){try{psCheckUserExists.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        return false;
    }


    static boolean deleteRequest(String username)
    {
        Connection connection = null; PreparedStatement delete_requestUser = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            delete_requestUser = connection.prepareStatement("DELETE FROM request_user WHERE username = ?;");
            delete_requestUser.setString(1,username);
            delete_requestUser.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("ERRORRR");
            e.printStackTrace();}
        finally{
            if(delete_requestUser!= null){try{delete_requestUser.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        return false;
    }


    static boolean acceptRequest(Users users) throws SQLException
    {
        try {
            return deleteRequest(users.getName()) && signUpUser(users.getName(), users.getPassword(), users.getContact());
        } catch (Exception exception){throw new SQLException();}
    }




    static Boolean addDate(String username, String date) throws SQLException {

        Connection connection = null; PreparedStatement Date = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            Date = connection.prepareStatement("UPDATE users\n" +
                                                    "SET date = ?\n" +
                                                    "WHERE username = ?;");
            Date.setString(1,date);
            Date.setString(2,username);
            Date.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("ERRORRR");
            e.printStackTrace();}
        finally{
            if(Date != null){try{Date.close();} catch (SQLException e){e.printStackTrace();};}
            if(connection != null){try{connection.close();} catch (SQLException e){e.printStackTrace();};}
        }
        return false;
    }
}

