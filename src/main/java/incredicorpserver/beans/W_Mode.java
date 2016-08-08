package incredicorpserver.beans;

/**
 * Created by olivier on 08/12/2015.
 */

import javax.persistence.*;

@Entity
@Table(name="w_mode")
public class W_Mode {

    public W_Mode(String modeName) {
        this.modeName = modeName;
    }

    public W_Mode() {
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