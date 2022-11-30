package org.ss.DTO;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;



/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class Super {

    private int superID;

    @NotBlank(message = "Enter Super name.")
    private String superName;

    @NotBlank(message = "Enter Super description.")
    private String superDescription;

    private List<Power> powers;
    private List<Sighting> sightings;

    public int getSuperID() {
        return superID;
    }

    public void setSuperID(int superID) {
        this.superID = superID;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getSuperDescription() {
        return superDescription;
    }

    public void setSuperDescription(String superDescription) {
        this.superDescription = superDescription;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(superID, superName, superDescription, powers);
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
        final Super other = (Super) obj;
        if (this.superID != other.superID) {
            return false;
        }
        if (!Objects.equals(this.superName, other.superName)) {
            return false;
        }
        if (!Objects.equals(this.superDescription, other.superDescription)) {
            return false;
        }
        return Objects.equals(this.powers, other.powers);
    }

}
