package utils.pojo;

import java.util.List;

import lombok.Data;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamIntValue;

@Data
public class HouseExample {
    @PodamCollection(nbrElements = 1)
    private List<ParkingPlacesItem> parkingPlaces;
    @PodamIntValue(minValue = 999, maxValue = 99999)
    private int price;
    @PodamIntValue(minValue = 1, maxValue = 7)
    private int floorCount;
}
