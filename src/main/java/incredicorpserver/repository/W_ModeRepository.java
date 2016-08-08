package incredicorpserver.repository;

import incredicorpserver.beans.W_Mode;
import org.springframework.data.repository.CrudRepository;

public interface W_ModeRepository extends CrudRepository<W_Mode,Long> {
    W_Mode findByModeId(int id);
}
