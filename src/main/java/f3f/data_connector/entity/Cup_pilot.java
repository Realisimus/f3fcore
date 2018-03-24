package f3f.data_connector.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUP_PILOTS")
public class Cup_pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUP_ID")
    private String cup_id;

    @Column(name = "PILOT_ID")
    private String pilot_id;

    public Cup_pilot() {
    }

    public Cup_pilot(String cup_id, String pilot_id) {
        this.cup_id = cup_id;
        this.pilot_id = pilot_id;
    }

    public Long getId() {
        return id;
    }

    public String getCup_id() {
        return cup_id;
    }

    public void setCup_id(String cup_id) {
        this.cup_id = cup_id;
    }

    public String getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(String pilot_id) {
        this.pilot_id = pilot_id;
    }
}

