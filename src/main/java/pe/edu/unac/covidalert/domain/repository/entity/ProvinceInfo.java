package pe.edu.unac.covidalert.domain.repository.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.Embeddable;

@Embeddable
public class ProvinceInfo {

    @JsonAlias("_id")
    private Long provinceId;

    @JsonAlias("lat")
    private double latitude;

    @JsonAlias("long")
    private double longitude;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "ProvinceInfo{" +
                "provinceId=" + provinceId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
