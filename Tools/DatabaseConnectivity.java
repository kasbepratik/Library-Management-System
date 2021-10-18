package Tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MKhairy
 */
public class DatabaseConnectivity {

    Connection con;
    Statement st;
    public ResultSet rs;

    public DatabaseConnectivity() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_database", "root", "");

            st = con.createStatement();

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage().toString());
        }

    }

    public ResultSet executeQuery(String Query) {
        try {
            return st.executeQuery(Query);
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean execute(String query) {
        try {
            st.execute(query);
            return true;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
            return false;
        }

    }

    public String[][] Select(String Query) {
        try {
            rs = st.executeQuery(Query);
            rs.last();
            int row = rs.getRow();
            rs.beforeFirst();

            int column = rs.getMetaData().getColumnCount();

            String[][] data = new String[row][column];

            int x = 0;
            while (rs.next()) {

                for (int i = 0; i < column; i++) {

                    data[x][i] = rs.getObject(i + 1) + "";
                }
                x++;
            }
            return data;

        } catch (Exception e) {
            return null;
        }

    }

}
