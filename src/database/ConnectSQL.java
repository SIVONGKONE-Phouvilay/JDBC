package database;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class ConnectSQL {
    private static String DB_URL = "jdbc:mysql://localhost:3306/dienthoai";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    public static void lietKe(Statement stmt) throws SQLException {
        System.out.println("\nHien thi");
        ResultSet rs = stmt.executeQuery("select * from loai");
        // show data
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "  " + rs.getString("thuonghieu")
                    + "  " + rs.getString("price"));
        }
    }
    public static void capNhat(Statement stmt, String id, String thuonghieu, String price) throws SQLException{
        System.out.println("\nDa cap nhat du lieu co id: " + id);
        String update = "UPDATE loai SET price = '"+price+"', thuonghieu = '"+thuonghieu+"' WHERE id = "+id;
        stmt.executeUpdate(update);
        lietKe(stmt);
    }
    public static void them(Statement stmt, String id, String thuonghieu, String price) throws SQLException{
        System.out.println("\nDa them du lieu co id: " + id + ", thuonghieu: " + thuonghieu + ", price: " +price);
        String add = "INSERT INTO loai VALUES ( "+ id +", '"+ thuonghieu +"',"+price+" )";
        stmt.executeUpdate(add);
        lietKe(stmt);
    }
    public static void xoa(Statement stmt, String id) throws SQLException{
        System.out.println("\nDa xoa du lieu co id: " + id);
        String delete = "DELETE FROM loai " + "WHERE id = " + id;
        stmt.executeUpdate(delete);
        lietKe(stmt);
    }
    public static void main(String args[]) {
        try {
            // connnect to database 'dienthoai'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            lietKe(stmt);
            them(stmt,"4","nokia","265000");
            xoa(stmt,"4");
            capNhat(stmt,"5","iphone","12500");
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}