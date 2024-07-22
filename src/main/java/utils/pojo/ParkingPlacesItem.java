package utils.pojo;

import lombok.Data;
import uk.co.jemos.podam.common.PodamIntValue;

@Data
public class ParkingPlacesItem {
    @PodamIntValue(minValue = 1, maxValue = 7)
    private int placesCount;
    private boolean isWarm;
    private boolean isCovered;
}
