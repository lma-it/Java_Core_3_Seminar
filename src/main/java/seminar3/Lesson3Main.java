package seminar3;

public class Lesson3Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Basilar", 2);

        System.out.println(Cat
                .builder()
                .age(4)
                .name("Kruzik")
                .build()
        );

        System.out.println(cat);

    }
}
