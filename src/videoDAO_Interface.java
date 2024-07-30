import java.sql.SQLException;
import java.util.List;

public interface videoDAO_Interface {
    public int insert(VideoVO videoVO);
    public int update(VideoVO videoVO);
    public int delete(Integer videoID);
    public VideoVO findByPrimaryKey(Integer videoID);
    public List<VideoVO> getAll();
}
