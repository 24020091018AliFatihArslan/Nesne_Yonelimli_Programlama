package com.ali.hospital.service;

import com.ali.hospital.domain.Doctor;
import com.ali.hospital.dto.DoctorDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventBus;
import com.ali.hospital.event.EventType;
import com.ali.hospital.repo.InMemoryStore;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {
    private final InMemoryStore store;
    private final EventBus bus;

    public DoctorService(InMemoryStore store, EventBus bus) {
        this.store = store;
        this.bus = bus;
    }

    public List<DoctorDTO> listDoctorsByClinic(long clinicId) {
        return store.listDoctorsByClinic(clinicId).stream()
                .map(d -> new DoctorDTO(d.getId(), d.getFullName(), d.getClinicId(), store.getClinic(d.getClinicId()).getName()))
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> listAll() {
        return store.listDoctorsAll().stream()
                .map(d -> new DoctorDTO(d.getId(), d.getFullName(), d.getClinicId(), store.getClinic(d.getClinicId()).getName()))
                .collect(Collectors.toList());
    }

    public void addDoctor(String username, String pass, String fullName, long clinicId) {
        store.addDoctor(username, pass, fullName, clinicId);
        bus.publish(new AppEvent(EventType.DATA_CHANGED));
    }

    public Doctor getDoctor(long id) {
        return (Doctor) store.getUser(id);
    }
}
