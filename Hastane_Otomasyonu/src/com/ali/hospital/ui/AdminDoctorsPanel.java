package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.dto.ClinicDTO;
import com.ali.hospital.dto.DoctorDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventListener;
import com.ali.hospital.event.EventType;
import com.ali.hospital.ui.table.DoctorTableModel;

import javax.swing.*;
import java.awt.*;

public class AdminDoctorsPanel extends JPanel implements EventListener {
    private final AppContext ctx;

    private final DoctorTableModel model = new DoctorTableModel();
    private final JTable table = new JTable(model);

    private final JTextField fullName = new JTextField(12);
    private final JTextField username = new JTextField(10);
    private final JTextField pass = new JTextField(8);
    private final JComboBox<ClinicDTO> clinicCombo = new JComboBox<>();

    public AdminDoctorsPanel(AppContext ctx) {
        this.ctx = ctx;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        form.add(new JLabel("Ad Soyad"));
        form.add(fullName);
        form.add(new JLabel("TC NO/username"));
        form.add(username);
        form.add(new JLabel("Şifre"));
        form.add(pass);
        form.add(new JLabel("Poliklinik"));
        form.add(clinicCombo);

        JButton addBtn = new JButton("Ekle");
        addBtn.addActionListener(e -> addDoctor());
        form.add(addBtn);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        ctx.bus.subscribe(this);
        loadClinics();
        refresh();
    }

    private void loadClinics() {
        clinicCombo.removeAllItems();
        for (ClinicDTO c : ctx.clinic.listClinics()) clinicCombo.addItem(c);
    }

    private void addDoctor() {
        ClinicDTO c = (ClinicDTO) clinicCombo.getSelectedItem();
        if (c == null) { UiUtil.err(this, "Poliklinik seçiniz."); return; }
        if (fullName.getText().trim().isEmpty() || username.getText().trim().isEmpty() || pass.getText().trim().isEmpty()) {
            UiUtil.err(this, "Alanlar boş olamaz.");
            return;
        }
        ctx.doctor.addDoctor(username.getText().trim(), pass.getText().trim(), fullName.getText().trim(), c.id);
        UiUtil.info(this, "Doktor eklendi.");
        fullName.setText(""); username.setText(""); pass.setText("");
        refresh();
    }

    private void refresh() {
        java.util.List<DoctorDTO> doctors = ctx.doctor.listAll();
        model.setRows(doctors);
    }

    @Override
    public void onEvent(AppEvent e) {
        if (e.type == EventType.DATA_CHANGED) SwingUtilities.invokeLater(() -> { loadClinics(); refresh(); });
    }
}
