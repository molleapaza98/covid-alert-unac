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
import pe.edu.unac.covidalert.domain.repository.entity.Province;
import pe.edu.unac.covidalert.domain.service.ProvinceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProvinceRest {

    private final ProvinceService service;

    @Autowired
    public ProvinceRest(ProvinceService service) {
        this.service = service;
    }

    //-------------------Retrieve All Province--------------------------------------
    @GetMapping(value = "/provinces")
    public ResponseEntity<List<Province>> listAllProvince(){
        List<Province> provinces = service.getAllProvinces();
        if(provinces.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provinces);
    }

    //-------------------Retrieve Single Province--------------------------------------
    @GetMapping(value = "/provinces/{id}")
    public ResponseEntity<Province> getProvince(@PathVariable Long id){
        Province customer = service.getProvinceById(id);
        if(customer==null){
            log.error("Province with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    //-------------------create a Province---------------------------------------------
    @PostMapping(value = "/provinces")
    public ResponseEntity<Province> createProvince(@RequestBody Province province, BindingResult result) {
        log.info("Creating province : {}", province);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Province provinceDB = service.createProvince(province);
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceDB);
    }

    //-------------------Update a province---------------------------------------------
    @PutMapping(value = "/provinces/{id}")
    public ResponseEntity<Province> updateProvince(@PathVariable Long id, @RequestBody Province province){
        log.info("Updating Customer with id {}", id);
        Province currentProvince = service.getProvinceById(id);
        if (currentProvince == null){
            log.error("Unable to update. province with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        currentProvince= service.updateProvince(province);
        return ResponseEntity.ok(currentProvince);
    }

    @PutMapping(value = "provinces/{id}/todayCases/{quantity}")
    public ResponseEntity<Province> updateTodayCases(@PathVariable Long id, @PathVariable Long quantity){
        log.info("Update cases today : {} with quantity {}",id,quantity);
        Province province = service.getProvinceById(id);
        if (province == null){
            log.error("Unable to update today cases. Province with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        province = service.updateCase(id,quantity);
        return ResponseEntity.status(HttpStatus.OK).body(province);

    }

    //-------------------Delete a Province---------------------------------------------
    @DeleteMapping(value = "provinces/{id}")
    public ResponseEntity<Province> deleteProvince(@PathVariable Long id){
        log.info("Fetching & Deleting Province with id {}", id);
        Province province = service.getProvinceById(id);
        if (province == null){
            log.error("Unable to delete. Province with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        province = service.deleteProvince(province);
        return ResponseEntity.ok(province);
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
