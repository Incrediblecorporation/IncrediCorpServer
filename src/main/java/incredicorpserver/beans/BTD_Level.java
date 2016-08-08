package incredicorpserver.beans;

import javax.persistence.*;


@Entity
@Table(name="btd_level")
public class BTD_Level {

    public BTD_Level(String levelName) {
        this.levelName = levelName;
    }

    public BTD_Level() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="level_id")
    private int levelId;
    @Column(name="level_name")
    private String levelName;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
