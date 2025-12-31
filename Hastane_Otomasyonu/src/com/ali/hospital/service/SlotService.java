package com.ali.hospital.service;

import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.domain.WorkingSlot;
import com.ali.hospital.dto.SlotDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventBus;
import com.ali.hospital.event.EventType;
import com.ali.hospital.repo.InMemoryStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SlotService {
    private final InMemoryStore store;
    private final EventBus bus;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public SlotService(InMemoryStore store, EventBus bus) {
        this.store = store;
        this.bus = bus;
    }

    public void addSlot(long doctorId, LocalDateTime dt) {
        store.addSlot(doctorId, dt);
        bus.publish(new AppEvent(EventType.DATA_CHANGED));
    }

    public List<SlotDTO> listSlotsByDoctor(long doctorId) {
        return store.listSlotsByDoctor(doctorId).stream()
                .map(s -> new SlotDTO(s.getId(), s.getDoctorId(), store.getUser(s.getDoctorId()).getFullName(), s.getDateTime().format(fmt), s.getStatus()))
                .collect(Collectors.toList());
    }

    public List<SlotDTO> listOpenSlotsByDoctor(long doctorId) {
        return store.listOpenSlotsByDoctor(doctorId).stream()
                .map(s -> new SlotDTO(s.getId(), s.getDoctorId(), store.getUser(s.getDoctorId()).getFullName(), s.getDateTime().format(fmt), s.getStatus()))
                .collect(Collectors.toList());
    }

    public BaseUser getUser(long id){ return store.getUser(id); }
}
