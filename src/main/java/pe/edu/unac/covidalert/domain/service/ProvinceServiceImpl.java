package pe.edu.unac.covidalert.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unac.covidalert.domain.repository.ProvinceRepository;
import pe.edu.unac.covidalert.domain.repository.entity.Province;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Province createProvince(Province province) {
        Province provinceDB = getProvinceById(province.getId());
        if (provinceDB!=null){
            return provinceDB;
        }
        province.setStatus("CREATED");
        return provinceRepository.save(province);
    }

    @Override
    public Province updateProvince(Province province) {
        Province provinceDB = getProvinceById(province.getId());
        if (provinceDB == null){
            return null;
        }
        provinceDB.setProvince(province.getProvince());
        provinceDB.setCases(province.getCases());
        provinceDB.setActive(province.getActive());
        provinceDB.setDeaths(province.getDeaths());
        provinceDB.setRecovered(province.getRecovered());
        provinceDB.setTodayCases(province.getTodayCases());
        provinceDB.setTodayDeaths(province.getTodayDeaths());

        return provinceRepository.save(provinceDB);
    }

    @Override
    public Province deleteProvince(Province province) {
        Province provinceDB = getProvinceById(province.getId());
        if (provinceDB == null){
            return null;
        }
        province.setStatus("DELETED");
        return provinceRepository.save(province);
    }

    @Override
    public Province getProvinceById(Long id) {
        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    public Province updateCase(Long id, Long quantity) {
        Province provinceDB = getProvinceById(id);
        long todayCases = provinceDB.getTodayCases()+quantity;
        provinceDB.setTodayCases(todayCases);
        return provinceRepository.save(provinceDB);
    }
}
