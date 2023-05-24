package pro.sky23.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky23.demo.model.Employee;
import pro.sky23.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String name,
                        @RequestParam("lastName") String surname,
                        @RequestParam int department,
                        @RequestParam double salary) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String name,
                           @RequestParam("lastName") String surname,@RequestParam("department") int department,@RequestParam("salary") double salary){
        return employeeService.remove(name, surname,department,salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String name,
                         @RequestParam("lastName") String surname,@RequestParam("department") int department,@RequestParam("salary") double salary) {
        return employeeService.find(name, surname,department,salary);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

}
