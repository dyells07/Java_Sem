import java.sql.*;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/JavaDatabase";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to the database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name, salary, department FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                String department = rs.getString("department");

                // Display values
                System.out.println("Name: " + name);
                System.out.println("Salary: " + salary);
                System.out.println("Department: " + department);
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
