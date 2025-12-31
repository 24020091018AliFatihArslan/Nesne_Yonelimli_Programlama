package com.ali.hospital.ui;

import com.ali.hospital.AppContext;
import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.domain.Patient;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final AppContext ctx;

    private final JTextField patientTc = new JTextField(12);
    private final JPasswordField patientPass = new JPasswordField(12);

    private final JTextField doctorUser = new JTextField(12);
    private final JPasswordField doctorPass = new JPasswordField(12);

    private final JTextField adminUser = new JTextField(12);
    private final JPasswordField adminPass = new JPasswordField(12);

    public LoginFrame(AppContext ctx) {
        super("Hastane Otomasyonu");
        this.ctx = ctx;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        title.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));

        add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Hasta Girişi", buildPatientTab());
        tabs.addTab("Doktor Girişi", buildDoctorTab());
        tabs.addTab("Admin", buildAdminTab());

        add(tabs, BorderLayout.CENTER);
    }

    private JPanel buildPatientTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8,8,8,8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx=0; gc.gridy=0; p.add(new JLabel("T.C Numaranız :"), gc);
        gc.gridx=1; p.add(patientTc, gc);

        gc.gridx=0; gc.gridy=1; p.add(new JLabel("Şifre :"), gc);
        gc.gridx=1; p.add(patientPass, gc);

        JButton login = new JButton("Giriş Yap");
        login.addActionListener(e -> doPatientLogin());
        gc.gridx=1; gc.gridy=2; gc.anchor = GridBagConstraints.CENTER;
        p.add(login, gc);

        return p;
    }

    private JPanel buildDoctorTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8,8,8,8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx=0; gc.gridy=0; p.add(new JLabel("Kullanıcı Adı :"), gc);
        gc.gridx=1; p.add(doctorUser, gc);

        gc.gridx=0; gc.gridy=1; p.add(new JLabel("Şifre :"), gc);
        gc.gridx=1; p.add(doctorPass, gc);

        JButton login = new JButton("Giriş Yap");
        login.addActionListener(e -> doDoctorLogin());
        gc.gridx=1; gc.gridy=2; gc.anchor = GridBagConstraints.CENTER;
        p.add(login, gc);

        return p;
    }

    private JPanel buildAdminTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8,8,8,8);
        gc.anchor = GridBagConstraints.WEST;

        gc.gridx=0; gc.gridy=0; p.add(new JLabel("Kullanıcı Adı :"), gc);
        gc.gridx=1; p.add(adminUser, gc);

        gc.gridx=0; gc.gridy=1; p.add(new JLabel("Şifre :"), gc);
        gc.gridx=1; p.add(adminPass, gc);

        JButton login = new JButton("Giriş Yap");
        login.addActionListener(e -> doAdminLogin());
        gc.gridx=1; gc.gridy=2; gc.anchor = GridBagConstraints.CENTER;
        p.add(login, gc);

        return p;
    }

    private void doPatientLogin() {
        String tc = patientTc.getText().trim();
        String pass = new String(patientPass.getPassword());
        // Patient uses tc as username in this demo
        BaseUser user = ctx.auth.login(findPatientUsernameByTc(tc), pass).orElse(null);
        if (user == null || !(user instanceof Patient)) {
            UiUtil.info(this, "Hatalı Hasta Bilgisi!");
            return;
        }
        openMain(user);
    }

    private String findPatientUsernameByTc(String tc) {
        // demo mapping: tc == stored tc OR tc matches username
        // We'll scan store for patients matching tc, else use tc as username.
        for (var u : ctx.store.users.values()) {
            if (u instanceof Patient) {
                Patient p = (Patient) u;
                if (p.getTcNo().equals(tc)) return p.getUsername();
            }
        }
        return tc;
    }

    private void doDoctorLogin() {
        String u = doctorUser.getText().trim();
        String pass = new String(doctorPass.getPassword());
        BaseUser user = ctx.auth.login(u, pass).orElse(null);
        if (user == null || user.getRole() != com.ali.hospital.domain.Role.DOCTOR) {
            UiUtil.err(this, "Hatalı Doktor Bilgisi!");
            return;
        }
        openMain(user);
    }

    private void doAdminLogin() {
        String u = adminUser.getText().trim();
        String pass = new String(adminPass.getPassword());
        BaseUser user = ctx.auth.login(u, pass).orElse(null);
        if (user == null || user.getRole() != com.ali.hospital.domain.Role.ADMIN) {
            UiUtil.err(this, "Hatalı Admin Bilgisi!");
            return;
        }
        openMain(user);
    }

    private void openMain(BaseUser user) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame(ctx, user);
            mf.setVisible(true);
            dispose();
        });
    }
}
