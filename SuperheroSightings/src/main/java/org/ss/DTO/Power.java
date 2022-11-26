package org.ss.DTO;

import java.util.Objects;
import javax.validation.constraints.NotBlank;



/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class Power {

    private int powerID;

    @NotBlank(message = "Enter Power name.")
    private String powerName;

    @NotBlank(message = "Enter Power description.")
    private String powerDescription;

    public int getPowerID() {
        return powerID;
    }

    public void setPowerID(int powerID) {
        this.powerID = powerID;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerID, powerName, powerDescription);
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
        final Power other = (Power) obj;
        if (this.powerID != other.powerID) {
            return false;
        }
        if (!Objects.equals(this.powerName, other.powerName)) {
            return false;
        }
        return Objects.equals(this.powerDescription, other.powerDescription);
    }


}
