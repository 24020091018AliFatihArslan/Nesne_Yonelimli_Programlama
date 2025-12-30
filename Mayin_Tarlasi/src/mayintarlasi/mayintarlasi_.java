package mayintarlasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class mayintarlasi_ extends JFrame {
	private JPanel panel;
	private Btn[][] b = new Btn[10][10];
	private ImageIcon i1, i2;

	public mayintarlasi_() {
		setTitle("Mayın Tarlası");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		setResizable(false);

		try {
			i1 = new ImageIcon(new ImageIcon(getClass().getResource("a.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			i2 = new ImageIcon(new ImageIcon(getClass().getResource("aa.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		} catch (Exception e) {
			System.out.println("Resim hatası!");
		}

		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(10, 10, 2, 2));
		setContentPane(panel);

		tahtayiKur();
		oyunuHazirla();
	}

	private void tahtayiKur() {
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				Btn btn = new Btn(r, c);
				btn.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						Btn t = (Btn) e.getSource();
						if (!t.isEnabled()) return;
						if (SwingUtilities.isRightMouseButton(e)) {
							if (!t.isBayrak()) {
								t.setIcon(i2);
								t.setBayrak(true);
							} else {
								t.setIcon(null);
								t.setBayrak(false);
							}
						} else {
							tikla(t);
						}
					}
				});
				b[r][c] = btn;
				panel.add(btn);
			}
		}
	}

	public void oyunuHazirla() {
		Random rnd = new Random();
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				b[r][c].setBomba(false);
				b[r][c].setAcik(false);
				b[r][c].setBayrak(false);
				b[r][c].setEnabled(true);
				b[r][c].setText("");
				b[r][c].setIcon(null);
				b[r][c].setBackground(new Color(50, 50, 50));
			}
		}
		
		int m = 0;
		while (m < 15) {
			int r = rnd.nextInt(10);
			int c = rnd.nextInt(10);
			if (!b[r][c].isBomba()) {
				b[r][c].setBomba(true);
				m++;
			}
		}

		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (!b[r][c].isBomba()) {
					int s = 0;
					for (int i = r - 1; i <= r + 1; i++) {
						for (int j = c - 1; j <= c + 1; j++) {
							if (i >= 0 && i < 10 && j >= 0 && j < 10 && b[i][j].isBomba()) s++;
						}
					}
					b[r][c].setKomsuSayi(s);
				}
			}
		}
	}

	private void tikla(Btn btn) {
		if (btn.isAcik() || btn.isBayrak()) return;
		btn.setAcik(true);

		if (btn.isBomba()) {
			btn.setIcon(i1);
			btn.setBackground(Color.RED);
			oyunBitti();
		} else {
			btn.setEnabled(false);
			btn.setBackground(new Color(80, 80, 80));
			if (btn.getKomsuSayi() > 0) {
				btn.setText(String.valueOf(btn.getKomsuSayi()));
				if (btn.getKomsuSayi() == 1) btn.setDisabledTextColor(Color.BLUE);
				else if (btn.getKomsuSayi() == 2) btn.setDisabledTextColor(new Color(0, 150, 0));
				else if (btn.getKomsuSayi() == 3) btn.setDisabledTextColor(Color.RED);
				else if (btn.getKomsuSayi() == 4) btn.setDisabledTextColor(new Color(0, 0, 150));
				else btn.setDisabledTextColor(Color.MAGENTA);
			} else {
				for (int i = btn.getSatir() - 1; i <= btn.getSatir() + 1; i++) {
					for (int j = btn.getSutun() - 1; j <= btn.getSutun() + 1; j++) {
						if (i >= 0 && i < 10 && j >= 0 && j < 10) tikla(b[i][j]);
					}
				}
			}
		}
	}

	private void oyunBitti() {
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (b[r][c].isBomba()) b[r][c].setIcon(i1);
				b[r][c].setEnabled(false);
			}
		}
		JOptionPane.showMessageDialog(this, "BOMMM! Oyun Bitti.");
		oyunuHazirla();
	}
}