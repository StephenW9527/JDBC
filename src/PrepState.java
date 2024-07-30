import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;


public  class PrepState {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        VideoJDBCDAO dao = new VideoJDBCDAO();
        dao.initialize();

        List<VideoVO> list = dao.getAll();
        for (VideoVO vo : list) {
            System.out.println(vo.getVideoID() +" " + vo.getVideoName() + " " + vo.getPrice());

        }
        dao.close();
    }
}
