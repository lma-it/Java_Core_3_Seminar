package seminar3homework.service;

import seminar3homework.util.EmployeeDB;
import java.time.LocalDate;
import java.util.Random;

public class EmployeeDBService {
    private static final Random RANDOM = new Random();


    public static String getRandomName(){
        return EmployeeDB.getNames()[RANDOM.nextInt(0, EmployeeDB.getNames().length - 1)];
    }

    public static String getRandomLastName(){
        return EmployeeDB.getLastName()[RANDOM.nextInt(0, EmployeeDB.getLastName().length - 1)];
    }

    public static String getRandomSecondName(){
        return EmployeeDB.getSecondNames()[RANDOM.nextInt(0, EmployeeDB.getSecondNames().length - 1)];
    }

    public static int getRandomSalary(){
        return EmployeeDB.getRandomSalary();
    }
    public static int getRandomCEOSalary() {return EmployeeDB.getRandomCEOSalary();}

    public static String getRandomPosition(){
        return EmployeeDB.getPosts()[RANDOM.nextInt(0, EmployeeDB.getPosts() .length - 1)];
    }

    public static String getRandomPhoneNumber(){
        return EmployeeDB.getRandomPhoneNumber();
    }

    public static LocalDate getRandomBirthDate(){
        return EmployeeDB.getRandomBirthDate();
    }
}
