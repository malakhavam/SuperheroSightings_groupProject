package org.ss.DTO;

import java.util.Objects;
import javax.validation.constraints.NotBlank;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class Location {

    private int locationID;

    @NotBlank(message = "Enter Location name.")
    private String locationName;

    @NotBlank(message = "Enter Location description.")
    private String locationDescription;

    @NotBlank(message = "Enter Location address.")
    private String locationAddress;

    @NotBlank(message = "Enter Location latitude.")
    private String locationLatitude;

    @NotBlank(message = "Enter Location longitude.")
    private String locationLongitude;



    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }


    @Override
    public int hashCode() {
        return Objects.hash(locationID, locationName, locationDescription, locationAddress,locationLatitude ,locationLongitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationLatitude, other.locationLatitude)) {
            return false;
        }
        return Objects.equals(this.locationLongitude, other.locationLongitude);
    }


}
