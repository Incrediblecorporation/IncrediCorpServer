package incredicorpserver.repository;

import incredicorpserver.beans.BTD_Difficulty;
import incredicorpserver.beans.BTD_Level;
import incredicorpserver.beans.BTD_Mode;
import incredicorpserver.beans.BTD_Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BTD_ScoreRepository extends CrudRepository<BTD_Score,Long> {
    @Override
    List<BTD_Score> findAll();

    List<BTD_Score> findByLevelAndModeAndDifficulty(BTD_Level level, BTD_Mode mode, BTD_Difficulty difficulty);
}