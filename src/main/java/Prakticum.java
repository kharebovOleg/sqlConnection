import java.sql.*;
import java.util.Scanner;

public class Prakticum {

    private  final static String  HOST     = "localhost"  ; // сервер базы данных
    private  final static String  DATABASENAME = "postgres"  ;// имя базы
    private  final static String  USERNAME = "postgres"; // учетная запись пользователя
    private  final static String  PASSWORD = "oleg28dec"; // пароль пользователя
    static Connection connection;

    public static void main(String[] args) {

        String url = "jdbc:postgresql://" + HOST + "/" + DATABASENAME + "?user=" + USERNAME + "&password=" + PASSWORD;
        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            if (connection == null)
                System.err.println("Нет соединения с БД!");
            else {
                System.out.println("Соединение с БД установлено корректно");
                if (checkvalue(new Scanner(System.in).nextInt())) {
                    System.out.println("Число есть в таблице");
                } else {
                    System.out.println("Число отсутствует в таблице");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkvalue(int checkedvallue){
        String SQL = "Select * from public.viezd where lnp=?;";
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setInt(1, checkedvallue);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        return false;
    }
}
