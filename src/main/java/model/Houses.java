package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Houses {
    private String id;
    private String floorCount;
    private String price;
    private List<String> lodgers;

    public Houses(String id, String floorCount, String price, List<String> lodgers) {
        this.id = id;
        this.floorCount = floorCount;
        this.price = price;
        this.lodgers = lodgers;
    }
}
