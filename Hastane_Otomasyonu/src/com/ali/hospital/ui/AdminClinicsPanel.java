package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventListener;
import com.ali.hospital.event.EventType;
import com.ali.hospital.ui.table.ClinicTableModel;

import javax.swing.*;
import java.awt.*;

public class AdminClinicsPanel extends JPanel implements EventListener {
    private final AppContext ctx;

    private final ClinicTableModel model = new ClinicTableModel();
    private final JTable table = new JTable(model);

    private final JTextField name = new JTextField(16);

    public AdminClinicsPanel(AppContext ctx) {
        this.ctx = ctx;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        form.add(new JLabel("Poliklinik Adı"));
        form.add(name);
        JButton add = new JButton("Ekle");
        add.addActionListener(e -> addClinic());
        form.add(add);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        ctx.bus.subscribe(this);
        refresh();
    }

    private void addClinic() {
        String n = name.getText().trim();
        if (n.isEmpty()) { UiUtil.err(this, "İsim boş olamaz."); return; }
        ctx.clinic.addClinic(n);
        UiUtil.info(this, "Poliklinik eklendi.");
        name.setText("");
        refresh();
    }

    private void refresh() {
        model.setRows(ctx.clinic.listClinics());
    }

    @Override
    public void onEvent(AppEvent e) {
        if (e.type == EventType.DATA_CHANGED) SwingUtilities.invokeLater(this::refresh);
    }
}
