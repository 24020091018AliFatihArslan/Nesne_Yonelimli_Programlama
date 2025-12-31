package com.ali.hospital.domain;

import java.time.LocalDateTime;

public class Appointment {
    private final long id;
    private final long clinicId;
    private final long doctorId;
    private final long patientId;
    private final LocalDateTime dateTime;

    public Appointment(long id, long clinicId, long doctorId, long patientId, LocalDateTime dateTime) {
        this.id = id;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
    }

    public long getId() { return id; }
    public long getClinicId() { return clinicId; }
    public long getDoctorId() { return doctorId; }
    public long getPatientId() { return patientId; }
    public LocalDateTime getDateTime() { return dateTime; }
}
