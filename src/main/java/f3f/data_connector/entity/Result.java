package f3f.data_connector.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESULTS")
public class Result implements Comparable<Result>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PILOT_ID")
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "CUP_ID")
    private Cup cup;

    @Column(name = "ROUND")
    private Integer round;

    @Column(name = "TIME")
    private Integer time;

    @Column(name = "PENALTY")
    private Integer penalty;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "PERCENTAGES")
    private Integer percentages;

    public Result() {}

    public Result(Pilot pilot, Cup cup, Integer round, Integer time, Integer penalty) {
        this.pilot = pilot;
        this.cup = cup;
        this.round = round;
        this.time = time;
        this.penalty = penalty;
    }

    public Long getId() {
        return id;
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getPercentages() {
        return percentages;
    }

    public void setPercentages(Integer percentages) {
        this.percentages = percentages;
    }

    @Override
    public int compareTo(Result r) {
        return (int) ((r.getScore() - this.getScore()) * 100);
    }

}
