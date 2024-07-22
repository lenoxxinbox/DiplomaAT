package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
    private String firstName;
    private String secondName;
    private int age;
    private double money;
    @JsonIgnore
    private boolean isMale;

    public User(String firstName, String secondName, int age, double money, boolean isMale) {
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

    public double getMoney() {
        return money;
    }

    @JsonIgnore
    public boolean isMale() {
        return isMale;
    }

    @JsonProperty("sex")
    public String getSex() {
        return isMale ? "MALE" : "FEMALE";
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
