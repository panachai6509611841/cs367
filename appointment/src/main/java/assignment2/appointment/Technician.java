package assignment2.appointment;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Technician {
    private @Id @GeneratedValue Long id;
    private String name;
    private String phone;
    private String expertise;
    @ElementCollection
    private List<String> appointmentDates = new ArrayList<>();
    @ElementCollection
    private List<String> customerNames = new ArrayList<>();


    Technician() {}

    public List<String> getAppointmentDates() {
        return appointmentDates;
    }

    public void setAppointmentDates(List<String> appointmentDates) {
        this.appointmentDates = appointmentDates;
    }

    public List<String> getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(List<String> customerNames) {
        this.customerNames = customerNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    
    @Override
    public String toString() {
        return "Technician [id=" + id + ", name=" + name + ", phone=" + phone + ", expertise=" + expertise
                + ", appointmentDates=" + appointmentDates + ", customerNames=" + customerNames + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((expertise == null) ? 0 : expertise.hashCode());
        result = prime * result + ((appointmentDates == null) ? 0 : appointmentDates.hashCode());
        result = prime * result + ((customerNames == null) ? 0 : customerNames.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Technician other = (Technician) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (expertise == null) {
            if (other.expertise != null)
                return false;
        } else if (!expertise.equals(other.expertise))
            return false;
        if (appointmentDates == null) {
            if (other.appointmentDates != null)
                return false;
        } else if (!appointmentDates.equals(other.appointmentDates))
            return false;
        if (customerNames == null) {
            if (other.customerNames != null)
                return false;
        } else if (!customerNames.equals(other.customerNames))
            return false;
        return true;
    }

    public Technician( String name, String phone, String expertise, List<String> appointmentDates,
            List<String> customerNames) {
        this.name = name;
        this.phone = phone;
        this.expertise = expertise;
        this.appointmentDates = appointmentDates;
        this.customerNames = customerNames;
    }



}
