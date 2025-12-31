package com.ali.hospital;

import com.ali.hospital.ui.LoginFrame;
import com.ali.hospital.ui.UiUtil;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UiUtil.setLaf();
        AppContext ctx = new AppContext();
        SwingUtilities.invokeLater(() -> new LoginFrame(ctx).setVisible(true));
    }
}
