package model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {
    @SerializedName("engineType")
    private String engineType;
    @SerializedName("mark")
    private String mark;
    @SerializedName("model")
    private String model;
    @SerializedName("price")
    private Double price;

    public Car(String engineType, String mark, String model, Double price) {
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engineType='" + engineType + '\'' +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
