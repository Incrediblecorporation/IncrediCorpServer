package incredicorpserver.repository;

import incredicorpserver.beans.BTD_Difficulty;
import org.springframework.data.repository.CrudRepository;

public interface BTD_DifficultyRepository extends CrudRepository<BTD_Difficulty, Long> {
    BTD_Difficulty findByDifficultyId(int id);
}
