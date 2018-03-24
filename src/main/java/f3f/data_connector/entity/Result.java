package f3f.data_connector.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESULTS")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PILOT_ID")
    private Long pilot_id;

    @Column(name = "CUP_ID")
    private Long cup_id;

    @Column(name = "ROUND")
    private Integer round;

    @Column(name = "TIME")
    private Float time;

    @Column(name = "PENALTY")
    private Integer penalty;

    @Column(name = "SCORE")
    private Float score;

    public Result() {}

    public Result(Long pilot_id, Long cup_id, Integer round, Float time, Integer penalty) {
        this.pilot_id = pilot_id;
        this.cup_id = cup_id;
        this.round = round;
        this.time = time;
        this.penalty = penalty;
    }

    public Long getId() {
        return id;
    }

    public Long getPilot_id() {
        return pilot_id;
    }

    public void setPilot_id(Long pilot_id) {
        this.pilot_id = pilot_id;
    }

    public Long getCup_id() {
        return cup_id;
    }

    public void setCup_id(Long cup_id) {
        this.cup_id = cup_id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
