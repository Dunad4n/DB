package blog.scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class CreateUsers {

    final private static String URL = "jdbc:mysql://localhost:3306/blog";
    final private static String LOGIN = "root";
    final private static String PASSWORD = "Den221032";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM blog.user WHERE user_id >= 1");
        statement.execute("ALTER TABLE blog.user AUTO_INCREMENT = 1");

        Random rnd = new Random();

        String[] nicknames = getNicknames();
        String[] logins = getLogins(rnd);
        String[] passwords = getPasswords(rnd);

        for (int i = 0; i < 1000; i++) {
            Date releaseDate = new java.sql.Date(System.currentTimeMillis() - rnd.nextLong(999999999));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringDate = sdf.format(releaseDate);

            String query = "INSERT INTO user (nickname, login, password, date) VALUES ('" + nicknames[i] + "', '" + logins[i] + "', '" + passwords[i] + "', '" + stringDate + "')";
            statement.execute(query);
        }
    }

    public static String[] getNicknames() throws FileNotFoundException {
        File file = new File("D:\\JavaTasks\\BD\\BD_test\\src\\main\\resources\\nicknames.txt");

        String[] nicknames = new String[1000];

        Scanner scanner = new Scanner(file);

        int index = 0;
        while(scanner.hasNextLine()) {
            nicknames[index] = scanner.nextLine();
            index++;
        }
        return nicknames;
    }

    public static String[] getLogins(Random rnd) {
        String literals = "qwertyuiopasdfghjklzxcvbnm12345678910#%";
        String[] logins = new String[1000];

        for (int i = 0; i < logins.length; i++) {
            int n = rnd.nextInt(literals.length());
            logins[i] = String.valueOf(literals.charAt(n));
            for (int j = 0; j < 10; j++) {
                n = rnd.nextInt(literals.length());
                logins[i] += literals.charAt(n);
            }
            logins[i] += "@gmail.com";
        }

        return logins;
    }

    public static String[] getPasswords(Random rnd) {
        String literals = "qwertyuiopasdfghjklzxcvbnm12345678910_#%.";
        String[] passwords = new String[1000];

        for (int i = 0; i < passwords.length; i++) {
            int n = rnd.nextInt(literals.length());
            passwords[i] = String.valueOf(literals.charAt(n));
            for (int j = 0; j < 10; j++) {
                n = rnd.nextInt(literals.length());
                passwords[i] += literals.charAt(n);
            }
        }

        return passwords;
    }

}
