import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private  final static String  HOST     = "localhost"  ; // сервер базы данных
    private  final static String  DATABASENAME = "postgres"  ;// имя базы
    private  final static String  USERNAME = "postgres"; // учетная запись пользователя
    private  final static String  PASSWORD = "oleg28dec"; // пароль

    public  static void main(String[] args)
    {
        List<LoadModel> data = new ArrayList<>();
        String url="jdbc:postgresql://"+HOST+"/"+DATABASENAME+"?user="+USERNAME+"&password="+PASSWORD;
        try (Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD)) {
            if (connection == null)
                System.err.println("Нет соединения с БД!");
            else {
                System.out.println("Соединение с БД установлено корректно");
                String SQL = "Select * from public.viezd;";
                //Запрос на получение всех данных
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            int i = resultSet.getInt("lnp");
                            if (resultSet.wasNull()) {
                                System.out.println("NULL");
                            } else {
                                System.out.println(i);
                            }
                            //Добавляем каждый полученный элемент в наш лист
                            LoadModel loadModel = new LoadModel();
                            loadModel.i = i;
                            data.add(loadModel);
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
