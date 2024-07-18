package model;

public class User {
    private String firstName;
    private String secondName;
    private int age;
    private int money;
    private boolean isMale;

    public User(String firstName, String secondName, int age, int money, boolean isMale) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.money = money;
        this.isMale = isMale;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public int getMoney() {
        return money;
    }

    public boolean isMale() {
        return isMale;
    }
}
