package tr.edu.istiklal;

public class Ekmek  extends Urun{
    private String ekmekTuru;
    private double gramaj;

    public Ekmek(String urunAdi, double urunFiyati,String ekmekTuru, double gramaj) {
      super(urunAdi,urunFiyati);
        this.ekmekTuru = ekmekTuru;
        this.gramaj = gramaj;
    }


    public String getEkmekTuru() {
        return ekmekTuru;
    }

    public double getGramaj() {
        return gramaj;
    }

    @Override
    public double kdvHesapla() {
        return getUrunFiyati();
    }
     @Override
    public String toString() {
         return ekmekTuru + " " + gramaj + " g - " + getUrunFiyati() + " TL";
    }

    public void setEkmekTuru(String ekmekTuru) {
        this.ekmekTuru = ekmekTuru;
    }

    public void setGramaj(double gramaj) {
        this.gramaj = gramaj;
    }
 
}