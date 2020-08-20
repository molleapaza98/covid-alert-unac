package pe.edu.unac.covidalert.domain.repository.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class ProvinceInfo {

    @JsonAlias("_id")
    private Long provinceId;

    @JsonAlias("lat")
    private double latitude;

    @JsonAlias("long")
    private double longitude;


}
