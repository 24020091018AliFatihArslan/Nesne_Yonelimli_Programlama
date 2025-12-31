package com.ali.hospital.dto;

import com.ali.hospital.domain.SlotStatus;

public class SlotDTO {
    public long id;
    public long doctorId;
    public String doctorName;
    public String dateTime; // yyyy-MM-dd HH:mm
    public SlotStatus status;

    public SlotDTO(long id, long doctorId, String doctorName, String dateTime, SlotStatus status) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.status = status;
    }
}
