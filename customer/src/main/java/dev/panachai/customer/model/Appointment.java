package dev.panachai.customer.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Appointment {
    private @Id
    @GeneratedValue
    Long id;
    private Long customerId;
    private Long technicianId;
    private LocalDateTime scheduledTime;
    private String status;
    public Appointment()
    {
    }
    public Appointment(Long id, Long customerId, Long technicianId, LocalDateTime scheduledTime, String status) {
        this.id = id;
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.scheduledTime = scheduledTime;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getTechnicianId() {
        return technicianId;
    }
    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }
    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((technicianId == null) ? 0 : technicianId.hashCode());
        result = prime * result + ((scheduledTime == null) ? 0 : scheduledTime.hashCode());
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
        Appointment other = (Appointment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (technicianId == null) {
            if (other.technicianId != null)
                return false;
        } else if (!technicianId.equals(other.technicianId))
            return false;
        if (scheduledTime == null) {
            if (other.scheduledTime != null)
                return false;
        } else if (!scheduledTime.equals(other.scheduledTime))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Appointment [id=" + id + ", customerId=" + customerId + ", technicianId=" + technicianId
                + ", scheduledTime=" + scheduledTime + ", status=" + status + "]";
    } 
    
    
}
