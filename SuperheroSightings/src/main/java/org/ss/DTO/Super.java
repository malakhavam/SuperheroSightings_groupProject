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

    private List<Power> superPowers;

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

    public List<Power> getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(List<Power> superPowers) {
        this.superPowers = superPowers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(superID, superName, superDescription, superPowers);
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
        return Objects.equals(this.superPowers, other.superPowers);
    }

}
