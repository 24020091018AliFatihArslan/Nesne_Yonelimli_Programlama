package com.ali.hospital.domain;

import java.time.LocalDateTime;

public class WorkingSlot {
    private final long id;
    private final long doctorId;
    private final LocalDateTime dateTime;
    private SlotStatus status;

    public WorkingSlot(long id, long doctorId, LocalDateTime dateTime, SlotStatus status) {
        this.id = id;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public long getId() { return id; }
    public long getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public SlotStatus getStatus() { return status; }
    public void setStatus(SlotStatus status) { this.status = status; }
}
