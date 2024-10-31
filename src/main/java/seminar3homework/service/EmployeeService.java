package seminar3homework.service;

import lombok.Getter;
import seminar3homework.model.impl.CEO;
import seminar3homework.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeService {
    @Getter
    private static List<Employee> employees = new ArrayList<>();
    @Getter
    private static final HashMap<Integer, String> innerMap = new HashMap<>();
    @Getter
    private static HashMap<Employee, HashMap<Integer, String>> output = new HashMap<>();
    private static final int CHIEF = 0;
    private static final int sizeOfEmployeeFields = 8;

    public static int[] fillMethods(Employee employee){
        int[] methods = new int[sizeOfEmployeeFields];
        methods[0] = employee.getBirthDate().hashCode();
        methods[1] = employee.getName().hashCode() + employee.getName().hashCode();
        methods[2] = employee.getSecondName().hashCode();
        methods[3] = employee.getLastName().hashCode();
        methods[4] = employee.getPhoneNumber().hashCode();
        methods[5] = employee.getPositionInCompany().hashCode();
        methods[6] = Integer.hashCode(employee.getSalary());
        methods[7] = Integer.hashCode(employee.getAge());
        return methods;
    }

    public static int getHashCode(int[] methods, int index){
        return methods[index];
    }

    public static Employee createEmployee(){
        return Employee.builder()
                .birthDate(LocalDate.of(
                        Integer.parseInt(InputService.input(("Введите год:"))),
                        Integer.parseInt(InputService.input(("Введите месяц:"))),
                        Integer.parseInt(InputService.input(("Введите число:")))))
                .name(InputService.input("Введите имя:"))
                .secondName(InputService.input("Введите фамилию:"))
                .lastName(InputService.input("Введите отчество:"))
                .phoneNumber(InputService.input("Введите номер телефона:"))
                .positionInCompany(InputService.input("Введите должность:"))
                .salary(Integer.parseInt(InputService.input(("Введите зарплату:"))))
                .build();

    }
    public static void setEmployees(List<Employee> employees) {
        for(Employee employee : employees){
            fillOutputMap(employee);
        }
        EmployeeService.employees = employees;
    }

    public static void addEmployee(Employee employee){
        employees.add(fillOutputMap(employee));
    }

    public static Employee removeEmployee(Employee employee){
        removeFromOutputMap(employee);
        employees.remove(employee);
        return employee;
    }

    public static CEO getCEO(){
        if (EmployeeService.getEmployees().get(CHIEF).isCEO()){
            return ((CEO) EmployeeService.getEmployees().get(CHIEF));
        }
        return null;
    }

    public static Employee fillOutputMap(Employee employee){
        innerMap.put(employee.getBirthDate().hashCode(), String.format("Дата рождения: %s", employee.getBirthDate()));
        output.put(employee, innerMap);
        innerMap.put(employee.getName().hashCode() + employee.getName().hashCode(), String.format("Имя: %s", employee.getName()));
        output.put(employee, innerMap);
        innerMap.put(employee.getSecondName().hashCode(), String.format("Фамилия: %s", employee.getSecondName()));
        output.put(employee, innerMap);
        innerMap.put(employee.getLastName().hashCode(), String.format("Отчество: %s", employee.getLastName()));
        output.put(employee, innerMap);
        innerMap.put(employee.getPhoneNumber().hashCode(), String.format("Номер телефона: %s", employee.getPhoneNumber()));
        output.put(employee, innerMap);
        innerMap.put(employee.getPositionInCompany().hashCode(), String.format("Должность в компании: %s", employee.getPositionInCompany()));
        output.put(employee, innerMap);
        innerMap.put(Integer.hashCode(employee.getSalary()), String.format("Зарплата: %s руб.", employee.getSalary()));
        output.put(employee, innerMap);
        innerMap.put(Integer.hashCode(employee.getAge()), String.format("Возраст: %s", employee.getAge()));
        output.put(employee, innerMap);
        return employee;
    }

    public static void removeFromOutputMap(Employee employee){
        getOutput().remove(employee);
    }


    public static Employee findByName(String name){
        for (Employee employee : EmployeeService.getEmployees()){
            if (employee.getName().equals(name))
                return employee;
        }
        return null;
    }

    public static Employee findByPhone(String phone){
        for (Employee employee : EmployeeService.getEmployees()){
            if (employee.getPhoneNumber().equals(phone))
                return employee;
        }
        return null;
    }



}
