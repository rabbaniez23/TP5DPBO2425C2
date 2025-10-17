import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection;
    private Statement statement;

    // -------------------------------
    // Konstruktor: Membuka koneksi ke database MySQL
    // -------------------------------
    public Database() {
        try {
            // Ganti "db_product" sesuai nama database kamu
            String url = "jdbc:mysql://localhost:3306/db_product";
            String user = "root";
            String pass = "";

            // Membuka koneksi
            connection = DriverManager.getConnection(url, user, pass);

            // Membuat objek Statement untuk menjalankan query
            statement = connection.createStatement();

            System.out.println("Koneksi ke database berhasil!");
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke database: " + e.getMessage());
        }
    }

    // -------------------------------
    // Menjalankan query SELECT dan mengembalikan hasilnya (ResultSet)
    // -------------------------------
    public ResultSet selectQuery(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Kesalahan saat SELECT: " + e.getMessage());
        }
        return resultSet;
    }

    // -------------------------------
    // Menjalankan query INSERT, UPDATE, atau DELETE
    // -------------------------------
    public int insertUpdateDeleteQuery(String sql) {
        int result = 0;
        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Kesalahan saat INSERT/UPDATE/DELETE: " + e.getMessage());
        }
        return result;
    }

    // -------------------------------
    // Menutup koneksi database
    // -------------------------------
    public void closeConnection() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            System.out.println("Koneksi database ditutup.");
        } catch (SQLException e) {
            System.err.println("Kesalahan saat menutup koneksi: " + e.getMessage());
        }
    }

    // -------------------------------
    // Getter (jika diperlukan)
    // -------------------------------
    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
}
