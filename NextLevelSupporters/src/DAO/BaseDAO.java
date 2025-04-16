package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vo.BaseVO;

public class BaseDAO<T extends BaseVO> {

    private Connection con;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public List<T> getAllItems() {
        List<T> returnList = new ArrayList<>();
        String sql = " ";
        // sql 과 pstmt부분만 오버라이딩하면 됨
        // 아닌가? 어떤 VO에 담을지도 오버라이딩해야하나?
        // 생성자의 인자로 줄까?
        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (rs.next()) {
            // 여기 들어가야 할 것은, VO에 있는 필드들을 전부 가져와서 
            // returnList에 하니씩 넣기기

            BaseVO tv = new; // 여기 뒤를 작성해야 하는데.. 
            //(3) 객체 생성

            returnList.add(tv);
            //(4) 컬렉션에 넣기
        }
        return returnList;
    }

}
