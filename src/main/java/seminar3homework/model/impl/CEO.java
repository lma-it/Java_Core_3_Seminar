package seminar3homework.model.impl;

import lombok.Getter;
import seminar3homework.model.Employee;
import seminar3homework.service.EmployeeService;

import java.time.LocalDate;

@Getter
public class CEO extends Employee {

    private static final String positionInCompany = "Chief Executive Officer";

    public CEO(LocalDate birthDate, String name,
                String secondName,
                String lastName,
                String phoneNumber,
                int salary){
        super(birthDate, name, secondName, lastName, phoneNumber, salary);
        this.setPositionInCompany(getPosition());

    }

    public static void upSalary(float percent, Employee employee){
        if (!(employee instanceof CEO)) {
            int upperSalary = employee.getSalary();
            employee.setSalary((int) (upperSalary * percent));
            EmployeeService.fillOutputMap(employee);
        }
    }

    public static String getPosition() {
        return positionInCompany;
    }
}
