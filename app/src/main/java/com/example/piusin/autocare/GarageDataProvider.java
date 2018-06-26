package com.example.piusin.autocare;

/**
 * Created by Piusin on 3/30/2018.
 */

public class GarageDataProvider {
    private String garageId;
    private String garageImage;
    private String garageName;
    private String garageLocation;
    private String garageOpenTime;
    private String garageCloseTime;
    private String garageOwner;
    private String description;

    public GarageDataProvider(String garageId, String garageImage, String garageName, String garageLocation, String garageOpenTime, String garageCloseTime, String garageOwner, String description) {
        this.garageId = garageId;
        this.garageImage = garageImage;
        this.garageName = garageName;
        this.garageLocation = garageLocation;
        this.garageOpenTime = garageOpenTime;
        this.garageCloseTime = garageCloseTime;
        this.garageOwner = garageOwner;
        this.description = description;
    }

    public String getGarageId() {
        return garageId;
    }

    public String getGarageImage() {
        return garageImage;
    }

    public String getGarageName() {
        return garageName;
    }

    public String getGarageLocation() {
        return garageLocation;
    }

    public String getGarageOpenTime() {
        return garageOpenTime;
    }

    public String getGarageCloseTime() {
        return garageCloseTime;
    }

    public String getGarageOwner() {
        return garageOwner;
    }

    public String getDescription() {
        return description;
    }
}
