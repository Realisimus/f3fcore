package f3f.data_connector.entity;

import javax.persistence.*;

@Entity
@Table(name = "TOTAL_RESULTS")
public class TotalResult implements Comparable<TotalResult>{

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
    private Integer score;

    @Column(name = "PERCENTS")
    private Integer percents;

    @Column(name = "RAW_SCORE")
    private Integer raw_score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPercents() {
        return percents;
    }

    public void setPercents(Integer percents) {
        this.percents = percents;
    }

    public Integer getRaw_score() {
        return raw_score;
    }

    public void setRaw_score(Integer raw_score) {
        this.raw_score = raw_score;
    }

    @Override
    public int compareTo(TotalResult o) {
        return o.getScore() - this.getScore();
    }
}

