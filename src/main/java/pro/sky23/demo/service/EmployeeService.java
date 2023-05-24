package pro.sky23.demo.service;

import org.springframework.stereotype.Service;
import pro.sky23.demo.exception.EmployeeAlreadyAddedException;
import pro.sky23.demo.exception.EmployeeNotFoundException;
import pro.sky23.demo.exception.EmployeeStorageIsFullException;
import pro.sky23.demo.exception.IncorrectNameException;
import pro.sky23.demo.model.Employee;


import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {


    private final List<Employee> employees = new ArrayList<>();



    public Employee add(String name,
                        String surname,
                        int department,
                        double salary) {
        Employee employee = new Employee(
                name,
                surname,
                department,
                salary
        );
        if(name.isEmpty()||surname.isEmpty()){
            throw new IncorrectNameException();
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
            employees.add(employee);
            return employee;
    }

    public Employee remove(String name,
                           String surname, int department, double salary) {
        Employee employee = new Employee(name,surname,department,salary);
       if(name.isEmpty()||surname.isEmpty()){
           throw new IncorrectNameException();
       }
       if(!employees.contains(employee)){
           throw new EmployeeNotFoundException();
       }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name,
                         String surname,int department,double salary) {
        Employee employee = new Employee(name,surname,department,salary);
        if(name.isEmpty() || surname.isEmpty()){
            throw new IncorrectNameException();
        }
        if(!employees.contains(employee)){
            throw  new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

}
