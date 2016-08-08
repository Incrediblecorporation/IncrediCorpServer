package incredicorpserver.beans;

import javax.persistence.*;

@Entity
@Table(name="btd_mode")
public class BTD_Mode {

    public BTD_Mode(String modeName) {
        this.modeName = modeName;
    }

    public BTD_Mode() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="mode_id")
    private int modeId;
    @Column(name="mode_name")
    private String modeName;

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    public String getModeName() { return modeName; }

    public void setModeName(String modeName) { this.modeName = modeName; }
}
