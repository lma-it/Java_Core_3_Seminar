package seminar3homework.decorator;

import seminar3homework.model.Employee;
import seminar3homework.service.EmployeeService;

import java.util.List;

public class EmployeeDecorator {

    public static void decorateOutput(List<Employee> employees){
        for (Employee employee : employees){
            EmployeeDecorator.decorateOutput(employee);
        }
    }
    public static void decorateOutput(Employee employee){
        System.out.printf(
                "Имя: %s, " +
                "Фамилия: %s, " +
                "Отчество: %s, " +
                "Дата рождения: %s, " +
                "Номер телефона: %s, " +
                "Должность в компании: %s, " +
                "Зарплата: %s руб.\n",
                employee.getName(),
                employee.getSecondName(),
                employee.getLastName(),
                employee.getBirthDate(),
                employee.getPhoneNumber(),
                employee.getPositionInCompany(),
                employee.getSalary()
        );
    }

    public static void decorateOutput(int equal, Employee first, Employee second) {

        if (first != null && second != null){
            if (equal > 0) {
                System.out.printf("%s %s г.р. младше %s %s г.р. на %s %s.\n",
                        first.getName(),
                        first.getBirthDate().getYear(),
                        second.getName(),
                        second.getBirthDate().getYear(),
                        first.getBirthDate().getYear() - second.getBirthDate().getYear(),
                        getRightNounsCasesBy( first.getBirthDate().getYear() - second.getBirthDate().getYear())
                );
            } else if(equal == 0) {
                System.out.printf("%s и %s одного года рождения (%s).\n",
                        first.getName(),
                        second.getName(),
                        first.getBirthDate().getYear()
                );
            }else {
                System.out.printf("%s %s г.р. старше %s %s г.р. на %s %s.\n",
                        first.getName(),
                        first.getBirthDate().getYear(),
                        second.getName(),
                        second.getBirthDate().getYear(),
                        second.getBirthDate().getYear() - first.getBirthDate().getYear(),
                        getRightNounsCasesBy( second.getBirthDate().getYear() - first.getBirthDate().getYear())
                );
            }
        }
    }

    public static void decorateOutput(Employee employee, Object... args){
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < args.length; i++){
            if (i != args.length - 1){
                output
                        .append(EmployeeService.getOutput().get(employee).get(args[i].hashCode()))
                        .append(", ");
            }else
                output.append(EmployeeService.getOutput().get(employee).get(args[i].hashCode()));

        }
        System.out.println(output);
    }


    private static String getRightNounsCasesBy(int dateOfBirth){

        if (dateOfBirth > 20){
            if (dateOfBirth % 10 == 2 || dateOfBirth % 10 == 3 || dateOfBirth % 10 == 4) return "года";
            if (dateOfBirth % 10 == 1) return "год";
        }
        if (dateOfBirth >= 5) return "лет";
        if (dateOfBirth >= 2) return "года";
        if (dateOfBirth == 1) return "год";
        return "лет";
    }


}
