
import vo.BaseVo;
import java.util.List;
import interfaces.Readable;
import java.sql.SQLException;
import db.ConnectDB;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class LoginDAO implements Readable {

    private Connection con;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    @Override
    public ArrayList<BaseVO> read() throws SQLException {
        ArrayList<BaseVO> tiarray = new ArrayList<>();
       
        
            //(4) 컬렉션에 넣기
        }
        return tiarray;
    }

    @Override
    public List<BaseVO> read(int target) throws SQLException {
        String sql = "SELECT * FROM USER WHERE id= 받아온거";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();


    }
}
