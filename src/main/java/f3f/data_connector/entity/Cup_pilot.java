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
    private Long cup_id;

    @Column(name = "PILOT_ID")
    private Long pilot_id;

    @Column(name = "SCORE")
    private Float score;

    @Column(name = "RANK")

    private Integer rank;

    public Cup_pilot() {
    }

    public Cup_pilot(Long cup_id, Long pilot_id) {
        this.cup_id = cup_id;
        this.pilot_id = pilot_id;
    }

    public Long getId() {
        return id;
    }

    public Long getCup_id() {
        return cup_id;
    }

    public void setCup_id(Long cup_id) {
        this.cup_id = cup_id;
    }

    public Long getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(Long pilot_id) {
        this.pilot_id = pilot_id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

