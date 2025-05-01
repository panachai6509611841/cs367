package assignment2.appointment;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Technician {
    private @Id @GeneratedValue Long id;
    private String name;
    private String phone;
    private String expertise;
    private LocalDateTime appointmentDate;
    private String status; 

    Technician() {}

    Technician(String name, String phone, String expertise, LocalDateTime appointmentDate, String status) {
        this.name = name;
        this.phone = phone;
        this.expertise = expertise;
        this.appointmentDate = appointmentDate;
        this.status = status;
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

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Technician [id=" + id + ", name=" + name + ", phone=" + phone + ", expertise=" + expertise
                + ", appointmentDate=" + appointmentDate + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((expertise == null) ? 0 : expertise.hashCode());
        result = prime * result + ((appointmentDate == null) ? 0 : appointmentDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        if (appointmentDate == null) {
            if (other.appointmentDate != null)
                return false;
        } else if (!appointmentDate.equals(other.appointmentDate))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    

}
