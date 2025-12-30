package mayintarlasi;

import java.awt.EventQueue;

public class main {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				mayintarlasi_ frame = new mayintarlasi_();
				frame.setVisible(true);
			} catch (Exception e) {}
		});
	}
}