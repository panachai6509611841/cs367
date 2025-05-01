package dev.panachai.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.panachai.customer.dto.BookingRequest;
import dev.panachai.customer.model.Appointment;
import dev.panachai.customer.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
     public Appointment bookAppointment(BookingRequest request) {
        Appointment appt = new Appointment();
        appt.setCustomerId(request.getCustomerId());
        appt.setTechnicianId(request.getTechnicianId());
        appt.setScheduledTime(request.getScheduledTime());
        appt.setStatus("PENDING");
        return appointmentRepository.save(appt);
    }

    public List<Appointment> getAppointmentsByCustomer(Long customerId) {
        return appointmentRepository.findByCustomerId(customerId);
    }

    public List<Appointment> getAppointmentsForTechnician(Long technicianId) {
        return appointmentRepository.findByTechnicianId(technicianId);
    }
}
