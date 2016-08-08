package incredicorpserver.beans;

import javax.persistence.*;

@Entity
@Table(name="w_score")
public class W_Score {

    public W_Score(Integer scoreRank, Integer scoreValue, String scoreL, Boolean scoreNew, User user, W_Mode mode, W_Level level) {
        this.scoreRank = scoreRank;
        this.scoreValue = scoreValue;
        this.scoreL = scoreL;
        this.scoreNew = scoreNew;
        this.user = user;
        this.mode = mode;
        this.level = level;
    }

    public W_Score() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="score_id")
    private int scoreId;
    @Column(name="score_rank")
    private Integer scoreRank;
    @Column(name="score_value")
    private Integer scoreValue;
    @Column(name="score_l")
    private String scoreL;
    @Column(name="score_new")
    private Boolean scoreNew;


    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",insertable=true,updatable=true)
    private User user;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="mode_id",insertable=true,updatable=true)
    private W_Mode mode;

    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="level_id",insertable=true,updatable=true)
    private W_Level level;

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

    public String getScoreL() {
        return scoreL;
    }

    public void setScoreL(String scoreL) {
        this.scoreL = scoreL;
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

    public W_Mode getMode() {
        return mode;
    }

    public void setMode(W_Mode mode) {
        this.mode = mode;
    }

    public W_Level getLevel() {
        return level;
    }

    public void setLevel(W_Level level) {
        this.level = level;
    }
}
