package pe.edu.unac.covidalert.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.unac.covidalert.domain.repository.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
