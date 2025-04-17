package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Readable<T> {

    public List<T> read() throws SQLException;
    public List<T> read(int target) throws SQLException; 
}
