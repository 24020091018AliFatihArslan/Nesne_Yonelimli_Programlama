package tr.edu.istiklal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basit sepet modeli.
 * UI katmanından bağımsız olarak ürün ekleme/silme ve toplam hesaplama işini taşır.
 */
public class SepetCart {

    private final List<Urun> items = new ArrayList<>();

    public void add(Urun urun) {
        if (urun == null) {
            throw new IllegalArgumentException("Ürün null olamaz");
        }
        items.add(urun);
    }

    public Urun removeAt(int index) {
        return items.remove(index);
    }

    public int size() {
        return items.size();
    }

    public List<Urun> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * KDV dahil toplam tutar.
     */
    public double totalWithKdv() {
        double total = 0.0;
        for (Urun u : items) {
            total += u.kdvHesapla();
        }
        return total;
    }
}
