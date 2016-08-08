package incredicorpserver.repository;

import incredicorpserver.beans.BTD_Mode;
import org.springframework.data.repository.CrudRepository;

public interface BTD_ModeRepository extends CrudRepository<BTD_Mode,Long> {
    BTD_Mode findByModeId(int id);
    BTD_Mode findByModeName(String name);
}