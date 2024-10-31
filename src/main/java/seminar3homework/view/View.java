package seminar3homework.view;

import seminar3homework.decorator.EmployeeDecorator;
import seminar3homework.model.Employee;
import seminar3homework.service.*;
import seminar3homework.util.AboutCompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View {

    private static boolean isTip = false;
    private static String tipOne;
    private static String tipTwo;
    private static String tipThree;




    public static void start(){

        boolean isCEO = false;
        System.out.println("Добро пожаловать в нашу компанию!");

        while (true) {

            isTipOn();
            mainMenu();
            switch (Integer.parseInt(InputService.input("Ожидаем команду..."))){
                // начало блока создания руководителя
                case 1 -> {

                    if (!isCEO){
                        createEmployeeMenu("руководителя");
                        switch (Integer.parseInt(InputService.input("Ожидаем команду..."))){
                            // начало блока создания руководителя в ручную
                            case 1 -> {
                                if(!EmployeeService.getEmployees().isEmpty()){
                                    Employee temp = EmployeeService.getEmployees().get(0);
                                    EmployeeService.getEmployees().set(0, CEOService.createCEO()).updateOutputMap();
                                    EmployeeService.getEmployees().add(temp);
                                }else{
                                    EmployeeService.addEmployee(CEOService.createCEO());
                                }
                                isCEO = true;

                            }
                            // конец блока создания руководителя в ручную

                            // начало блока создания руководителя автоматически из БД
                            case 2 -> {
                                if(!EmployeeService.getEmployees().isEmpty()){
                                    Employee temp = EmployeeService.getEmployees().get(0);
                                    EmployeeService.getEmployees().set(0, CompanyService.buildCEO()).updateOutputMap();
                                    EmployeeService.getEmployees().add(temp);
                                }else {
                                    EmployeeService.addEmployee(CompanyService.buildCEO());
                                    Objects.requireNonNull(EmployeeService.getCEO()).updateOutputMap();
                                }
                                isCEO = true;
                            }
                            // конец блока создания руководителя автоматически из БД

                            // Выход из меню создания руководителя
                            case 0 -> {
                            }
                        }

                    // начало блока редактирования руководителя
                    }else{
                        editCEOMenu();
                        switch (Integer.parseInt(InputService.input("Введите команду..."))){
                            case 1 -> {
                                editAutoOrByHands();
                                switch (Integer.parseInt(InputService.input("Введите команду..."))){
                                    case 1 -> EmployeeService.addEmployee(CEOService.createCEO());
                                    case 2 -> EmployeeService.addEmployee(CompanyService.buildCEO());
                                    case 0 -> {
                                    }
                                }
                            }
                            case 2 -> {
                                editAutoOrByHands();
                                switch (Integer.parseInt(InputService.input("Введите команду"))){
                                    case 1 -> {
                                        EmployeeService.getEmployees().get(0).setPhoneNumber(InputService.input("Введите новый номер телефона"));
                                        EmployeeService.getEmployees().get(0).updateOutputMap();
                                    }
                                    case 2 -> {
                                        EmployeeService.getEmployees().get(0).setPhoneNumber(EmployeeDBService.getRandomPhoneNumber());
                                        EmployeeService.getEmployees().get(0).updateOutputMap();
                                    }
                                    case 0 -> {
                                    }
                                }

                            }
                            case 3 -> {
                                EmployeeService.getEmployees().get(0).setSalary(Integer.parseInt(InputService.input("Введите новую зарплату.")));
                                EmployeeService.getEmployees().get(0).updateOutputMap();
                            }
                            case 4 -> {
                            }
                        }
                    }
                    // конец блока редактирования руководителя

                }
                // конец блока создания руководителя

                // начало блока создания сотрудника
                case 2 -> {
                    createEmployeeMenu("сотрудника");
                    switch (Integer.parseInt(InputService.input("Ожидаем число..."))){

                        // начало блока создания сотрудника в ручную
                        case 1 -> EmployeeService
                                .getEmployees()
                                .add(EmployeeService
                                        .fillOutputMap(
                                                EmployeeService.createEmployee()
                                        )
                                );
                        // конец блока создания сотрудника в ручную

                        // начало блока создания сотрудника автоматически из БД
                        case 2 -> EmployeeService
                                .getEmployees()
                                .add(EmployeeService
                                        .fillOutputMap(
                                                CompanyService.buildEmployee()
                                        )
                                );
                        // конец блока создания сотрудника автоматически из БД

                        // начало блока создания группы сотрудников из БД
                        case 3 -> {
                            if(EmployeeService.getEmployees().isEmpty()){
                                EmployeeService.setEmployees(
                                        CompanyService.fillEmployeesFromDBWithoutCEO(
                                                Integer.parseInt(
                                                        InputService.input(
                                                                "Введите количество сотрудников: ")
                                                )
                                        )
                                );
                            }else {
                                CompanyService.fillEmployeesFromDBWithoutCEO(Integer.parseInt(
                                        InputService.input(
                                                "Введите число сотрудников: ")));
                            }
                        }
                        // конец блока создания группы сотрудников из БД
                        // начало блока выхода
                        case 0 -> {
                        }
                        // конец блока выхода
                    }
                }
                // конец блока создания сотрудника

                // начало блока просмотра руководителя
                case 3 -> {
                    if(!EmployeeService.getEmployees().isEmpty() && EmployeeService.getEmployees().get(0).isCEO())
                        EmployeeDecorator.decorateOutput(EmployeeService.getEmployees().get(0));
                    else
                        System.out.println("Временно руководителя в компании нет, вышел по делам.");
                }
                // конец блока просмотра руководителя

                // начало блока меню сотрудников
                case 4 -> {
                    employeeMenu();
                    switch (Integer.parseInt(InputService.input("Введите команду..."))){
                        case 1 -> EmployeeDecorator.decorateOutput(EmployeeService.getEmployees());
                        case 2 -> {
                            Employee employee = EmployeeService.findByName(InputService.input("Введите имя: "));
                            if(employee == null)
                                System.out.println("Такого сотрудника в компании нет.");
                            else
                                EmployeeDecorator.decorateOutput(employee);
                        }
                        case 3 -> {
                            Employee employee = EmployeeService.findByPhone(InputService.input("Введите телефон: "));
                            if(employee == null)
                                System.out.println("Сотрудника с таким номером телефона в компании нет.");
                            else
                                EmployeeDecorator.decorateOutput(employee);
                        }
                        case 4 -> {
                            for(Employee employee : EmployeeService.getEmployees()){
                                EmployeeDecorator.decorateOutput(employee,
                                                EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 1),
                                        EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 2),
                                        EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 3));
                            }
                        }
                        case 5 -> {
                            for(Employee employee : EmployeeService.getEmployees()){
                                EmployeeDecorator.decorateOutput(employee,
                                        EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 1),
                                        EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 4));
                            }
                        }
                        case 6 -> {
                            employeeInfoMenu();
                            String[] methods = InputService.input("Введите номера что нужно отобразить: ").split("");
                            if(Integer.parseInt(methods[0]) == 0){
                                break;
                            }
                            int[] indexes = new int[methods.length];
                            Object[] args = new Object[indexes.length];
                            for(Employee employee : EmployeeService.getEmployees()){
                                for(int i = 0; i < indexes.length; i++){
                                    indexes[i] = Integer.parseInt(methods[i]) - 1;
                                    args[i] = EmployeeService.getHashCode(EmployeeService.fillMethods(employee), indexes[i]);
                                }
                                EmployeeDecorator.decorateOutput(employee, args);
                            }
                        }
                        case 7 -> {
                            if (!(EmployeeService.getEmployees().size() == 1)){
                                var equalEmployees = new ArrayList<>(List.copyOf(EmployeeService.getEmployees()));
                                for(int i = 0; i < EmployeeService.getEmployees().size(); i ++){
                                    for (Employee employee : equalEmployees){
                                        if(equalEmployees.get(i) != null && employee != null){
                                            if (!equalEmployees.get(i).isMe(employee)){
                                                EmployeeDecorator.decorateOutput(equalEmployees.get(i).compareTo(employee), equalEmployees.get(i), employee);
                                            }
                                        }
                                    }
                                    equalEmployees.set(i, null);
                                }
                            }else{
                                System.out.println("В компании только один сотрудник, сравнивать не с кем.");
                            }

                        }
                        case 0 ->{
                        }
                    }

                }
                // конец блока меню сотрудников

                // начало блока с повышением в должности
                case 5 -> {
                    if (EmployeeService.getEmployees().get(0).isCEO()){
                        employeePositionUpMenu();
                        switch (Integer.parseInt(InputService.input("Введите команду.."))) {
                            case 1 -> {
                                for (Employee employee : EmployeeService.getEmployees()) {
                                    if (!employee.isCEO())
                                        EmployeeDecorator.decorateOutput(employee,
                                                EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 1),
                                                EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 5));
                                }
                                Employee temp = EmployeeService.findByName(InputService.input("Введите имя сотрудника, у которого хотите поменять должность."));
                                if (temp != null) {
                                    EmployeeDecorator.decorateOutput(temp);
                                    temp.setPositionInCompany(InputService.input("Введите новую должность"));
                                    Objects.requireNonNull(EmployeeService.getCEO()).upSalary(Float.parseFloat(InputService.input("Введите процент повышения зарплаты. Например: 1.15f - 15%, 1.05f - 5%")), temp);
                                    EmployeeDecorator.decorateOutput(temp);
                                    temp.updateOutputMap();
                                    EmployeeService.fillOutputMap(temp);
                                }
                            }
                            case 2 -> {
                                for (Employee employee : EmployeeService.getEmployees()) {
                                    if (!employee.isCEO())
                                        EmployeeDecorator.decorateOutput(employee,
                                                EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 1),
                                                EmployeeService.getHashCode(EmployeeService.fillMethods(employee), 5));
                                }
                                Employee temp = EmployeeService.findByName(InputService.input("Введите имя сотрудника, у которого хотите поменять должность."));
                                if (temp != null) {
                                    if (!temp.getPositionInCompany().contains("grade")) {
                                        temp.setPositionInCompany("III grade " + temp.getPositionInCompany());
                                        Objects.requireNonNull(EmployeeService.getCEO()).upSalary(1.05f, temp);
                                        temp.updateOutputMap();
                                        EmployeeService.fillOutputMap(temp);
                                    } else if (temp.getPositionInCompany().contains("III")) {
                                        temp.setPositionInCompany(temp.getPositionInCompany().replace("III", "II"));
                                        Objects.requireNonNull(EmployeeService.getCEO()).upSalary(1.10f, temp);
                                        temp.updateOutputMap();
                                        EmployeeService.fillOutputMap(temp);
                                    } else if (temp.getPositionInCompany().contains("II")) {
                                        temp.setPositionInCompany(temp.getPositionInCompany().replace("II", "I"));
                                        Objects.requireNonNull(EmployeeService.getCEO()).upSalary(1.15f, temp);
                                        temp.updateOutputMap();
                                        EmployeeService.fillOutputMap(temp);
                                    } else {
                                        System.out.println("Сотрудник имеет максимум повышений в совей должности.");
                                    }
                                }
                            }
                            case 3 -> {
                            }
                        }
                    }else{
                        System.out.println("Повышать в должности может только руководитель. Добавьте его в компанию.");
                    }

                }
                // конец блока с повышением в должности

                // начало блока редактирования сотрудника
                case 6 -> {
                    editEmployeeMenu();
                    switch (Integer.parseInt(InputService.input("Введите команду."))){
                        case 1 -> {
                            editAutoOrByHands();
                            switch (Integer.parseInt(InputService.input("Введите команду."))){
                                case 1 -> {
                                    EmployeeDecorator.decorateOutput(EmployeeService.getEmployees());
                                    Employee edit = EmployeeService
                                            .removeEmployee(
                                                    EmployeeService
                                                            .findByPhone(
                                                                    InputService
                                                                            .input("Введите номер телефона сотрудника,у которого хотите изменить должность.")
                                                            )
                                            );
                                    assert edit != null;
                                    edit.setPositionInCompany(InputService.input("Введите новую должность"));
                                    EmployeeService.addEmployee(edit);
                                }
                                case 2 -> {
                                    EmployeeDecorator.decorateOutput(EmployeeService.getEmployees());
                                    Employee edit = EmployeeService
                                            .removeEmployee(
                                                    EmployeeService
                                                            .findByPhone(
                                                                    InputService
                                                                            .input("Введите номер телефона сотрудника,у которого хотите изменить должность.")
                                                            )
                                            );
                                    assert edit != null;
                                    edit.setPositionInCompany(EmployeeDBService.getRandomPosition());
                                    EmployeeService.addEmployee(edit);
                                }
                                case 0 -> {
                                }
                            }
                        }
                        case 2 -> {
                            editAutoOrByHands();
                            switch (Integer.parseInt(InputService.input("Введите команду."))){
                                case 1 -> {
                                    EmployeeDecorator.decorateOutput(EmployeeService.getEmployees());
                                    Employee edit = EmployeeService
                                            .removeEmployee(
                                                    EmployeeService
                                                            .findByName(
                                                                    InputService
                                                                            .input("Введите имя сотрудника,у которого хотите изменить номер телефона.")
                                                            )
                                            );
                                    assert edit != null;
                                    edit.setPhoneNumber(InputService.input("Введите новый номер телефона."));
                                    EmployeeService.addEmployee(edit);
                                }
                                case 2 -> {
                                    EmployeeDecorator.decorateOutput(EmployeeService.getEmployees());
                                    Employee edit = EmployeeService
                                            .removeEmployee(
                                                    EmployeeService
                                                            .findByName(
                                                                    InputService
                                                                            .input("Введите имя сотрудника,у которого хотите изменить номер телефона.")
                                                            )
                                            );
                                    assert edit != null;
                                    edit.setPhoneNumber(EmployeeDBService.getRandomPhoneNumber());
                                    EmployeeService.addEmployee(edit);
                                }
                                case 0 -> {
                                }
                            }
                        }
                        case 3 -> {
                        }
                    }
                }
                // конец блока редактирования сотрудника

                // начало блока повышения зарплаты
                case 7 -> {
                    if (EmployeeService.getEmployees().get(0).isCEO()){
                        System.out.println("1. Повысить зарплату одному сотруднику");
                        System.out.println("2. Повысить зарплату всем сотрудникам (не CEO)");
                        System.out.println("3. Выход");
                        switch (Integer.parseInt(InputService.input("Введите команду."))){
                            case 1 -> {
                                for (Employee employee : EmployeeService.getEmployees()){
                                    if (!employee.isCEO()){
                                        EmployeeDecorator.decorateOutput(employee);
                                    }
                                }
                                Employee edit = EmployeeService
                                        .removeEmployee(
                                                EmployeeService
                                                        .findByPhone(
                                                                InputService
                                                                        .input("Введите номер телефона сотрудника,у которого хотите изменить должность.")
                                                        )
                                        );
                                Objects.requireNonNull(EmployeeService
                                                .getCEO())
                                        .upSalary(
                                                Float.parseFloat(
                                                        InputService.input(
                                                                "Введите на сколько процентов повысить зарплату. (Вводить в формате: 1.05 - 5%, 1.15 - 15%)")),
                                                edit);
                                EmployeeService.addEmployee(edit);
                            }
                            case 2 -> {
                                float percent = Float.parseFloat(InputService.input("Введите на сколько процентов повысить зарплату. (Вводить в формате: 1.05 - 5%, 1.15 - 15%)"));
                                for (Employee employee : EmployeeService.getEmployees()){
                                    Objects.requireNonNull(EmployeeService.getCEO()).upSalary(percent, employee);
                                }
                            }
                            case 3 -> {
                            }
                        }
                    }else{
                        System.out.println("Повысить зарплату может только руководитель,а он вышел по делам.");
                    }

                }
                // конец блока повышения зарплаты

                // начало блока увольнения сотрудника
                case 8 -> {
                    if(EmployeeService.getEmployees().size() > 1 && Objects.requireNonNull(EmployeeService.getCEO()).isCEO()){
                        for (Employee employee : EmployeeService.getEmployees()){
                            if (!employee.isCEO()){
                                EmployeeDecorator.decorateOutput(employee);
                            }
                        }
                        System.out.printf("Удален сотрудник %s\n", EmployeeService.removeEmployee(EmployeeService.findByPhone(InputService.input("Введите номер телефона сотрудника, которого необходимо уволить."))));
                        if (!EmployeeService.getEmployees().isEmpty() && EmployeeService.getEmployees().get(0).isCEO()){
                            Objects.requireNonNull(EmployeeService.getCEO()).updateOutputMap();
                        }else{
                            EmployeeService.getEmployees().get(0).updateOutputMap();
                        }
                    }else if(!EmployeeService.getEmployees().get(0).isCEO()) {
                        System.out.println("В компании только сотрудники, руководитель вышел по делам, а без него нельзя никого уволить.");
                    }else{
                        System.out.println("Нет сотрудников для увольнения. В компании пока только CEO.");
                    }

                }
                // конец блока увольнения сотрудника

                // начало блока О компании
                case 9 -> AboutCompany.aboutCompany();
                // конец блока О компании

                // начало блока с подсказками
                case 10 -> {
                    if(isTip){
                        System.out.println("1. Отключить?");
                        System.out.println("0. Выход");
                        switch (Integer.parseInt(InputService.input("Введите команду..."))) {
                            case 1 -> {
                                tipOne = "";
                                tipTwo = "";
                                tipThree = "";
                                isTip = false;
                            }
                            case 0 -> {
                            }
                        }
                    }else{

                        System.out.println("1. Включить?");
                        System.out.println("0. Выход");
                        switch (Integer.parseInt(InputService.input("Введите команду..."))) {
                            case 1 -> {
                                tipOne = "*";
                                tipTwo = "**";
                                tipThree = "***";
                                isTip = true;
                            }
                            case 0 -> {
                            }
                        }
                    }
                }
                // конец блока с подсказками
                case 0 -> {
                    System.out.println("Всего наилучшего, надеемся на скорейшее Ваше возвращение.");
                    return;
                }
            }
        }

    }

    public static void isTipOn(){
        if (isTip){
            tipOne = "(*)";
            tipTwo = "(**)";
            tipThree = "(***)";
        }else{
            tipOne = "";
            tipTwo = "";
            tipThree = "";
        }
    }

    // Основное меню
    private static void mainMenu(){
        System.out.println("Выберите (введите номер) желаемое действие из списка:");
        // Тестировать, особенно номера для меню
        if(EmployeeService.getEmployees().isEmpty()){
            System.out.printf("1. Добавить руководителя%s.\n", tipThree);
        }else if(!EmployeeService.getEmployees().get(0).isCEO()){
            System.out.printf("1. Добавить руководителя%s.\n", tipThree);
        } else {
            System.out.printf("1. Изменить руководителя%s.\n", tipTwo);
        }
        System.out.printf("2. Добавить сотрудника%s.\n", tipThree);
        if (!EmployeeService.getEmployees().isEmpty() && EmployeeService.getEmployees().get(0).isCEO()){
            System.out.println("3. Просмотреть руководителя.");

        }else{
            System.out.println("3. Просмотреть руководителя. (Пока нет руководителя для просмотра).");
        }
        if(EmployeeService.getEmployees().size() == 1 && EmployeeService.getEmployees().get(0).isCEO()){
            tipMenu("компании");
        }

        if(EmployeeService.getEmployees().size() > 1 && EmployeeService.getEmployees().get(0).isCEO()){
            System.out.printf("4. Меню сотрудников%s.\n", tipThree);
            System.out.printf("5. Повысить сотрудника в должности%s.\n", tipTwo);
            System.out.printf("6. Изменить сотрудника%s.\n", tipOne);
            System.out.printf("7. Повысить зарплату%s.\n", tipThree);
            System.out.println("8. Уволить сотрудника.");
            tipMenu("компании");

        }else if (!EmployeeService.getEmployees().isEmpty() && !EmployeeService.getEmployees().get(0).isCEO()){
            System.out.println("4. Меню сотрудников.");
            System.out.println("5. Повысить сотрудника в должности может только руководитель. Добавьте его в компанию.");
            System.out.println("6. Изменить сотрудника.");
            System.out.println("7. Повысить зарплату.");
            System.out.println("8. Уволить сотрудника.");
            tipMenu("компании");
        }
        if(EmployeeService.getEmployees().isEmpty()){
            tipMenu("компании");
        }


    }

    // Вспомогательные меню

    private static void tipMenu(String menu){
        if(isTip){
            System.out.println("9. О компании.");
            System.out.println("10. Скрыть подсказки.");
            System.out.printf("0. Выход из %s.\n", menu);
        }else{
            System.out.println("9. О компании.");
            System.out.println("10. Показать подсказки.");
            System.out.printf("0. Выход из %s.\n", menu);
        }
        if(isTip){
            System.out.println();
            System.out.println("ПОДСКАЗКА: * - пункт меню без которых не выполнить ДЗ");
            System.out.println("ПОДСКАЗКА: ** - пункт меню интересный и вспомогательный для ДЗ");
            System.out.println("ПОДСКАЗКА: *** - пункт меню с самим ДЗ.");
        }

    }

    // Меню создания сотрудников. Общее для всех
    private static void createEmployeeMenu(String employee){
        if(Objects.equals(employee, "руководителя")){
            System.out.printf("1. Создать %s вручную.\n", employee);
            System.out.printf("2. Создать %s автоматически из Базы Данных.\n", employee);
            System.out.println("0. Выход из меню создания.\n");
        }else {
            System.out.printf("1. Создать %s вручную.\n", employee);
            System.out.printf("2. Создать %s автоматически из Базы Данных.\n", employee);
            System.out.printf("3. Создать группу %sов автоматически из Базы Данных.\n", employee.substring(0, employee.length()-1));
            System.out.println("0. Выход из меню создания.\n");
        }

    }

    private static void editAutoOrByHands(){
        System.out.println("1. Заменить руками.");
        System.out.println("2. Заменить автоматически из Базы Данных.");
        System.out.println("0. Выход");
    }

    private static void employeeMenu(){
        System.out.printf("1. Просмотреть всех %sов\n", "сотрудник");
        System.out.printf("2. Найти %sа по имени.\n", "сотрудник");
        System.out.printf("3. Найти %sа по телефону.\n", "сотрудник");
        System.out.printf("4. Просмотреть только Ф.И.О. всех %sов\n", "сотрудник");
        System.out.printf("5. Просмотреть Имя и телефон всех %sов\n", "сотрудник");
        System.out.printf("6. Просмотреть выборочную информацию о всех %sах%s\n", "сотрудник", tipTwo);
        System.out.printf("7. Сравнение всех %sов по возрасту%s\n", "сотрудник", tipThree);
        tipMenu("меню");
    }

    private static void employeeInfoMenu(){
        System.out.printf("1. Посмотреть дату рождения %sов\n", "сотрудник");
        System.out.printf("2. Посмотреть имя %sов\n", "сотрудник");
        System.out.printf("3. Посмотреть фамилию %sов\n", "сотрудник");
        System.out.printf("4. Посмотреть отчество %sов\n", "сотрудник");
        System.out.printf("5. Посмотреть телефон %sов\n", "сотрудник");
        System.out.printf("6. Посмотреть должность %sов\n", "сотрудник");
        System.out.printf("7. Посмотреть зарплату %sов\n", "сотрудник");
        System.out.printf("8. Посмотреть возраст %sв\n", "сотрудник");
        System.out.println("0. Выход из меню.");
        System.out.println("TIP: для выбора нескольких параметров, просто вводите их порядковый номер без пробелов.");
    }

    private static void editCEOMenu(){
        System.out.println("1. Заменить руководителя");
        System.out.println("2. Изменить номер телефона руководителя");
        System.out.println("3. Изменить зарплату руководителя");
        System.out.println("0. Выход.");
    }

    private static void editEmployeeMenu(){
        System.out.println("1. Поменять должность сотрудника");
        System.out.println("2. Изменить номер телефона сотрудника");
        System.out.println("0. Выход.");
    }

    private static void employeePositionUpMenu(){
        System.out.println("1. Повысить в должности и повысить зарплату в ручную.");
        System.out.println("2. Повысить в должности и повысить зарплату автоматически.");
        System.out.println("0. Выход.");
    }

}
