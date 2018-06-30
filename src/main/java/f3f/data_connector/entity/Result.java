package f3f.data_connector.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RESULTS")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PILOT_ID")
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "CUP_ID")
    private Cup cup;

    @Column(name = "ROUND")
    private Integer round;

    @Column(name = "TIME")
    private BigDecimal time;

    @Column(name = "PENALTY")
    private Integer penalty;

    @Column(name = "SCORE")
    private BigDecimal score;

    @Column(name = "PERCENTAGES")
    private BigDecimal percentages;

    public Result() {}

    public Result(Integer id, Pilot pilot, Cup cup, Integer round, BigDecimal time, Integer penalty) {
        this.id = id;
        this.pilot = pilot;
        this.cup = cup;
        this.round = round;
        this.time = time;
        this.penalty = penalty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getPercentages() {
        return percentages;
    }

    public void setPercentages(BigDecimal percentages) {
        this.percentages = percentages;
    }
}
