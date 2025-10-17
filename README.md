# TP5DPBO2425C2


# Aplikasi CRUD Data Produk Sederhana

Aplikasi desktop sederhana yang dibuat menggunakan Java Swing untuk mengelola data produk. Aplikasi ini menerapkan operasi **CRUD (Create, Read, Update, Delete)** yang terhubung langsung ke database MySQL/MariaDB.

## Desain Program

Program ini dibangun dengan struktur yang memisahkan antara tampilan, logika, dan koneksi database untuk kemudahan pengembangan.

* **Bahasa & Teknologi:**
    * **Java**: Bahasa pemrograman utama.
    * **Java Swing**: Untuk membangun antarmuka pengguna grafis (GUI).
    * **JDBC (Java Database Connectivity)**: Untuk menghubungkan aplikasi dengan database.
    * **MySQL/MariaDB**: Sebagai sistem manajemen database untuk menyimpan data produk.

* **Struktur Kelas:**
    1.  `ProductMenu.java`: Kelas utama yang bertindak sebagai **View** dan **Controller**. Kelas ini bertanggung jawab untuk:
        * Menampilkan semua komponen GUI (tabel, form input, tombol).
        * Menangani semua interaksi dari pengguna (klik tombol, pilih baris tabel).
        * Memanggil metode dari kelas `Database` untuk memanipulasi data.

    2.  `Database.java`: Kelas **helper** yang bertanggung jawab untuk semua hal terkait database:
        * Membangun dan mengelola koneksi ke server MySQL.
        * Menyediakan metode untuk menjalankan query SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`).

    3.  `Product.java`: Kelas **Model (POJO - Plain Old Java Object)** yang merepresentasikan entitas sebuah produk. Kelas ini memiliki atribut seperti `id`, `nama`, `harga`, `kategori`, dan `stok`.

---

## Penjelasan Alur Program

Berikut adalah alur penggunaan aplikasi dari awal hingga akhir.

1.  **Menampilkan Data (Read)**
    * Saat aplikasi pertama kali dijalankan, program akan otomatis terhubung ke database.
    * Semua data produk yang ada di tabel `product` akan diambil dan ditampilkan di dalam `JTable`.
    * Pengguna dapat melihat seluruh data produk yang tersimpan.

2.  **Menambah Data Baru (Create)**
    * Pengguna mengisi semua kolom input pada form (ID Produk, Nama, Harga, Kategori, Stok).
    * Setelah semua terisi, pengguna menekan tombol **"Add"**.
    * Program akan melakukan validasi:
        * Memastikan tidak ada kolom yang kosong.
        * Memastikan ID Produk belum pernah digunakan sebelumnya.
        * Memastikan harga yang dimasukkan adalah angka yang valid.
    * Jika validasi berhasil, data baru akan disimpan ke database dan tabel akan otomatis diperbarui.

3.  **Mengubah Data (Update)**
    * Pengguna mengklik salah satu baris data pada tabel.
    * Data dari baris tersebut akan otomatis muncul di form input. Tombol "Add" berubah menjadi **"Update"**, dan tombol "Delete" muncul.
    * Pengguna mengubah data yang diinginkan melalui form.
    * Setelah selesai, pengguna menekan tombol **"Update"**.
    * Program akan menyimpan perubahan ke database berdasarkan ID produk asli.
    * Data pada baris tabel yang dipilih akan langsung diperbarui tanpa memuat ulang seluruh tabel.

4.  **Menghapus Data (Delete)**
    * Pengguna memilih data yang akan dihapus dengan mengklik baris pada tabel.
    * Pengguna menekan tombol **"Delete"**.
    * Sebuah dialog konfirmasi akan muncul untuk memastikan pengguna yakin ingin menghapus data tersebut.
    * Jika pengguna memilih "Yes", data akan dihapus dari database, dan tabel akan diperbarui.

5.  **Tombol Cancel**
    * Tombol **"Cancel"** berfungsi untuk membersihkan semua isian pada form dan mengembalikan state form ke mode "Add" (mode tambah data).

---

## Dokumentasi Program (Screenshot)

Berikut adalah dokumentasi tangkapan layar saat program menjalankan semua fungsi CRUD.

### 1. Tampilan Awal (Read)
* Menampilkan semua data dari database saat program pertama kali dijalankan.
* <img width="1027" height="912" alt="Screenshot 2025-10-17 132823" src="https://github.com/user-attachments/assets/25194410-ad78-4c8e-9ad5-1f2add4f73d4" />




### 2. Proses Penambahan Data (Create)
* Form diisi dengan data produk baru, kemudian menekan tombol "Add".
* <img width="1026" height="860" alt="Screenshot 2025-10-17 132855" src="https://github.com/user-attachments/assets/ff0b2d6a-e5c2-4171-bee2-f45d6deef732" />
* <img width="1021" height="883" alt="Screenshot 2025-10-17 132904" src="https://github.com/user-attachments/assets/ac04a602-59c2-4234-b2d2-65d2b246eda0" />
* <img width="1023" height="860" alt="Screenshot 2025-10-17 132912" src="https://github.com/user-attachments/assets/bb5f9825-8b7c-4d58-b918-775b9dc2b052" />

gk bisa tambah data klo id nya sama
<img width="1010" height="872" alt="Screenshot 2025-10-17 133022" src="https://github.com/user-attachments/assets/911bd309-23f7-4330-a2be-dba4d385c656" />






### 3. Proses Perubahan Data (Update)
* Memilih salah satu data, mengubah isinya, lalu menekan tombol "Update".
* <img width="996" height="862" alt="Screenshot 2025-10-17 132947" src="https://github.com/user-attachments/assets/e9de2626-68d6-484d-9bb7-9b97b33069cf" />
* <img width="1011" height="887" alt="Screenshot 2025-10-17 132955" src="https://github.com/user-attachments/assets/7e1da331-249e-4eea-92fe-fe05849df1ed" />



### 4. Proses Penghapusan Data (Delete)
* Memilih salah satu data, menekan tombol "Delete", dan konfirmasi penghapusan.
* <img width="1040" height="882" alt="Screenshot 2025-10-17 132924" src="https://github.com/user-attachments/assets/aaadd8e6-33ce-46fb-90c8-d068e6ff4697" />
* <img width="1007" height="859" alt="Screenshot 2025-10-17 132933" src="https://github.com/user-attachments/assets/70ce361f-7993-41d7-a627-a989bcf19676" />
