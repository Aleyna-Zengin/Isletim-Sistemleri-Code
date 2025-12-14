//Kontrol Eden Öğretmene Notlar:
//Bu projenin belli başlı yerlerinde kullanıcı için görüntüyü güzelleştirmek ve okunabilirliği/anlaşılabilirliği arttırmak amacıyla yapay zekadan yardım alınmıştır. Örnek olarak: README.md dosyasındaki proje yapısı bölümündeki düzenleme çizgileri vb.
//Aleyna Zengin-2025/2026 Öğrenim Dönemi İşletim Sistemleri Dersi Proje Ödevi
//Herkesçe erişilebilir olması adına ödevin bazı yerlerinde ingilizce ana yazım dili olarak ele alınarak yazılmıştır.

# CPU Scheduling Algorithms Project

## Proje Tanımı
Bu proje, işletim sistemlerinde kullanılan temel **CPU zamanlama algoritmalarının** simülasyonunu gerçekleştirmek amacıyla geliştirilmiştir.
Verilen CSV dosyalarındaki süreç bilgileri kullanılarak algoritmalar test edilmiş ve performans karşılaştırmaları yapılmıştır.

Projede **2 farklı case dosyası** ve **6 farklı CPU zamanlama algoritması** uygulanmıştır.

## Kullanılan Zamanlama Algoritmaları
1. FCFS (First Come First Served)
2. Preemptive SJF (Shortest Job First)
3. Non-Preemptive SJF
4. Round Robin (Quantum = 4)
5. Preemptive Priority Scheduling
6. Non-Preemptive Priority Scheduling

## Proje Yapısı
CPU_Scheduling_Project/
├── CPU_Scheduling_Project_with_results/
│   ├── results
│   ├── case1.csv
│   └── case2.csv
├── src/
│   ├── java/cpusched
│   └── resources (case1.csv, case2.csv)
├── README.md
└── REPORT.md

## Girdi (CSV) Formatı
Process_ID,Arrival_Time,CPU_Burst_Time,Priority

## Programın Çalıştırılması
1. CSV dosyalarını data klasörüne ekleyin
2. Java projesini derleyin
3. Programı çalıştırın

## Üretilen Çıktılar
- Zaman Tablosu
- Bekleme Süresi
- Turnaround Süresi
- Throughput
- CPU Verimliliği
- Bağlam Değiştirme Sayısı
