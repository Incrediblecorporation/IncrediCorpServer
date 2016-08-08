package incredicorpserver.beans;

import javax.persistence.*;


@Entity
@Table(name="btd_difficulty")
public class BTD_Difficulty {

    public BTD_Difficulty(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public BTD_Difficulty() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="difficulty_id")
    private int difficultyId;
    @Column(name="difficulty_name")
    private String difficultyName;

    public int getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }
}