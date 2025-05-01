package dev.panachai.customer.dto;

import java.time.LocalDateTime;

public class BookingRequest {
    private Long customerId;
    private Long technicianId;
    private LocalDateTime scheduledTime;
    public BookingRequest(Long customerId, Long technicianId, LocalDateTime scheduledTime) {
        this.customerId = customerId;
        this.technicianId = technicianId;
        this.scheduledTime = scheduledTime;
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
    
}
