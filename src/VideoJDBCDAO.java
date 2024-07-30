import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoJDBCDAO implements videoDAO_Interface {

    public Connection con;


//     {
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stevedb", "steve", "root");
//            if (con != null) {
//                System.out.println("Connected to the database successfully!!");
//            } else {
//                System.out.println("Failed to connect to the database...");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    private static final String GET_ONE_STMT = "select * from video where videoID = ?;";
    private static final String INSERT_STMT = "insert into video values(NULL, ?, ?)";
    private static final String UPDATE_STMT = "update video set videoName=?, price=? where videoID = ?;";
    private static final String DELETE_STMT = "delete from video where videoID = ?;";
    private static final String GET_ALL_STMT = "select * from video order by videoID;";

    public void initialize() throws SQLException, ClassNotFoundException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stevedb", "steve", "root");
        if (con != null) {
            System.out.println("Connected to the database successfully!!");
        } else {
            System.out.println("Failed to connect to the database...");
        }

    }

    public void close() throws SQLException, ClassNotFoundException {
        con.close();
    }

    @Override
    public VideoVO findByPrimaryKey(Integer videoID) {
        VideoVO vVo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String vid = JOptionPane.showInputDialog("Input a Video ID.");
            con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, videoID);
            pstmt.executeQuery();
            while (rs.next()) {
                vVo = new VideoVO();
                vVo.setVideoID(rs.getInt("videoID"));
                vVo.setVideoName(rs.getString("videoName"));
                vVo.setPrice(rs.getInt("price"));
//                int videoID = Integer.parseInt(rs.getString("videoID"));
//                String videoName = rs.getString("videoName");
//                int price = Integer.parseInt(rs.getString("price"));

            }

        } catch (SQLException e) {
            throw new RuntimeException("A Database error occured." + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }

        }
        return vVo;
    }

    @Override
    public int insert(VideoVO videoVO) {
        int updateCount = 0;
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, videoVO.getVideoName());
            pstmt.setInt(2, videoVO.getPrice());

            updateCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("A Database error occured." + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return updateCount;
    }


    @Override
    public int update(VideoVO videoVO) {
        int updateCount = 0;
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setString(1, videoVO.getVideoName());
            pstmt.setInt(2, videoVO.getPrice());

            updateCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("A Database error occured." + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }

            }
        }

        return updateCount;
    }


    @Override
    public int delete(Integer videoID) {
        int updateCount = 0;
        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, videoID);
            updateCount = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("A Database error occured." + e.getMessage());
        } finally {
            if (pstmt != null) {

                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return updateCount;
    }

    @Override
    public List<VideoVO> getAll() {
        List<VideoVO> list = new ArrayList<VideoVO>();
        VideoVO vVo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                vVo = new VideoVO();
                vVo.setVideoID(rs.getInt("videoID"));
                vVo.setVideoName(rs.getString("videoName"));
                vVo.setPrice(rs.getInt("price"));
                list.add(vVo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("A Database error occured." + e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

}


