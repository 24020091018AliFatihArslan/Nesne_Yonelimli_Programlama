package mayintarlasi;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Btn extends JButton {
	private int satir, sutun, komsuSayi;
	private boolean bomba, acik, bayrak;

	public Btn(int r, int c) {
		this.satir = r;
		this.sutun = c;
		this.bomba = false;
		this.acik = false;
		this.bayrak = false;
		this.komsuSayi = 0;
		
		setBackground(new Color(50, 50, 50));
		setFocusPainted(false);
		setFont(new Font("Arial", Font.BOLD, 15));
	}

	public void setDisabledTextColor(Color renk) {
		super.setForeground(renk);
	}

	public int getSatir() { return satir; }
	public int getSutun() { return sutun; }
	public boolean isBomba() { return bomba; }
	public void setBomba(boolean b) { bomba = b; }
	public boolean isAcik() { return acik; }
	public void setAcik(boolean a) { acik = a; }
	public boolean isBayrak() { return bayrak; }
	public void setBayrak(boolean b) { bayrak = b; }
	public int getKomsuSayi() { return komsuSayi; }
	public void setKomsuSayi(int k) { komsuSayi = k; }
}