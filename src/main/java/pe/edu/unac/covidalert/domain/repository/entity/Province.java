package pe.edu.unac.covidalert.domain.repository.entity;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_provinces")
public class Province {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(unique = true)
    private String province;

    @Embedded
    private ProvinceInfo provinceInfo;

    @Column(name="cases")
    private Long cases;

    @Column(name="today_cases")
    private Long todayCases;

    @Column(name="deaths")
    private Long deaths;

    @Column(name="today_deaths")
    private Long todayDeaths;

    @Column(name="recovered")
    private Long recovered;

    @Column(name="active")
    private Long active;

    @Column(name = "status")
    private String status;

}
