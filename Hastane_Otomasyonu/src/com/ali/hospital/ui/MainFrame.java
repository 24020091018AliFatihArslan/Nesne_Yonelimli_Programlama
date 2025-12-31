package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.domain.Role;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(AppContext ctx, BaseUser me) {
        super("Hastane Yönetim Sistemi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(980, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(8,10,8,10));
        JLabel welcome = new JLabel("Hoşgeldiniz Sayın, " + me.getFullName());
        JButton logout = new JButton("Çıkış Yap");
        logout.addActionListener(e -> {
            dispose();
            new LoginFrame(ctx).setVisible(true);
        });
        top.add(welcome, BorderLayout.WEST);
        top.add(logout, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        if (me.getRole() == Role.PATIENT) {
            tabs.addTab("Randevu Sistemi", new PatientPanel(ctx, me));
        } else if (me.getRole() == Role.DOCTOR) {
            tabs.addTab("Çalışma Saatleri", new DoctorSlotsPanel(ctx, me));
            tabs.addTab("Randevu Listesi", new DoctorAppointmentsPanel(ctx, me));
        } else {
            tabs.addTab("Doktor Yönetimi", new AdminDoctorsPanel(ctx));
            tabs.addTab("Poliklinikler", new AdminClinicsPanel(ctx));
            tabs.addTab("Randevu Listesi", new AdminAppointmentsPanel(ctx));
        }

        add(tabs, BorderLayout.CENTER);
    }
}
