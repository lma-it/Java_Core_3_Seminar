package seminar3homework.model;

import lombok.*;
import seminar3homework.comparator.EmployeeComparator;
import seminar3homework.model.impl.CEO;
import seminar3homework.service.EmployeeService;
import seminar3homework.util.Methods;

import java.time.LocalDate;
import java.util.Objects;


@Builder
@Setter
public class Employee implements EmployeeComparator, Methods {

    private LocalDate birthDate;
    private String name;
    private String secondName;
    private String lastName;
    private String phoneNumber;
    private String positionInCompany;
    private int salary;


    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            System.out.println("Объект равен null!");
            return false;
        }

        Employee other = (Employee) obj;
        return Objects.equals(hashCode(), other.hashCode());
    }

    public Employee(Employee employee){
        this.birthDate = employee.getBirthDate();
        this.name = employee.getName();
        this.secondName = employee.getSecondName();
        this.lastName = employee.getLastName();
        this.phoneNumber = employee.getPhoneNumber();
        this.positionInCompany = employee.getPositionInCompany();
        this.salary = employee.getSalary();
    }

    public Employee(LocalDate birthDay,
                    String name,
                    String secondName,
                    String lastName,
                    String phoneNumber,
                    String position,
                    int salary){
        this.birthDate = birthDay;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.positionInCompany = position;
        this.salary = salary;
    }

    public Employee(LocalDate birthDay,
                    String name,
                    String secondName,
                    String lastName,
                    String phoneNumber,
                    int salary){
        this.birthDate = birthDay;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


    @Override
    public int hashCode() {

        return Objects.hash(
                getBirthDate(),
                getName(),
                getSecondName(),
                getLastName(),
                getSalary(),
                getPositionInCompany(),
                getPhoneNumber()
        );
    }

    @Override
    public int compareTo(Employee o) {
        if (o != null)
            return Integer.compare(this.getBirthDate().getYear(), o.getBirthDate().getYear());
        return 2;
    }

    public boolean isMe(Employee employee){
        return this.equals(employee);
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSecondName() {
        return this.secondName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String getPositionInCompany() {
        return this.positionInCompany;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getAge(){
        return LocalDate.now().getYear() - this.birthDate.getYear();
    }

    @Override
    public String toString() {
        return String.format(
        "Имя: %s, Фамилия: %s, Отчество: %s, Возраст: %s, Номер телефона: %s, Должность в компании: %s. Зарплата: %s руб.\n",
                this.getName(),
                this.getSecondName(),
                this.getLastName(),
                LocalDate.now().getYear() - this.getBirthDate().getYear(),
                this.getPhoneNumber(),
                this.getPositionInCompany(),
                this.getSalary()
        );
    }

    public boolean isCEO(){
        return this.getClass().getName().equals(CEO.class.getName());
    }

    public void updateOutputMap(){
        for(Employee employee : EmployeeService.getEmployees()){
            EmployeeService.fillOutputMap(employee);
        }
    }
}
