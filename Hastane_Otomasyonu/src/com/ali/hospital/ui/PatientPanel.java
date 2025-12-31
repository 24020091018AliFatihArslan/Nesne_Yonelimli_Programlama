package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.dto.ClinicDTO;
import com.ali.hospital.dto.DoctorDTO;
import com.ali.hospital.dto.SlotDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventListener;
import com.ali.hospital.event.EventType;
import com.ali.hospital.ui.table.ClinicTableModel;
import com.ali.hospital.ui.table.DoctorTableModel;
import com.ali.hospital.ui.table.SlotTableModel;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel implements EventListener {
    private final AppContext ctx;
    private final BaseUser me;

    private final ClinicTableModel clinicModel = new ClinicTableModel();
    private final JTable clinicTable = new JTable(clinicModel);

    private final DoctorTableModel doctorModel = new DoctorTableModel();
    private final JTable doctorTable = new JTable(doctorModel);

    private final SlotTableModel slotModel = new SlotTableModel();
    private final JTable slotTable = new JTable(slotModel);

    public PatientPanel(AppContext ctx, BaseUser me) {
        this.ctx = ctx;
        this.me = me;

        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel center = new JPanel(new GridLayout(1,3,10,10));

        center.add(wrap("Poliklinik Adı", clinicTable));
        center.add(buildActions());
        center.add(wrap("Doktor Adı", doctorTable));

        add(center, BorderLayout.CENTER);
        add(wrap("Doktor: Slotlar", slotTable), BorderLayout.SOUTH);

        ctx.bus.subscribe(this);
        refreshClinics();
    }

    private JPanel buildActions() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JButton pick = new JButton("Poliklinik Seç");
        pick.setAlignmentX(Component.CENTER_ALIGNMENT);
        pick.addActionListener(e -> refreshDoctors());

        JButton book = new JButton("Randevu Al");
        book.setAlignmentX(Component.CENTER_ALIGNMENT);
        book.addActionListener(e -> bookSelected());

        p.add(Box.createVerticalGlue());
        p.add(pick);
        p.add(Box.createVerticalStrut(12));
        p.add(book);
        p.add(Box.createVerticalGlue());
        return p;
    }

    private JScrollPane wrap(String title, JTable t) {
        JScrollPane sp = new JScrollPane(t);
        sp.setBorder(BorderFactory.createTitledBorder(title));
        t.setFillsViewportHeight(true);
        return sp;
    }

    private void refreshClinics() {
        clinicModel.setRows(ctx.clinic.listClinics());
        doctorModel.setRows(java.util.List.of());
        slotModel.setRows(java.util.List.of());
    }

    private void refreshDoctors() {
        int r = clinicTable.getSelectedRow();
        if (r < 0) {
            UiUtil.info(this, "Poliklinik seçiniz.");
            return;
        }
        ClinicDTO c = clinicModel.getAt(r);
        java.util.List<DoctorDTO> doctors = ctx.doctor.listDoctorsByClinic(c.id);
        doctorModel.setRows(doctors);
        slotModel.setRows(java.util.List.of());
    }

    private void refreshSlotsForSelectedDoctor() {
        int r = doctorTable.getSelectedRow();
        if (r < 0) return;
        DoctorDTO d = doctorModel.getAt(r);
        slotModel.setRows(ctx.slot.listOpenSlotsByDoctor(d.id));
    }

    private void bookSelected() {
        // pick doctor first
        int dr = doctorTable.getSelectedRow();
        if (dr < 0) {
            UiUtil.info(this, "Doktor seçiniz.");
            return;
        }
        refreshSlotsForSelectedDoctor();

        int sr = slotTable.getSelectedRow();
        if (sr < 0) {
            UiUtil.info(this, "Slot seçiniz.");
            return;
        }
        SlotDTO slot = slotModel.getAt(sr);
        boolean ok = ctx.appointment.book(slot.id, me.getId());
        if (!ok) {
            UiUtil.err(this, "Bu slot artık uygun değil.");
            refreshSlotsForSelectedDoctor();
            return;
        }
        UiUtil.info(this, "Randevu alındı.");
        refreshSlotsForSelectedDoctor();
    }

    @Override
    public void onEvent(AppEvent e) {
        if (e.type == EventType.DATA_CHANGED) SwingUtilities.invokeLater(() -> {
            refreshClinics();
            refreshDoctors();
            refreshSlotsForSelectedDoctor();
        });
    }
}
