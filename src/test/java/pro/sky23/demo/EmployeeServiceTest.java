package pro.sky23.demo;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky23.demo.exception.*;
import pro.sky23.demo.model.Employee;
import pro.sky23.demo.service.EmployeeService;

import javax.print.attribute.standard.MediaSize;

import static javax.swing.Action.NAME;
import static pro.sky23.demo.Constants.SURNAME;


public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test//добавление
    public void addEmployeeTest() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            employeeService.add("Ivan", "Ivanov", 2, 10_000);
            employeeService.add("Egor", "Vasiliev", 2, 10_000);
        });
    }

    @Test//проверка на повторное добавление
    public void addEmployeeAlreadyTest() {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add("Ivan", "Ivanov", 2, 10_000);
            employeeService.add("Ivan", "Ivanov", 2, 10_000);
        });
    }

    @Test//пустые строки
    public void addEmployeeEmptyTest() {
        Assertions.assertThrows(IncorrectNameException.class, () -> {
            employeeService.add("", "Ivanov", 2, 10_000);
            employeeService.add("Ivan", "", 2, 10_000);
            employeeService.add("", "", 2, 10_000);
        });
    }

    @Test// удаление сотрудника
    public void removeEmployeeTest() {
        Assertions.assertDoesNotThrow(() -> {
            employeeService.add(NAME, SURNAME,2,30_000);
            employeeService.remove(NAME, SURNAME,2,30_000);
        });
    }

    @Test//удаление сотрудника которого нет
    public void removeEmployeeRepetitionTest() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.add(NAME, SURNAME, 2, 10_000);
            employeeService.remove("Egor", "Vasiliev", 2, 10_000);
        });
    }

    @Test//проверка на пустые строки в методе удаления
    public void removeEmployeeEmptyTest() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove(NAME, "Ivanov", 2, 10_000);
            employeeService.remove("Ivan", SURNAME, 2, 10_000);
            employeeService.remove(NAME, SURNAME, 2, 10_000);
        });
    }

    @Test//проверка на поиск сотрудника
    public void findEmployeeTest() {
        Assertions.assertDoesNotThrow(() -> {
            employeeService.add(NAME, SURNAME, 2, 10_000);
            employeeService.find(NAME, SURNAME, 2, 10_000);
        });
    }

    @Test//проверка на поиск сотрудника которого нет в коллекции
    public void findEmployeeNotFindTest() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.add(NAME, SURNAME, 2, 10_000);
            employeeService.find("Egor", "Vasiliev", 2, 10_000);
        });
    }
    @Test//проверка на поиск сотрудника если не введены поля
    public void findEmployeeEmptyTest(){
        Assertions.assertThrows(IncorrectNameException.class, () -> {
            employeeService.find("", "Ivanov", 2, 10_000);
            employeeService.find("Ivan", "", 2, 10_000);
            employeeService.find("", "", 2, 10_000);
        });
    }

}
