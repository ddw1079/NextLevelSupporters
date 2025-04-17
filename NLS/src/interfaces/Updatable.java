package interfaces;

import java.sql.SQLException;

public interface Updatable<T> {

    public void update() throws SQLException;
}
