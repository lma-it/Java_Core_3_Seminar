package seminar3homework.service;

import lombok.Getter;
import seminar3homework.model.Employee;
import seminar3homework.model.impl.CEO;

import java.util.List;

@Getter
public class CompanyService {

    public static List<Employee> fillEmployeesFromDBWithCEO(int boundsOfEmployees){

        for (int i = 0; i < boundsOfEmployees; i++){
            if(i == 0)
                EmployeeService.addEmployee(new CEO(
                        EmployeeDBService.getRandomBirthDate(),
                        EmployeeDBService.getRandomName(),
                        EmployeeDBService.getRandomSecondName(),
                        EmployeeDBService.getRandomLastName(),
                        EmployeeDBService.getRandomPhoneNumber(),
                        EmployeeDBService.getRandomCEOSalary()));
            else
                EmployeeService.addEmployee(CompanyService.buildEmployee());
        }
        return EmployeeService.getEmployees();
    }

    public static List<Employee> fillEmployeesFromDBWithoutCEO(int boundsOfEmployees){

        for (int i = 0; i < boundsOfEmployees; i++){
            EmployeeService.addEmployee(CompanyService.buildEmployee());
        }
        return EmployeeService.getEmployees();
    }

    public static Employee buildEmployee(){

        return Employee
                .builder()
                .birthDate(EmployeeDBService.getRandomBirthDate())
                .name(EmployeeDBService.getRandomName())
                .lastName(EmployeeDBService.getRandomLastName())
                .secondName(EmployeeDBService.getRandomSecondName())
                .salary(EmployeeDBService.getRandomSalary())
                .positionInCompany(EmployeeDBService.getRandomPosition())
                .phoneNumber(EmployeeDBService.getRandomPhoneNumber())
                .build();
    }

    public static CEO buildCEO(){
        return new CEO(EmployeeDBService.getRandomBirthDate(),
                EmployeeDBService.getRandomName(),
                EmployeeDBService.getRandomSecondName(),
                EmployeeDBService.getRandomLastName(),
                EmployeeDBService.getRandomPhoneNumber(),
                EmployeeDBService.getRandomCEOSalary()
        );
    }

}
