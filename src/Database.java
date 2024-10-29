import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private Statement statement;

    public void connectSQLite() throws SQLException {
        try {
        String url = "jdbc:sqlite:db.sqlite";
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();
        System.out.println("SQLite Veritabanına bağlantı başarılı.");
    } catch (SQLException e) {
        System.out.println("SQLite Veritabanına bağlanırken hata oluştu: " + e.getMessage());
        }
    }

    public void connect() throws SQLException {
    try {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=db;encrypt=false;trustServerCertificate=true";
        String username = "sa";
        String password = "!#1Password";

        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
        System.out.println("Veritabanına bağlantı başarılı.");
    } catch (SQLException e) {
        System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
        connectSQLite();
    }
}


    public User LoginUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
              email = resultSet.getString("email");
              password = resultSet.getString("password");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            return new User(email, password, name, surname, age);
        } else {
            return null;
        }
    }

    public void registerUser(User NewUser) throws SQLException {
        String sql = "INSERT INTO users (name, surname, email, password, age) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, NewUser.getName());
        preparedStatement.setString(2, NewUser.getSurname());
        preparedStatement.setString(3, NewUser.getEmail());
        preparedStatement.setString(4, NewUser.getPassword());
        preparedStatement.setInt(5, NewUser.getAge());

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("Kayıt başarıyla eklendi: " + NewUser);
        } else {
            System.out.println("Kayıt eklenemedi!");
        }
    }

    public void usersList() throws SQLException {
        String sql = "SELECT * FROM users";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");

            System.out.println("Ad: " + name + ", Soyad: " + surname + ", Yaş: " + age + ", Email: " + email);
            System.out.println("----------------------------------------------------------");
        }
    }

    public void ageOrder() throws SQLException {
        String sql = "SELECT * FROM users ORDER BY age";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int age = rs.getInt("age");

            System.out.println("Ad: " + name + ", Soyad: " + surname + ", Yaş: " + age);
            System.out.println("----------------------------------------------------------");
        }
    }

    public void deleteUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "DELETE FROM Users WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Kayıt başarıyla silindi.");
        } else {
            System.out.println("Kayıt bulunamadı.");
        }
    }

    public void updatePassword(String email, String password) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, email);

        int result = preparedStatement.executeUpdate();

        if (result > 0) {
            System.out.println("Şifre başarıyla güncellendi.");
        } else {
            System.out.println("Şifre güncellenemedi!");
        }
    }

    public void disconnect() throws SQLException {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
        System.out.println("Veritabanı bağlantısı kapatıldı.");
    }
}