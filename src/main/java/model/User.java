package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
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

    public boolean isMale() {
        return isMale;
    }
}