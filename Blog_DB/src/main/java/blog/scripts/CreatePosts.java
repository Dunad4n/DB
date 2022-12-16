package blog.scripts;

import blog.entities.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;

public class CreatePosts {

    final private static String URL = "jdbc:mysql://localhost:3306/blog";
    final private static String LOGIN = "root";
    final private static String PASSWORD = "Den221032";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM blog.post WHERE post_id >= 1");
        statement.execute("ALTER TABLE blog.post AUTO_INCREMENT = 1");

        Date releaseDate = new java.sql.Date(System.currentTimeMillis() - new Random().nextLong(999999999));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringDate = sdf.format(releaseDate);

//        String query = "INSERT INTO post (title, text, date, user_id) VALUES ('testTitle', 'text text text text text', '" + stringDate + "'" +
//                ", '" + new User(1L, "test_nick", "test_login", "test_password", stringDate) +"')";
//        statement.execute(query);

    }

}
