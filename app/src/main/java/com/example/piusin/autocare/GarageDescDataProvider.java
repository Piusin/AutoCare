package com.example.piusin.autocare;

/**
 * Created by Piusin on 3/31/2018.
 */

public class GarageDescDataProvider {
    private String garageId;
    private String garageImage;
    private String garageName;
    private String garageLocation;
    private String garageOpenTime;
    private String garageCloseTime;
    private String garageOwnerName;
    private String garageOwnerExperience;
    private String garageDesc;
    private String garageOwnerNumber;

    public GarageDescDataProvider(String garageId, String garageImage, String garageName, String garageLocation, String garageOpenTime, String garageCloseTime, String garageOwnerName, String garageOwnerExperience, String garageDesc, String garageOwnerNumber) {
        this.garageId = garageId;
        this.garageImage = garageImage;
        this.garageName = garageName;
        this.garageLocation = garageLocation;
        this.garageOpenTime = garageOpenTime;
        this.garageCloseTime = garageCloseTime;
        this.garageOwnerName = garageOwnerName;
        this.garageOwnerExperience = garageOwnerExperience;
        this.garageDesc = garageDesc;
        this.garageOwnerNumber = garageOwnerNumber;
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

    public String getGarageOwnerName() {
        return garageOwnerName;
    }

    public String getGarageOwnerExperience() {
        return garageOwnerExperience;
    }

    public String getGarageDesc() {
        return garageDesc;
    }

    public String getGarageOwnerNumber() {
        return garageOwnerNumber;
    }
}
