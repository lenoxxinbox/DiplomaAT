package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
    private String firstName;
    private String secondName;
    private int age;
    private double money;
    private boolean isMale;

    public User(String firstName, String secondName, int age, double money, boolean isMale) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.money = money;
        this.isMale = isMale;
    }

    public boolean isMale() {
        return isMale;
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