package com.ali.hospital.repo;

import com.ali.hospital.domain.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryStore {
    private final IdGen id = new IdGen();

    public final Map<Long, Clinic> clinics = new LinkedHashMap<>();
    public final Map<Long, BaseUser> users = new LinkedHashMap<>();
    public final Map<Long, WorkingSlot> slots = new LinkedHashMap<>();
    public final Map<Long, Appointment> appointments = new LinkedHashMap<>();

    public InMemoryStore() {
        seed();
    }

    private void seed() {
        // Clinics
        Clinic c1 = new Clinic(id.next(), "göz hastalıkları");
        Clinic c2 = new Clinic(id.next(), "diş hekimliği");
        Clinic c3 = new Clinic(id.next(), "kardiyoloji");
        Clinic c4 = new Clinic(id.next(), "nöroloji");
        Clinic c5 = new Clinic(id.next(), "nefroloji");
        clinics.put(c1.getId(), c1);
        clinics.put(c2.getId(), c2);
        clinics.put(c3.getId(), c3);
        clinics.put(c4.getId(), c4);
        clinics.put(c5.getId(), c5);

        // Users
        Admin admin = new Admin(id.next(), "ali", "123", "ali");
        users.put(admin.getId(), admin);

        Doctor d1 = new Doctor(id.next(), "fatih", "123", "fatih", c1.getId());
        Doctor d2 = new Doctor(id.next(), "sedat", "123", "sedat", c1.getId());
        users.put(d1.getId(), d1);
        users.put(d2.getId(), d2);

        Patient p1 = new Patient(id.next(), "yusuf", "123", "yusuf", "11111");
        Patient p2 = new Patient(id.next(), "enes", "123", "enes", "22222");
        users.put(p1.getId(), p1);
        users.put(p2.getId(), p2);

        // Seed some doctor slots (like screenshots)
        addSlot(d1.getId(), LocalDateTime.now().withHour(9).withMinute(0).withSecond(0).withNano(0).plusDays(1));
        addSlot(d1.getId(), LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0).plusDays(1));
        addSlot(d2.getId(), LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).withNano(0).plusDays(1));
    }

    public synchronized long addClinic(String name) {
        Clinic c = new Clinic(id.next(), name);
        clinics.put(c.getId(), c);
        return c.getId();
    }

    public synchronized long addDoctor(String username, String pass, String fullName, long clinicId) {
        Doctor d = new Doctor(id.next(), username, pass, fullName, clinicId);
        users.put(d.getId(), d);
        return d.getId();
    }

    public synchronized long addSlot(long doctorId, LocalDateTime dt) {
        WorkingSlot s = new WorkingSlot(id.next(), doctorId, dt, SlotStatus.OPEN);
        slots.put(s.getId(), s);
        return s.getId();
    }

    public synchronized Optional<BaseUser> authByUsernamePass(String u, String p) {
        return users.values().stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(u) && x.getPassword().equals(p))
                .findFirst();
    }

    public synchronized List<Clinic> listClinics() {
        return new ArrayList<>(clinics.values());
    }

    public synchronized List<Doctor> listDoctorsByClinic(long clinicId) {
        return users.values().stream()
                .filter(x -> x instanceof Doctor)
                .map(x -> (Doctor) x)
                .filter(d -> d.getClinicId() == clinicId)
                .collect(Collectors.toList());
    }

    public synchronized List<Doctor> listDoctorsAll() {
        return users.values().stream()
                .filter(x -> x instanceof Doctor)
                .map(x -> (Doctor) x)
                .collect(Collectors.toList());
    }

    public synchronized List<WorkingSlot> listSlotsByDoctor(long doctorId) {
        return slots.values().stream()
                .filter(s -> s.getDoctorId() == doctorId)
                .sorted(Comparator.comparing(WorkingSlot::getDateTime))
                .collect(Collectors.toList());
    }

    public synchronized List<WorkingSlot> listOpenSlotsByDoctor(long doctorId) {
        return slots.values().stream()
                .filter(s -> s.getDoctorId() == doctorId && s.getStatus() == SlotStatus.OPEN)
                .sorted(Comparator.comparing(WorkingSlot::getDateTime))
                .collect(Collectors.toList());
    }

    public synchronized List<Appointment> listAppointmentsAll() {
        return appointments.values().stream()
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());
    }

    public synchronized List<Appointment> listAppointmentsByPatient(long patientId) {
        return appointments.values().stream()
                .filter(a -> a.getPatientId() == patientId)
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());
    }

    public synchronized List<Appointment> listAppointmentsByDoctor(long doctorId) {
        return appointments.values().stream()
                .filter(a -> a.getDoctorId() == doctorId)
                .sorted(Comparator.comparing(Appointment::getDateTime))
                .collect(Collectors.toList());
    }

    public synchronized boolean book(long slotId, long patientId) {
        WorkingSlot slot = slots.get(slotId);
        if (slot == null || slot.getStatus() != SlotStatus.OPEN) return false;

        Doctor d = (Doctor) users.get(slot.getDoctorId());
        long clinicId = d.getClinicId();

        slot.setStatus(SlotStatus.BOOKED);
        Appointment ap = new Appointment(id.next(), clinicId, slot.getDoctorId(), patientId, slot.getDateTime());
        appointments.put(ap.getId(), ap);
        return true;
    }

    public synchronized BaseUser getUser(long id) { return users.get(id); }
    public synchronized Clinic getClinic(long id) { return clinics.get(id); }
}
