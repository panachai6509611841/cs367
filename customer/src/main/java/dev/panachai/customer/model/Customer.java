package dev.panachai.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {
  private @Id @GeneratedValue Long id;
  private String customerName;
  private String customerPhone;
  private String location;
  private String appointmentDates;
  private String technicianID;

  public Customer() {}

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCustomerName() {
    return customerName;
  }
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  public String getCustomerPhone() {
    return customerPhone;
  }
  public void setCustomerPhone(String customerPhone) {
    this.customerPhone = customerPhone;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public String getAppointmentDates() {
    return appointmentDates;
  }
  public void setAppointmentDates(String appointmentDates) {
    this.appointmentDates = appointmentDates;
  }
  public String getTechnicianID() {
    return technicianID;
  }
  public void setTechnicianID(String technicianID) {
    this.technicianID = technicianID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
    result = prime * result + ((customerPhone == null) ? 0 : customerPhone.hashCode());
    result = prime * result + ((location == null) ? 0 : location.hashCode());
    result = prime * result + ((appointmentDates == null) ? 0 : appointmentDates.hashCode());
    result = prime * result + ((technicianID == null) ? 0 : technicianID.hashCode());
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
    Customer other = (Customer) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (customerName == null) {
      if (other.customerName != null)
        return false;
    } else if (!customerName.equals(other.customerName))
      return false;
    if (customerPhone == null) {
      if (other.customerPhone != null)
        return false;
    } else if (!customerPhone.equals(other.customerPhone))
      return false;
    if (location == null) {
      if (other.location != null)
        return false;
    } else if (!location.equals(other.location))
      return false;
    if (appointmentDates == null) {
      if (other.appointmentDates != null)
        return false;
    } else if (!appointmentDates.equals(other.appointmentDates))
      return false;
    if (technicianID == null) {
      if (other.technicianID != null)
        return false;
    } else if (!technicianID.equals(other.technicianID))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", location="
        + location + ", appointmentDates=" + appointmentDates + ", technicianID=" + technicianID + "]";
  }

  public Customer(String customerName, String customerPhone, String location, String appointmentDates,
      String technicianID) {
    this.customerName = customerName;
    this.customerPhone = customerPhone;
    this.location = location;
    this.appointmentDates = appointmentDates;
    this.technicianID = technicianID;
  }

  
  
}
