package pe.edu.unac.covidalert.domain.service;

import pe.edu.unac.covidalert.domain.repository.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartments();
    public Department createDepartment(Department department);
    public Department updateDepartment(Department department);
    public String deleteDepartment(Long id);
    public Department getDepartmentById(Long id);
    //public Province getDepartmentByName(String department);
    public Department updateCase(Long id, Long quantity);
}
