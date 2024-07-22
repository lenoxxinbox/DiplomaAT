package model;

public class House {
    private String floorCount;
    private String parkingPlaces;
    private String placeWarmAndCovered;
    private String placeNoWarmAndCovered;
    private String placeWarmAndNoCovered;
    private String placeNoWarmAndNoCovered;
    private String price;


    public House(String floorCount, String placeWarmAndCovered, String placeNoWarmAndCovered, String placeWarmAndNoCovered, String placeNoWarmAndNoCovered, String price) {
        this.floorCount = floorCount;
        this.placeWarmAndCovered = placeWarmAndCovered;
        this.placeNoWarmAndCovered = placeNoWarmAndCovered;
        this.placeWarmAndNoCovered = placeWarmAndNoCovered;
        this.placeNoWarmAndNoCovered = placeNoWarmAndNoCovered;
        this.price = price;
    }

    public House(String floorCount, String parkingPlaces, String price) {
        this.floorCount = floorCount;
        this.parkingPlaces = parkingPlaces;
        this.price = price;
    }

    public String getPlaceWarmAndCovered() {
        return placeWarmAndCovered;
    }

    public String getPlaceNoWarmAndCovered() {
        return placeNoWarmAndCovered;
    }

    public String getPlaceWarmAndNoCovered() {
        return placeWarmAndNoCovered;
    }

    public String getPlaceNoWarmAndNoCovered() {
        return placeNoWarmAndNoCovered;
    }

    public String getFloorCount() {
        return floorCount;
    }

    public String getParkingPlaces() {
        return parkingPlaces;
    }

    public String getPrice() {
        return price;
    }
}
