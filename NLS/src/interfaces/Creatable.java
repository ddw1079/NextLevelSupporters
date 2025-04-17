package interfaces;

import java.sql.SQLException;

public interface Creatable<T> {

    public void create() throws SQLException;
}
