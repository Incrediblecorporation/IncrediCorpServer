package incredicorpserver.beans;

import javax.persistence.*;

@Entity
@Table(name="btd_score")
public class BTD_Score {

    public BTD_Score(Integer scoreRank, Integer scoreValue, Boolean scoreNew, User user, BTD_Mode mode, BTD_Difficulty difficulty, BTD_Level level, Integer wave) {
        this.scoreRank = scoreRank;
        this.scoreValue = scoreValue;
        this.scoreNew = scoreNew;
        this.user = user;
        this.mode = mode;
        this.difficulty = difficulty;
        this.level = level;
        this.wave = wave;
    }

    public BTD_Score() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="score_id")
    private int scoreId;
    @Column(name="score_rank")
    private Integer scoreRank;
    @Column(name="score_value")
    private Integer scoreValue;
    @Column(name="score_new")
    private Boolean scoreNew;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",insertable=true,updatable=true)
    private User user;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="mode_id",insertable=true,updatable=true)
    private BTD_Mode mode;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="difficulty_id",insertable=true,updatable=true)
    private BTD_Difficulty difficulty;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="level_id",insertable=true,updatable=true)
    private BTD_Level level;

    @Column(name="wave")
    private Integer wave;

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(Integer scoreRank) {
        this.scoreRank = scoreRank;
    }

    public Integer getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Boolean getScoreNew() {
        return scoreNew;
    }

    public void setScoreNew(Boolean scoreNew) {
        this.scoreNew = scoreNew;
    }

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
    }

    public BTD_Mode getMode() {
        return mode;
    }

    public void setMode(BTD_Mode mode) {
        this.mode = mode;
    }

    public BTD_Level getLevel() {
        return level;
    }

    public void setLevel(BTD_Level level) {
        this.level = level;
    }

    public BTD_Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BTD_Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getWave() {
        return wave;
    }

    public void setWave(Integer wave) {
        this.wave = wave;
    }
}
