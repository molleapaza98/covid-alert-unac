package pe.edu.unac.covidalert.domain.repository.entity;


import javax.persistence.*;

@Entity
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ProvinceInfo getProvinceInfo() {
        return provinceInfo;
    }

    public void setProvinceInfo(ProvinceInfo provinceInfo) {
        this.provinceInfo = provinceInfo;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long todayCases) {
        this.todayCases = todayCases;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", provinceInfo=" + provinceInfo +
                ", cases=" + cases +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                '}';
    }
}
