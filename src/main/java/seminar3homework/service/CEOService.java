package seminar3homework.service;

import seminar3homework.model.impl.CEO;

import java.time.LocalDate;

public class CEOService {

    public static CEO createCEO(){
        LocalDate date = LocalDate.of(
                Integer.parseInt(InputService.input("Введите год: ")),
                Integer.parseInt(InputService.input("Введите месяц: ")),
                Integer.parseInt(InputService.input("Введите число")));
        String name = InputService.input("Введите имя: ");
        String secondName = InputService.input("Введите фамилию: ");
        String lastName = InputService.input("Введите отчество: ");
        String phone = InputService.input("Введите телефон: ");
        int salary = Integer.parseInt(
                InputService.input("Введите зарплату (от 600000 руб. до 1000000 руб. Все таки руководитель.) :")
        );
        if(salary < 600000){
            salary *= (600000 / salary);
        }
        return new CEO(date, name, secondName, lastName, phone, salary);
    }
}
