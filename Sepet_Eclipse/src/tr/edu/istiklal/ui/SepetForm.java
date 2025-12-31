package tr.edu.istiklal.ui;

import tr.edu.istiklal.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SepetForm extends JFrame {

    private JLabel lbl1, lbl2;
    private JComboBox<String> cmbUrunTipi;
    private JComboBox<String> cmbAlan1, cmbAlan2;
    private JButton btnEkle, btnSil;
    private JList<Urun> listSepet;
    private DefaultListModel<Urun> listModel;
    private JLabel lblToplam;

    private final tr.edu.istiklal.SepetCart cart = new tr.edu.istiklal.SepetCart();
    private final DecimalFormat moneyFmt = new DecimalFormat("0.00");

    public SepetForm() {
        setTitle("Sepet Uygulaması");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ================= ÜST PANEL =================
        JPanel ustPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        cmbUrunTipi = new JComboBox<>(new String[]{
                "Cep Telefonu", "Ekmek", "Tekstil"
        });

        lbl1 = new JLabel();
        lbl2 = new JLabel();

        cmbAlan1 = new JComboBox<>();
        cmbAlan2 = new JComboBox<>();

        btnEkle = new JButton("Ekle");

        ustPanel.add(new JLabel("Ürün Tipi"));
        ustPanel.add(lbl1);
        ustPanel.add(lbl2);

        ustPanel.add(cmbUrunTipi);
        ustPanel.add(cmbAlan1);
        ustPanel.add(cmbAlan2);

        add(ustPanel, BorderLayout.NORTH);

        // ================= ORTA =================
        listModel = new DefaultListModel<>();
        listSepet = new JList<>(listModel);
        add(new JScrollPane(listSepet), BorderLayout.CENTER);

        // ================= ALT =================
        JPanel altPanel = new JPanel(new FlowLayout());

        btnSil = new JButton("Seçiliyi Sil");
        lblToplam = new JLabel("Toplam: 0 TL");

        altPanel.add(btnEkle);
        altPanel.add(btnSil);
        altPanel.add(lblToplam);

        add(altPanel, BorderLayout.SOUTH);

        // ================= LISTENER =================
        cmbUrunTipi.addItemListener(e -> alanlariGuncelle());
        cmbAlan1.addItemListener(e -> ikinciAlanGuncelle());

        btnEkle.addActionListener(e -> urunEkle());
        btnSil.addActionListener(e -> urunSil());

        alanlariGuncelle();
    }

    // Ürün tipine göre alanları doldurur
    private void alanlariGuncelle() {
        cmbAlan1.removeAllItems();
        cmbAlan2.removeAllItems();

        String secim = cmbUrunTipi.getSelectedItem().toString();

        if (secim.equals("Cep Telefonu")) {
            lbl1.setText("Marka");
            lbl2.setText("Model");

            cmbAlan1.addItem("Samsung");
            cmbAlan1.addItem("Apple");
            cmbAlan1.addItem("Xiaomi");

        } else if (secim.equals("Ekmek")) {
            lbl1.setText("Ekmek Türü");
            lbl2.setText("Gramaj");

            cmbAlan1.addItem("Beyaz");
            cmbAlan1.addItem("Tam Buğday");
            cmbAlan1.addItem("Kepek");

            cmbAlan2.addItem("200");
            cmbAlan2.addItem("250");
            cmbAlan2.addItem("500");
            cmbAlan2.addItem("1000");

        } else if (secim.equals("Tekstil")) {
            lbl1.setText("Kumaş Türü");
            lbl2.setText("Beden");

            cmbAlan1.addItem("Pamuk");
            cmbAlan1.addItem("Kot");
            cmbAlan1.addItem("Yün");

            cmbAlan2.addItem("36");
            cmbAlan2.addItem("38");
            cmbAlan2.addItem("40");
            cmbAlan2.addItem("42");
        }
    }

    // Cep telefonu için marka seçilince model doldurur
    private void ikinciAlanGuncelle() {
        cmbAlan2.removeAllItems();

        if (!cmbUrunTipi.getSelectedItem().equals("Cep Telefonu")) return;

        String marka = (String) cmbAlan1.getSelectedItem();
        if (marka == null) return;

        if (marka.equals("Samsung")) {
            cmbAlan2.addItem("S23");
            cmbAlan2.addItem("A54");
        } else if (marka.equals("Apple")) {
            cmbAlan2.addItem("iPhone 14");
            cmbAlan2.addItem("iPhone 15");
        } else if (marka.equals("Xiaomi")) {
            cmbAlan2.addItem("Redmi 12");
            cmbAlan2.addItem("Note 13");
        }
    }

    private void urunEkle() {
        try {
            String tip = cmbUrunTipi.getSelectedItem().toString();
            Urun urun = null;

            if (tip.equals("Cep Telefonu")) {
                urun = new CepTelefonu(
                        "Cep Telefonu",
                        10000,
                        cmbAlan1.getSelectedItem().toString(),
                        cmbAlan2.getSelectedItem().toString()
                );
            }
            else if (tip.equals("Ekmek")) {
                urun = new Ekmek(
                        "Ekmek",
                        10,
                        cmbAlan1.getSelectedItem().toString(),
                        Double.parseDouble(cmbAlan2.getSelectedItem().toString())
                );
            }
            else if (tip.equals("Tekstil")) {
                urun = new Tekstil(
                        "Tekstil",
                        300,
                        cmbAlan1.getSelectedItem().toString(),
                        Integer.parseInt(cmbAlan2.getSelectedItem().toString()),
                        "Firma"
                );
            }

            cart.add(urun);
            listModel.addElement(urun);
            toplamGuncelle();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seçimleri kontrol et!");
        }
    }

    private void urunSil() {
        int index = listSepet.getSelectedIndex();
        if (index != -1) {
            cart.removeAt(index);
            listModel.remove(index);
            toplamGuncelle();
        }
    }

    private void toplamGuncelle() {
        double toplam = cart.totalWithKdv();
        lblToplam.setText("Toplam: " + moneyFmt.format(toplam) + " TL");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SepetForm().setVisible(true));
    }
}
