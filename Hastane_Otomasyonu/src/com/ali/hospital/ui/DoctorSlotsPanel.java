package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.dto.SlotDTO;
import com.ali.hospital.event.AppEvent;
import com.ali.hospital.event.EventListener;
import com.ali.hospital.event.EventType;
import com.ali.hospital.ui.table.SlotTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DoctorSlotsPanel extends JPanel implements EventListener {
    private final AppContext ctx;
    private final BaseUser me;

    private final JTextField date = new JTextField(10); // yyyy-MM-dd
    private final JComboBox<String> hour = new JComboBox<>();
    private final JButton add = new JButton("Ekle");

    private final SlotTableModel model = new SlotTableModel();
    private final JTable table = new JTable(model);

    public DoctorSlotsPanel(AppContext ctx, BaseUser me) {
        this.ctx = ctx;
        this.me = me;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        top.add(new JLabel("Tarih:"));
        top.add(date);
        top.add(new JLabel("Saat:"));
        for (int h=8; h<=17; h++) hour.addItem(String.format("%02d:00", h));
        hour.setSelectedItem("14:00");
        top.add(hour);
        top.add(add);

        add.addActionListener(e -> addSlot());

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        ctx.bus.subscribe(this);
        refresh();
    }

    private void addSlot() {
        try {
            LocalDate d = LocalDate.parse(date.getText().trim());
            String hhmm = (String) hour.getSelectedItem();
            LocalTime t = LocalTime.parse(hhmm);
            LocalDateTime dt = LocalDateTime.of(d, t);
            ctx.slot.addSlot(me.getId(), dt);
            UiUtil.info(this, "Slot eklendi.");
            refresh();
        } catch (Exception ex) {
            UiUtil.err(this, "Tarih formatı: yyyy-MM-dd (örn 2025-12-05)");
        }
    }

    private void refresh() {
        java.util.List<SlotDTO> rows = ctx.slot.listSlotsByDoctor(me.getId());
        model.setRows(rows);
    }

    @Override
    public void onEvent(AppEvent e) {
        if (e.type == EventType.DATA_CHANGED) SwingUtilities.invokeLater(this::refresh);
    }
}
