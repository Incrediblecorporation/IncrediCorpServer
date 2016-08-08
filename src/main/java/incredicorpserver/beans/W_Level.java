package incredicorpserver.beans;

/**
 * Created by olivier on 08/12/2015.
 */

import javax.persistence.*;

@Entity
@Table(name="w_level")
public class W_Level {

    public W_Level(String levelName) {
        this.levelName = levelName;
    }

    public W_Level() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
