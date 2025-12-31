package com.ali.hospital.service;

import com.ali.hospital.domain.Appointment;
import com.ali.hospital.dto.AppointmentDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventBus;
import com.ali.hospital.event.EventType;
import com.ali.hospital.repo.InMemoryStore;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private final InMemoryStore store;
    private final EventBus bus;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AppointmentService(InMemoryStore store, EventBus bus) {
        this.store = store;
        this.bus = bus;
    }

    public boolean book(long slotId, long patientId) {
        boolean ok = store.book(slotId, patientId);
        if (ok) bus.publish(new AppEvent(EventType.DATA_CHANGED));
        return ok;
    }

    public List<AppointmentDTO> listAll() {
        return store.listAppointmentsAll().stream().map(this::map).collect(Collectors.toList());
    }

    public List<AppointmentDTO> listByPatient(long patientId) {
        return store.listAppointmentsByPatient(patientId).stream().map(this::map).collect(Collectors.toList());
    }

    public List<AppointmentDTO> listByDoctor(long doctorId) {
        return store.listAppointmentsByDoctor(doctorId).stream().map(this::map).collect(Collectors.toList());
    }

    private AppointmentDTO map(Appointment a) {
        String patient = store.getUser(a.getPatientId()).getFullName();
        String doctor = store.getUser(a.getDoctorId()).getFullName();
        String clinic = store.getClinic(a.getClinicId()).getName();
        return new AppointmentDTO(a.getId(), patient, doctor, clinic, a.getDateTime().format(fmt));
    }
}
