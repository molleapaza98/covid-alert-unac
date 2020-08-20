package pe.edu.unac.covidalert.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unac.covidalert.domain.repository.DepartmentRepository;
import pe.edu.unac.covidalert.domain.repository.entity.Department;
import pe.edu.unac.covidalert.domain.repository.entity.Province;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        Department departmentDB = getDepartmentById(department.getId());
        if (departmentDB!=null){
            return departmentDB;
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        Department departmentDB = getDepartmentById(department.getId());
        if (departmentDB==null){
            return null;
        }
        departmentDB.setDepartment(department.getDepartment());
        departmentDB.setActive(department.getActive());
        departmentDB.setDeaths(department.getDeaths());
        departmentDB.setRecovered(department.getRecovered());
        departmentDB.setTodayCases(department.getTodayCases());
        departmentDB.setTodayDeaths(department.getTodayDeaths());

        return departmentRepository.save(departmentDB);
    }

    @Override
    public String deleteDepartment(Long id) {
        Department departmentDB = getDepartmentById(id);
        if (departmentDB != null){
            departmentRepository.deleteById(id);
            return "Department eliminado correctament.";
        }
        return "Error! department is null";
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public Department updateCase(Long id, Long quantity) {
        Department departmentDB = getDepartmentById(id);
        long actives = departmentDB.getActive()+quantity;
        departmentDB.setActive(actives);
        return departmentRepository.save(departmentDB);
    }
}
