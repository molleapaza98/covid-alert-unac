package pe.edu.unac.covidalert.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.unac.covidalert.domain.repository.entity.Department;
import pe.edu.unac.covidalert.domain.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DepartamentRest {

    private final DepartmentService service;

    @Autowired
    public DepartamentRest(DepartmentService service) {
        this.service = service;
    }

    //-------------------Retrieve All Province--------------------------------------
    @GetMapping(value = "/departments")
    public ResponseEntity<List<Department>> listAllDepartments(){
        List<Department> departments = service.getAllDepartments();
        if(departments.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departments);
    }

    //-------------------Retrieve Single Department--------------------------------------
    @GetMapping(value = "/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id){
        Department department = service.getDepartmentById(id);
        if(department==null){
            log.error("Department with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    //-------------------create a Department---------------------------------------------
    @PostMapping(value = "/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department, BindingResult result) {
        log.info("Creating department : {}", department);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Department departmetDB = service.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmetDB);
    }

    //-------------------Update a department---------------------------------------------
    @PutMapping(value = "/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department){
        log.info("Updating Department with id {}", id);
        Department currentDepartment = service.getDepartmentById(id);
        if (currentDepartment == null){
            log.error("Unable to update. department with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        currentDepartment= service.updateDepartment(department);
        return ResponseEntity.ok(currentDepartment);
    }

    @PutMapping(value = "/departments/{id}/cases/{quantity}")
    public ResponseEntity<Department> updateCases(@PathVariable Long id, @PathVariable Long quantity){
        log.info("Update cases today : {} with quantity {}",id,quantity);
        Department department = service.getDepartmentById(id);
        if (department == null){
            log.error("Unable to update today cases. Department with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        department = service.updateCase(id,quantity);
        return ResponseEntity.status(HttpStatus.OK).body(department);

    }

    //-------------------Delete a Department---------------------------------------------
    @DeleteMapping(value = "departments/{id}")
    public String deleteDepartment(@PathVariable Long id){
        log.info("Fetching & Deleting Department with id {}", id);
        Department department = service.getDepartmentById(id);
        if (department == null){
            log.error("Unable to delete. Department with id {} not found.", id);
            return "Error! department is null";
        }
        return service.deleteDepartment(id);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
