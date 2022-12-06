package com.onwumere.bright.employeemanagementsystem.controller;
/*
 * @Project Name: employee-management-system
 * @Author: Okechukwu Bright Onwumere
 * @Created: 04/12/2022
 */


import com.onwumere.bright.employeemanagementsystem.exception.StorageException;
import com.onwumere.bright.employeemanagementsystem.model.Employee;
import com.onwumere.bright.employeemanagementsystem.repository.EmployeeRepository;
import com.onwumere.bright.employeemanagementsystem.repository.FileStorageRepository;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

@Controller
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final FileStorageRepository fileStorageRepository;

    public EmployeeController(EmployeeRepository employeeRepository, FileStorageRepository fileStorageRepository) {
        this.employeeRepository = employeeRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    @ModelAttribute("employees")   //This method would run before any handler method
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @ModelAttribute("employee")
    public Employee getEmployee(){
        return new Employee();
    }

    @GetMapping
    public String showEmployees(){
        //List<Employee> employees = RandomEmployee.getRandomEmployees(8);
        //model.addAttribute("employees", employees);
        return "employee";   //this means show a view called
    }

    @GetMapping("/add")
    public String displayEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @GetMapping("/view/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("viewEmployee", employeeRepository.findById(id).get());
        return "view-employee";
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource){
        String disposition = """
                 attachment; filename="%s"
                """;
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(disposition, resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @PostMapping("/save")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors, @RequestParam("photoFileName") MultipartFile photoFile) throws IOException {
        log.info(employee);
        log.info("Photo File Name: "  + photoFile.getOriginalFilename());
        log.info("Photo File Size: " + photoFile.getSize());
        log.info("Errors: " + errors);
        if (!errors.hasErrors()){
            try {
                fileStorageRepository.save(photoFile.getOriginalFilename(), photoFile.getInputStream());
                employeeRepository.save(employee);
                return "redirect:/employee"; //if everything is okay, go back to the home page
            } catch (StorageException e) {
                model.addAttribute("errorMsg", "System is unable to accept images at this time. Please try again later");
                return "add-employee";
            }
        }
        return "add-employee";  //stay on the add-employee page if there are errors
    }

    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "edit-employee";
    }

    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, @RequestParam("photoFileName") MultipartFile photoFile) throws IOException {
        //get employee from database by id
        Employee existingEmployee = employeeRepository.findById(id).get();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        if(!employee.getPhotoFileName().isEmpty()){
            existingEmployee.setPhotoFileName(employee.getPhotoFileName());
            fileStorageRepository.save(photoFile.getOriginalFilename(), photoFile.getInputStream());
        }
        employeeRepository.save(existingEmployee);
        return "redirect:/employee";
    }


}
