# Hastane Otomasyonu (Java Swing)

⚠️ **UYARI:** Bu proje **hala geliştirme aşamasındadır**.  
Kod yapısı ve mimari bilinçli olarak kurulmuştur ancak **eksik özellikler, mantıksal hatalar (buglar) ve UI iyileştirmeleri** bulunabilir.  
Proje **ödev / demo / mimari gösterim** amacıyla hazırlanmıştır.

---

## 📌 Proje Hakkında

Bu uygulama Java dili kullanılarak geliştirilmiş bir **Hastane Otomasyonu** örneğidir.

Projede özellikle şu konular hedeflenmiştir:

- Nesne Yönelimli Programlama (OOP)
- **Kalıtım (Inheritance)** yapısı
- **DTO (Data Transfer Object)** kullanımı
- Katmanlı mimari (UI – Service – Repository)
- Event/Observer mantığı ile **gerçek zamanlı güncelleme**
- **Java Swing** ile sekmeli ve etkileşimli arayüz

> ⚠️ Bu sürüm **veritabanı kullanmaz**, tüm veriler **in-memory (RAM)** üzerinde tutulur.

---

## 🧱 Mimari Yapı

```
UI (Swing)
 ↓
Service Katmanı
 ↓
Repository (In-Memory)
 ↓
Domain (Entity + Inheritance)
 ↓
DTO (UI veri aktarımı)
```

- `BaseUser` → `Doctor`, `Patient`, `Admin`
- UI katmanı **entity değil DTO** ile çalışır
- Randevu alındığında **EventBus** ile tüm tablolar anında güncellenir

---

## 🖥️ Kullanılan Teknolojiler

- Java 8+
- Java Swing
- Eclipse IDE
- OOP (Inheritance, Encapsulation)
- Observer Pattern (EventBus)

---

## ▶️ Çalıştırma

1. Eclipse → **Import → Existing Projects into Workspace**
2. Projeyi workspace’e ekle
3. `src/com/ali/hospital/Main.java`
4. **Run As → Java Application**

Ek bir kurulum veya veritabanı gerekmez.

---

## 👤 Demo Kullanıcılar

### Admin
- **Kullanıcı adı:** ali  
- **Şifre:** 123  

### Doktorlar
- **fatih / 123**
- **sedat / 123**

### Hastalar
- **yusuf / 123**
- **enes / 123**

---

## ⚠️ Bilinen Eksikler / Hatalar

- Veri doğrulama sınırlı
- Geri alma / silme işlemleri yok
- Gerçek veritabanı entegrasyonu yok
- UI responsive değil
- Güvenlik (hash, yetkilendirme) demo seviyesinde

---

## 🚧 Geliştirme Planları (Opsiyonel)

- MariaDB entegrasyonu
- Gerçek kullanıcı yetkilendirme
- TC Kimlik doğrulama
- Randevu çakışma kontrolü
- Tema (Dark / Light)
- EXE çıktısı

---

## 🎓 Not

Bu proje **eğitim ve akademik kullanım** amaçlıdır.  
Gerçek hastane sistemleri için **uygun değildir**.

