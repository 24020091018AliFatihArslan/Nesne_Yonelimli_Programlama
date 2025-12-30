package hesap_makinesi;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class hesap_makinesi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private double ilkSayi;
	private double ikinciSayi;
	private double sonuc;
	private String operator;

	Color anaRenk = new Color(25, 25, 25);
	Color sayiRenk = new Color(45, 45, 45);
	Color turkuvazAna = new Color(0, 197, 205);
	Color turkuvazKoyu = new Color(0, 134, 139);
	Color temizleRenk = new Color(220, 53, 69);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hesap_makinesi frame = new hesap_makinesi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public hesap_makinesi() {
		setType(Type.UTILITY);
		setTitle("Hesap Makinesi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 570);
		contentPane = new JPanel();
		contentPane.setBackground(anaRenk);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(anaRenk);
		panel.setBounds(10, 11, 364, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(35, 35, 35));
		textField.setForeground(Color.WHITE);
		textField.setBorder(new LineBorder(turkuvazAna, 1));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Segoe UI", Font.BOLD, 24));
		textField.setText("");
		textField.setBounds(10, 22, 344, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(turkuvazAna);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBackground(anaRenk);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 0, 344, 22);
		panel.add(lblNewLabel);
		
		JPanel control = new JPanel();
		control.setBackground(anaRenk);
		control.setBounds(10, 82, 364, 438);
		contentPane.add(control);
		control.setLayout(new GridLayout(4, 4, 15, 15));
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setBackground(sayiRenk);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.setBackground(sayiRenk);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_1.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setBackground(sayiRenk);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_2.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("+");
		btnNewButton_3.setBackground(turkuvazAna);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_3.setBorder(new LineBorder(turkuvazKoyu));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ilkSayi = Double.parseDouble(textField.getText());
				operator = "+";
				lblNewLabel.setText(textField.getText() + " +");
				textField.setText("");
			}
		});
		control.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setBackground(sayiRenk);
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_4.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setBackground(sayiRenk);
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_5.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setBackground(sayiRenk);
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setFocusPainted(false);
		btnNewButton_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_6.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_6);
		
		JButton btnNewButton_8 = new JButton("-");
		btnNewButton_8.setBackground(turkuvazAna);
		btnNewButton_8.setForeground(Color.WHITE);
		btnNewButton_8.setFocusPainted(false);
		btnNewButton_8.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_8.setBorder(new LineBorder(turkuvazKoyu));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ilkSayi = Double.parseDouble(textField.getText());
				operator = "-";
				lblNewLabel.setText(textField.getText() + " -");
				textField.setText("");
			}
		});
		control.add(btnNewButton_8);
		
		JButton btnNewButton_7 = new JButton("1");
		btnNewButton_7.setBackground(sayiRenk);
		btnNewButton_7.setForeground(Color.WHITE);
		btnNewButton_7.setFocusPainted(false);
		btnNewButton_7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_7.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_7);
		
		JButton btnNewButton_9 = new JButton("2");
		btnNewButton_9.setBackground(sayiRenk);
		btnNewButton_9.setForeground(Color.WHITE);
		btnNewButton_9.setFocusPainted(false);
		btnNewButton_9.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_9.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("3");
		btnNewButton_10.setBackground(sayiRenk);
		btnNewButton_10.setForeground(Color.WHITE);
		btnNewButton_10.setFocusPainted(false);
		btnNewButton_10.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_10.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_10);
		
		JButton btnNewButton_11_1 = new JButton("*");
		btnNewButton_11_1.setBackground(turkuvazAna);
		btnNewButton_11_1.setForeground(Color.WHITE);
		btnNewButton_11_1.setFocusPainted(false);
		btnNewButton_11_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_11_1.setBorder(new LineBorder(turkuvazKoyu));
		btnNewButton_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ilkSayi = Double.parseDouble(textField.getText());
				operator = "*";
				lblNewLabel.setText(textField.getText() + " *");
				textField.setText("");
			}
		});
		control.add(btnNewButton_11_1);
		
		JButton btnNewButton_11 = new JButton("0");
		btnNewButton_11.setBackground(sayiRenk);
		btnNewButton_11.setForeground(Color.WHITE);
		btnNewButton_11.setFocusPainted(false);
		btnNewButton_11.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_11.setBorder(new LineBorder(sayiRenk.brighter()));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + e.getActionCommand());
			}
		});
		control.add(btnNewButton_11);
		
		JButton btnNewButton_11_2 = new JButton("=");
		btnNewButton_11_2.setBackground(turkuvazAna);
		btnNewButton_11_2.setForeground(Color.WHITE);
		btnNewButton_11_2.setFocusPainted(false);
		btnNewButton_11_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_11_2.setBorder(new LineBorder(turkuvazKoyu));
		btnNewButton_11_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ikinciSayi = Double.parseDouble(textField.getText());
				if (operator.equals("+")) sonuc = ilkSayi + ikinciSayi;
				else if (operator.equals("-")) sonuc = ilkSayi - ikinciSayi;
				else if (operator.equals("*")) sonuc = ilkSayi * ikinciSayi;
				else if (operator.equals("%")) sonuc = ilkSayi % ikinciSayi;
				lblNewLabel.setText(lblNewLabel.getText() + " " + ikinciSayi + " =");
				textField.setText(String.valueOf(sonuc));
			}
		});
		control.add(btnNewButton_11_2);
		
		JButton btnNewButton_11_3 = new JButton("C");
		btnNewButton_11_3.setBackground(temizleRenk);
		btnNewButton_11_3.setForeground(Color.WHITE);
		btnNewButton_11_3.setFocusPainted(false);
		btnNewButton_11_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_11_3.setBorder(new LineBorder(temizleRenk.darker()));
		btnNewButton_11_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				lblNewLabel.setText("");
				ilkSayi = 0;
				ikinciSayi = 0;
			}
		});
		control.add(btnNewButton_11_3);
		
		JButton btnNewButton_11_4 = new JButton("%");
		btnNewButton_11_4.setBackground(turkuvazAna);
		btnNewButton_11_4.setForeground(Color.WHITE);
		btnNewButton_11_4.setFocusPainted(false);
		btnNewButton_11_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton_11_4.setBorder(new LineBorder(turkuvazKoyu));
		btnNewButton_11_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ilkSayi = Double.parseDouble(textField.getText());
				operator = "%";
				lblNewLabel.setText(textField.getText() + " %");
				textField.setText("");
			}
		});
		control.add(btnNewButton_11_4);

	}
}