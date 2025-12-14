//Kontrol Eden Öğretmene Notlar: 
//Bu projenin belli başlı yerlerinde kullanıcı için görüntüyü güzelleştirmek ve okunabilirliği/anlaşılabilirliği arttırmak amacıyla yapay zekadan yardım alınmıştır. Örnek olarak: README.md dosyasındaki proje yapısı bölümündeki düzenleme çizgileri vb.
//Aleyna Zengin-2025/2026 Öğrenim Dönemi İşletim Sistemleri Dersi Proje Ödevi 
//Herkesçe erişilebilir olması adına ödevin bazı yerlerinde ingilizce ana yazım dili olarak ele alınarak yazılmıştır.

# CPU Scheduling Algorithms – Project Report

## 1. Giriş
Bu proje, CSV formatında sağlanan iki farklı iş yükü senaryosunu kullanarak klasik CPU zamanlama algoritmalarını analiz etmektedir.
Her işlem, arrival time, CPU burst time, and priority level ile tanımlanır.

Ana amaç, standart zamanlama ölçütlerini kullanarak farklı CPU zamanlama algoritmalarının performansını değerlendirmek ve karşılaştırmaktır.

---

## 2. Input Cases

### Case 1
- Total Processes: 200
- CPU Burst Times: Increasing from 1 to 20
- Arrival Times: Regular intervals (every 2 units)
- Priority Levels: Cyclic distribution (high, normal, low)

### Case 2
- Total Processes: 100
- CPU Burst Times: Variable (1–20)
- Arrival Times: Same structure as Case 1
- Priority Levels: Cyclic distribution (high, normal, low)

Bu iki örnek, hem öngörülebilir hem de düzensiz iş yükleri altında değerlendirme yapılmasına olanak tanır.

---

## 3. Implemented Scheduling Algorithms

### 3.1 First Come First Served (FCFS)
İşlemler geliş sırasına göre yürütülür.
- Simple implementation
- High average waiting time due to convoy effect

### 3.2 Shortest Job First (Non-Preemptive)
En kısa işlemci kullanım süresine sahip işlem seçilir.
- Minimizes average waiting time
- Requires knowledge of CPU burst time

### 3.3 Shortest Job First (Preemptive)
Daha kısa süreli bir iş geldiğinde, halihazırda devam eden işlem kesintiye uğrayabilir.
- Improved responsiveness
- Increased context switching overhead

### 3.4 Round Robin (Quantum = 4)
Her işleme sabit bir zaman dilimi atanır.
- Fair CPU distribution
- Higher number of context switches

### 3.5 Priority Scheduling (Non-Preemptive)
Süreçler önceliğe göre planlanır.
- Lower-priority processes may starve

### 3.6 Priority Scheduling (Preemptive)
Daha yüksek öncelikli işlemler, çalışan işlemlerin önüne geçebilir.
- Suitable for real-time systems
- Highest context switching cost

---

## 4. Performance Metrics

Her algoritma ve durum için aşağıdaki ölçütler hesaplanır:

- Maximum Waiting Time
- Average Waiting Time
- Maximum Turnaround Time
- Average Turnaround Time
- Throughput for T = [50, 100, 150, 200]
- CPU Utilization
- Total Context Switch Count

Context switch duration is assumed to be **0.001 time units**.

---

## 5. Results and Observations

### General Observations
- FCFS has the highest waiting and turnaround times.
- Preemptive SJF achieves the best average turnaround time.
- Round Robin improves fairness but increases overhead.
- Priority scheduling may cause starvation.

### Case Comparison
- Case 1 produces more predictable results.
- Case 2 shows higher variance due to irregular CPU bursts.

---

## 6. Conclusion
CPU zamanlama algoritmaları verimlilik, adalet ve ek yük arasında bir denge kurmayı gerektirir.
En uygun algoritma, yanıt verme hızı veya verim gibi sistem hedeflerine bağlıdır.
---

## 7. Reproducibility
1. Place CSV files into the `src/` directory
2. Run the program
3. Output files will be generated in the `CPU_Scheduling_Project_with_results/` directory
---

## Multi-Thread Bölümü
Bu projede, CPU zamanlama algoritmalarının performansını artırmak ve gerçek işletim sistemi davranışına daha yakın bir simülasyon oluşturmak amacıyla çoklu thread (multithreading) yaklaşımı kullanılmıştır.

7.1 Amaç
Ödev tanımında belirtilen: “Her yöntem ayrı bir thread olarak ve tüm yöntemler eş zamanlı olarak çalışmalı” şartını sağlamak amacıyla, her CPU zamanlama algoritması ayrı bir thread içerisinde çalıştırılmıştır.
Bu yaklaşım sayesinde:
• Algoritmalar birbirini beklemeden çalıştırılmıştır
• Hesaplama süreleri paralel hale getirilmiştir
• Gerçek çok çekirdekli sistem davranışı simüle edilmişti

