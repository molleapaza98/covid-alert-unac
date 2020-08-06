package pe.edu.unac.covidalert.domain.service;


import pe.edu.unac.covidalert.domain.repository.entity.Province;

import java.util.List;

public interface ProvinceService {

    public List<Province> getAllProvinces();
    public Province createProvince(Province province);
    public Province updateProvince(Province province);
    public Province deleteProvince(Province province);
    public Province getProvinceById(Long id);
    //public Province getProvinceByName(String province);
    public Province updateCase(Long id, Long quantity);


}
