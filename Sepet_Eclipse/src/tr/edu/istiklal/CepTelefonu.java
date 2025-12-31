
package tr.edu.istiklal;

public class CepTelefonu extends Urun{
    private String marka;
    private String model;

    public CepTelefonu(String urunAdi, double urunFiyati,String marka, String model) {
       super(urunAdi,urunFiyati);
        this.marka = marka;
        this.model = model;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    @Override
    public double kdvHesapla() {
       return getUrunFiyati() *1.18;
    }
     @Override
    public String toString() {
      return this.marka + " " + this.model + " - " + this.getUrunFiyati() + " TL";
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
}