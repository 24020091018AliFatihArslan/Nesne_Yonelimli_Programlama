package com.ali.hospital;

import com.ali.hospital.event.EventBus;
import com.ali.hospital.repo.InMemoryStore;
import com.ali.hospital.service.*;

public class AppContext {
    public final EventBus bus = new EventBus();
    public final InMemoryStore store = new InMemoryStore();

    public final AuthService auth = new AuthService(store);
    public final ClinicService clinic = new ClinicService(store, bus);
    public final DoctorService doctor = new DoctorService(store, bus);
    public final SlotService slot = new SlotService(store, bus);
    public final AppointmentService appointment = new AppointmentService(store, bus);
}
