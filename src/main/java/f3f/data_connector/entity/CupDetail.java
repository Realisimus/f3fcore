package f3f.data_connector.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "cup_details")
public class CupDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CUP_ID")
    private Cup cup;

    @NotNull
    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "WIND_SPEED_MIN")
    private BigDecimal wind_speed_min;

    @Column(name = "WIND_SPEED_MAX")
    private BigDecimal wind_speed_max;

    @Column(name = "WIND_GUSTS")
    private BigDecimal wind_gusts;

}
