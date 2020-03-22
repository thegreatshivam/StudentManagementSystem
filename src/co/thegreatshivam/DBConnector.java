package co.thegreatshivam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.*;

public class DBConnector {
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static void dbConnect(){
        try{
            String url = "jdbc:mysql://localhost/studentdbms";
            String username = "thegreatshivam";
            String password = "password";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    private static void dbDisconnect() throws Exception{
        connection.close();
    }

    public static void insert_student(int id, String name, long mobNo, String email) throws Exception {
        dbConnect();

        String insertQuery = "INSERT INTO student (id, name, mob_num, email) VALUES (?, ?, ?, ?);";
        try{
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setLong(3, mobNo);
            preparedStatement.setString(4, email);
            int status = preparedStatement.executeUpdate();
            if(status == 1){
                AlertBox.display("Message","Student inserted successfully");
            }else{
                AlertBox.display("Message", "Student didn't inserted");
            }
        }catch(SQLIntegrityConstraintViolationException e){
            AlertBox.display(350, "Message", "Student with ID : " + id + " already exist");
        }catch(Exception e){
            AlertBox.display("Message", "Something went wrong");
        }

        dbDisconnect();
    }

    public static void listAllStudent(TableView<Student> tableView) throws Exception{
        dbConnect();

        String selectQuery = "SELECT * FROM student;";
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);
            ObservableList<Student> data = FXCollections.observableArrayList();
            while(resultSet.next()){
                data.add(new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getLong("mob_num"), resultSet.getString("email")));
            }
            tableView.setItems(data);
            dbDisconnect();

        }catch (SQLException e){
            AlertBox.display("Message", "Something went wrong");
        }
    }

    public static void deleteStudent(int id) throws Exception{
        dbConnect();
        String query = "DELETE FROM student WHERE id = " + id + ";";
        try{
            statement = connection.createStatement();
            int status = statement.executeUpdate(query);
            if(status == 1){
                AlertBox.display("Message", "Student with id : " + id + " deleted");
            }else{
                AlertBox.display("Message", "Student with id : " + id + " doesn't exist");
            }
        }catch(SQLException e){
            AlertBox.display("Message", "Something went wrong");
        }
        dbDisconnect();
    }

    public static Student searchStudent(int id) throws Exception {
        dbConnect();

        Student student = new Student();
        String query = "SELECT * FROM student WHERE id = " + id + ";";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSet.next();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setMobNum(resultSet.getLong("mob_num"));
        student.setEmail(resultSet.getString("email"));

        dbDisconnect();
        return student;
    }

    public static void alterStudent(int id, String name, long mobNum, String email) throws Exception {
        dbConnect();

        String query = "UPDATE student SET name = ?, mob_num = ?, email = ? WHERE id = ?;";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, mobNum);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, id);
            int status = preparedStatement.executeUpdate();
            if(status == 1){
                AlertBox.display("Message", "Student with id : " + id + " altered");
            }else{
                AlertBox.display("Message", "Student with id : " + id + " doesn't exist");
            }
        }catch(SQLException e){
            AlertBox.display("Message", "Something went wrong");
        }
        dbDisconnect();
    }

    public static int studentExist(int id) throws Exception {
        dbConnect();
        String query = "SELECT EXISTS(SELECT * FROM student WHERE id = ?) AS ?;";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, "num");

        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int status = resultSet.getInt("num");

        dbDisconnect();
        return status;
    }
}
