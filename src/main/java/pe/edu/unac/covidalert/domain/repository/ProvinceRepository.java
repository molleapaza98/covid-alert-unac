package pe.edu.unac.covidalert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.unac.covidalert.domain.repository.entity.Province;

import java.util.List;

@Repository
public interface ProvinceRepository  extends JpaRepository<Province, Long> {


}
