package com.ali.hospital.ui;

import javax.swing.*;
import java.awt.*;

public final class UiUtil {
    private UiUtil() {}

    public static void setLaf() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
    }

    public static void info(Component p, String m){ JOptionPane.showMessageDialog(p, m, "Mesaj", JOptionPane.INFORMATION_MESSAGE); }
    public static void err(Component p, String m){ JOptionPane.showMessageDialog(p, m, "Hata", JOptionPane.ERROR_MESSAGE); }
}
