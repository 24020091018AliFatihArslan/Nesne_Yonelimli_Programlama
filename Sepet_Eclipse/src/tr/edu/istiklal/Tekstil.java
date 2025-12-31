
package tr.edu.istiklal;


public class Tekstil extends Urun {
    private String kumasTuru;
    private int beden;
    private String ureticiFirma;

    public Tekstil(String urunAdi, double urunFiyati,String kumasTuru, int beden, String ureticiFirma) {
        super(urunAdi,urunFiyati);
        this.kumasTuru = kumasTuru;
        this.beden = beden;
        this.ureticiFirma = ureticiFirma;
    }

    @Override
    public double kdvHesapla() {
        return getUrunFiyati() *1.10;
    }

    @Override
    public String toString() {
       return getUreticiFirma() + " " + getKumasTuru() + " (Beden: " + getBeden() + ") - " + getUrunFiyati() + " TL";
    }

    public String getKumasTuru() {
        return kumasTuru;
    }


    public void setKumasTuru(String kumasTuru) {
        this.kumasTuru = kumasTuru;
    }


    public int getBeden() {
        return beden;
    }


    public void setBeden(int beden) {
        this.beden = beden;
    }


    public String getUreticiFirma() {
        return ureticiFirma;
    }

    public void setUreticiFirma(String ureticiFirma) {
        this.ureticiFirma = ureticiFirma;
    }
    
}
