package tr.edu.istiklal;

public abstract class Urun {
   private String urunAdi;
   private double urunFiyati;

    public Urun(String urunAdi, double urunFiyati) {
        this.setUrunAdi(urunAdi);
        this.setUrunFiyati(urunFiyati);
    }
   

    public String getUrunAdi() {
        return urunAdi;
    }

    public double getUrunFiyati() {
        return urunFiyati;
    }
    
   public abstract double kdvHesapla(); 
    
   @Override
   public String toString() {
       return getUrunAdi();
   }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public void setUrunFiyati(double urunFiyati) {
        this.urunFiyati = urunFiyati;
    }
}