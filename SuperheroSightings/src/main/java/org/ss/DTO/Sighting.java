package org.ss.DTO;


import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class Sighting {

    private int sightingID;

    @NotNull(message = "Date must be valid")
    private LocalDate sightingDate;

    private Super sightingSuper;

    private Location sightingLocation;

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate date) {
        this.sightingDate = date;
    }

    public Super getSightingSuper() {
        return sightingSuper;
    }

    public void setSightingSuper(Super sightingSuper) {
        this.sightingSuper = sightingSuper;
    }

    public Location getSightingLocation() {
        return sightingLocation;
    }

    public void setSightingLocation(Location sightingLocation) {
        this.sightingLocation = sightingLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sightingID, sightingDate, sightingSuper, sightingLocation);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingID != other.sightingID) {
            return false;
        }
        if (!Objects.equals(this.sightingDate, other.sightingDate)) {
            return false;
        }
        if (!Objects.equals(this.sightingSuper, other.sightingSuper)) {
            return false;
        }
        return Objects.equals(this.sightingLocation, other.sightingLocation);
    }


}
