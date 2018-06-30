package f3f.data_connector.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TOTAL_RESULTS")
public class TotalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUP_ID")
    private Cup cup;

    @ManyToOne
    @JoinColumn(name = "PILOT_ID")
    private Pilot pilot;

    @Column(name = "RANK")
    private Integer rank;

    @Column(name = "SCORE")
    private BigDecimal score;

    @Column(name = "PERCENTS")
    private BigDecimal percents;

    @Column(name = "RAW_SCORE")
    private BigDecimal raw_score;

    @Column(name = "PENALTY")
    private int penalty;

    public TotalResult() {
    }

    public TotalResult(Cup cup, Pilot pilot) {
        this.cup = cup;
        this.pilot = pilot;
    }

    public Long getId() {
        return id;
    }

    public Cup getCup() {
        return cup;
    }

    public void setCup(Cup cup) {
        this.cup = cup;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getPercents() {
        return percents;
    }

    public void setPercents(BigDecimal percents) {
        this.percents = percents;
    }

    public BigDecimal getRaw_score() {
        return raw_score;
    }

    public void setRaw_score(BigDecimal raw_score) {
        this.raw_score = raw_score;
    }
}

