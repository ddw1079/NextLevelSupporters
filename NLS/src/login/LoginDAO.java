
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
        String sql = "SELECT * FROM USER WHERE id= 받아온거";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            //(2) 종이박스에서 한명씩 꺼내
            int id = rs.getInt("id");
            //id:자바의 변수명		//id:db의 컬럼명
            String name = rs.getString("name");
            String tel = rs.getString("tel");
            Date d = rs.getDate("d");

            LoginVO = new LoginVo(id, name, tel, d);
            //(3) 객체 생성

            tiarray.add(tv);
            //(4) 컬렉션에 넣기
        }
        return tiarray;
    }

    @Override
    public List<BaseVO> read(int target) throws SQLException {
        List<BaseVO> dummy;

        return dummy;

    }
}
