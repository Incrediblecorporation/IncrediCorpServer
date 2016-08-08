package incredicorpserver.repository;

import incredicorpserver.beans.BTD_Level;
import org.springframework.data.repository.CrudRepository;

public interface BTD_LevelRepository extends CrudRepository<BTD_Level, Long> {
    BTD_Level findByLevelId(int id);
}
