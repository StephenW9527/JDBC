import java.sql.*;


public class Main {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sql_statement = " SELECT * FROM VIDEO";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stevedb","steve","root");
        if(con != null) {
            System.out.println("Connected to the database successfully!!");
        }else {
            System.out.println("Failed to connect to the database...");
        }
        PreparedStatement ps = con.prepareStatement(sql_statement);

        ResultSet rs = ps.executeQuery();


//      ArrayList<Video> results = new ArrayList<>();
        while (rs.next()) {
//            System.out.println(rs.getString(1));
//            System.out.println(rs.getString(2));
//            System.out.println(rs.getString(3));
              Video video = new Video(Integer.parseInt(rs.getString("videoID")),rs.getString("videoName"),Integer.parseInt(rs.getString("price")));
              System.out.println(video);
        }
    con.close();
    }
}