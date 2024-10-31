package seminar3homework.util;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Random;

public class EmployeeDB{

    private static final Random RANDOM = new Random();
    @Getter
    private static String[] posts = {
            "Junior Developer", "Junior QA Engineer", "Junior Data Analyst", "Developer",
            "System Administrator", "Database Administrator", "Technical Writer",
            "Project Coordinator", "Senior Developer", "DevOps Engineer", "Security Specialist",
            "Product Manager", "Team Lead", "Tech Lead", "Data Science Manager", "Head of Product"
    };

    @Getter
    private static String[] names = {
            "Adam", "Michael", "Lola", "Anna", "Violetta", "Lion", "Freddy", "Lev", "Dmitriy",
            "Sergey", "Svetlana", "Naomi", "Alex", "Oleg", "Pavel", "Smith", "Omar", "Vlad",
            "Vadim", "Vyacheslav", "Inna", "Maria", "Sara", "Ann", "Chu", "Pu", "Mila",
            "Yakov", "Sam", "Masha", "Olga", "Ola", "Viola", "Vera", "Sonya", "Bella"
    };

    @Getter
    private static String[] lastName = {
            "Anderson", "Bailey", "Bell", "Bennett", "Brooks", "Carter", "Collins", "Cooper",
            "Davis", "Edwards", "Evans", "Foster", "Gray", "Green", "Hall", "Harris", "Howard",
            "Hughes", "Jackson", "Johnson", "Kelly", "King", "Lewis", "Long", "Martin", "Miller",
            "Moore", "Morgan", "Murphy", "Parker", "Reed", "Roberts", "Scott", "Taylor", "Thomas",
            "Thompson", "Walker", "White", "Williams", "Young"
    };

    @Getter
    private static String[] secondNames = {
            "Adams", "Bailey", "Bennett", "Brooks", "Carter", "Clark",
            "Collins", "Cooper", "Cox", "Davis", "Edwards", "Evans", "Foster", "Gray", "Green", "Hall",
            "Harris", "Howard", "Hughes", "Jackson", "Johnson", "Kelly", "King", "Lewis", "Martin", "Miller",
            "Moore", "Morgan", "Murphy", "Parker", "Phillips", "Price", "Reed", "Roberts", "Russell", "Scott",
            "Stewart", "Taylor", "Thomas", "Williams"
    };


    public static int getRandomSalary(){
        return RANDOM.nextInt(150000, 600001);
    }

    public static int getRandomCEOSalary(){
        return RANDOM.nextInt(600000, 1000001);
    }

    public static String getRandomPhoneNumber(){

        StringBuilder phoneNumber = new StringBuilder(16);

        for (int i = 0; i < phoneNumber.capacity(); i++){
            if (i != 0 && i != 1 && i != 2 && i != 6 && i != 10 && i != 13){
                phoneNumber.append(RANDOM.nextInt(0,10));
            } else if (i == 1) {
                phoneNumber.append(RANDOM.nextInt(7,9));
            } else if(i == 0){
                phoneNumber.append("+");
            }else if(i == 2){
                phoneNumber.append("(");
            }else if(i == 6){
                phoneNumber.append(")");
            }else{
                phoneNumber.append("-");
            }
        }
        return phoneNumber.toString();
    }

    public static LocalDate getRandomBirthDate(){
        return LocalDate.of(
                RANDOM.nextInt(1971, LocalDate.now().getYear() - 19),
                RANDOM.nextInt(1, 13),
                RANDOM.nextInt(1, 28));
    }

}
