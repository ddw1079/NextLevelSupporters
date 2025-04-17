package interfaces;

import java.util.List;

public interface Updatable<T> {

    // 얘는 리턴이 필요 없지.
    public List<T> update();

}
