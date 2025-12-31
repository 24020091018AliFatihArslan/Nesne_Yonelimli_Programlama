package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventListener;
import com.ali.hospital.event.EventType;
import com.ali.hospital.ui.table.AppointmentTableModel;

import javax.swing.*;
import java.awt.*;

public class DoctorAppointmentsPanel extends JPanel implements EventListener {
    private final AppContext ctx;
    private final BaseUser me;

    private final AppointmentTableModel model = new AppointmentTableModel();
    private final JTable table = new JTable(model);

    public DoctorAppointmentsPanel(AppContext ctx, BaseUser me) {
        this.ctx = ctx;
        this.me = me;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(new JScrollPane(table), BorderLayout.CENTER);

        ctx.bus.subscribe(this);
        refresh();
    }

    private void refresh() {
        model.setRows(ctx.appointment.listByDoctor(me.getId()));
    }

    @Override
    public void onEvent(AppEvent e) {
        if (e.type == EventType.DATA_CHANGED) SwingUtilities.invokeLater(this::refresh);
    }
}
