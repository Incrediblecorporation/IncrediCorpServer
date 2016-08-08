package incredicorpserver.repository;

import incredicorpserver.beans.W_Level;
import org.springframework.data.repository.CrudRepository;

public interface W_LevelRepository extends CrudRepository<W_Level, Long> {
    W_Level findByLevelId(int id);
}
