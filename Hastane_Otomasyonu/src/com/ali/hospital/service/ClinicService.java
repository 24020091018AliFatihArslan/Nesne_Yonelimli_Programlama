package com.ali.hospital.service;

import com.ali.hospital.dto.ClinicDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventBus;
import com.ali.hospital.event.EventType;
import com.ali.hospital.repo.InMemoryStore;

import java.util.List;
import java.util.stream.Collectors;

public class ClinicService {
    private final InMemoryStore store;
    private final EventBus bus;

    public ClinicService(InMemoryStore store, EventBus bus) {
        this.store = store;
        this.bus = bus;
    }

    public List<ClinicDTO> listClinics() {
        return store.listClinics().stream()
                .map(c -> new ClinicDTO(c.getId(), c.getName()))
                .collect(Collectors.toList());
    }

    public void addClinic(String name) {
        store.addClinic(name);
        bus.publish(new AppEvent(EventType.DATA_CHANGED));
    }
}
