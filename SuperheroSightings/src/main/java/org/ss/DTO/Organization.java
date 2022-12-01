package org.ss.DTO;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class Organization {

    
    private int OrganizationID;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message="Name must be less than 50 characters.")
    private String name;
    
    private boolean isSuper;
    
    @Size(max = 255, message="Description must be less than 255 characters.")
    private String description;
    
    @Size(max = 255, message="Address must be less than 255 characters.")
    private String address;
    
    @Size(max = 255, message="Contact must be less than 255 characters.")
    private String contact;
    
    private List<Super> supers;

    @Override
    public String toString() {
        return "Organization{" + "id=" + OrganizationID + ", name=" + name + ", isSuper=" + isSuper + ", description=" + description + ", address=" + address + ", contact=" + contact + ", supers=" + supers + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.OrganizationID;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + (this.isSuper ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.contact);
        hash = 53 * hash + Objects.hashCode(this.supers);
        return hash;
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
        final Organization other = (Organization) obj;
        if (this.OrganizationID != other.OrganizationID) {
            return false;
        }
        if (this.isSuper != other.isSuper) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.supers, other.supers)) {
            return false;
        }
        return true;
    }

    public int getOrganizationID() {
        return OrganizationID;
    }

    public void setOrganizationID(int id) {
        this.OrganizationID = OrganizationID;
    }

    public String getOrganizationName() {
        return name;
    }

    public void setOrganizationName(String name) {
        this.name = name;
    }

    public boolean isIsSuper() {
        return isSuper;
    }

    public void setIsSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public String getOrganizationDescription() {
        return description;
    }

    public void setOrganizationDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationContact() {
        return contact;
    }

    public void setOrganizationContact(String contact) {
        this.contact = contact;
    }

    public List<Super> getSupers() {
        return supers;
    }

    public void setSupers(List<Super> supers) {
        this.supers = supers;
    }
    
    
    
}
