import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // Standar setup untuk menjalankan frame
        ProductMenu menu = new ProductMenu();
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int selectedIndex = -1; // -1 berarti tidak ada baris yang dipilih
    private String originalId = null;
    private Database database;

    // Komponen GUI
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel kategoriLabel;
    private JSlider stokSlider;

    // ----------------------------------------------------
    // KONSTRUKTOR UTAMA
    // ----------------------------------------------------
    public ProductMenu() {
        database = new Database(); // Inisialisasi koneksi database
        productTable.setModel(setTable()); // Isi tabel saat program pertama kali berjalan

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // Atur isi combo box
        String[] kategoriData = {"Pilih Kategori", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        deleteButton.setVisible(false); // Sembunyikan tombol delete pada awalnya

        // --- Event Listeners ---

        // Tombol ADD / UPDATE
        addUpdateButton.addActionListener(e -> {
            if (selectedIndex == -1) {
                insertData(); // Jika tidak ada baris dipilih, jalankan fungsi insert
            } else {
                updateData(); // Jika ada baris dipilih, jalankan fungsi update
            }
        });

        // Tombol DELETE
        deleteButton.addActionListener(e -> {
            if (selectedIndex != -1) { // Pastikan ada baris yang dipilih
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tombol CANCEL
        cancelButton.addActionListener(e -> clearForm());

        // Klik pada baris tabel
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = productTable.getSelectedRow(); // Dapatkan indeks baris yang diklik

                // Jika ada baris yang valid diklik
                if (selectedIndex != -1) {
                    // Ambil data dari tabel dan tampilkan di form
                    originalId = productTable.getValueAt(selectedIndex, 1).toString();
                    idField.setText(productTable.getValueAt(selectedIndex, 1).toString());
                    namaField.setText(productTable.getValueAt(selectedIndex, 2).toString());
                    hargaField.setText(productTable.getValueAt(selectedIndex, 3).toString());
                    kategoriComboBox.setSelectedItem(productTable.getValueAt(selectedIndex, 4).toString());
                    stokSlider.setValue(Integer.parseInt(productTable.getValueAt(selectedIndex, 5).toString()));

                    // Ubah tombol "Add" menjadi "Update" dan tampilkan tombol "Delete"
                    addUpdateButton.setText("Update");
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    // ----------------------------------------------------
    // AMBIL SEMUA DATA DARI DATABASE DAN TAMPILKAN DI TABEL
    // ----------------------------------------------------
    public final DefaultTableModel setTable() {
        Object[] cols = {"No", "ID Produk", "Nama", "Harga", "Kategori", "Stok"};
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try {
            // Ambil data dari database
            ResultSet rs = database.selectQuery("SELECT * FROM product");
            int i = 1; // Variabel untuk penomoran otomatis
            while (rs.next()) {
                tmp.addRow(new Object[]{
                        i++, // Tampilkan nomor urut
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getDouble("harga"),
                        rs.getString("kategori"),
                        rs.getInt("stok")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return tmp;
    }

    // ----------------------------------------------------
    // TAMBAH DATA BARU KE DATABASE
    // ----------------------------------------------------
    public void insertData() {
        // Ambil data dari form dan hilangkan spasi di awal/akhir
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaStr = hargaField.getText().trim();
        String kategori = (String) kategoriComboBox.getSelectedItem();
        int stok = stokSlider.getValue();

        // 1. Validasi input kosong
        if (id.isEmpty() || nama.isEmpty() || hargaStr.isEmpty() || kategori.equals("Pilih Kategori")) {
            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // 2. Validasi: harga harus berupa angka
            double harga = Double.parseDouble(hargaStr);

            // 3. Cek duplikasi ID di database
            ResultSet rs = database.selectQuery("SELECT * FROM product WHERE id='" + id + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "ID Produk sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 4. Buat query SQL untuk INSERT
            // BARU (Benar)
            String sql = String.format(Locale.US,
                    "INSERT INTO product (id, nama, harga, kategori, stok) VALUES ('%s', '%s', %f, '%s', %d)",
                    id, nama, harga, kategori, stok
            );

            // 5. Eksekusi query
            database.insertUpdateDeleteQuery(sql);

            // 6. Jika berhasil, refresh tabel dan bersihkan form
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ----------------------------------------------------
// UPDATE DATA PRODUK DI DATABASE
// ----------------------------------------------------
    public void updateData() {
        // Ambil ID baru dari form
        String newId = idField.getText().trim();
        String nama = namaField.getText().trim();
        String hargaStr = hargaField.getText().trim();
        String kategori = (String) kategoriComboBox.getSelectedItem();
        int stok = stokSlider.getValue();

        // 1. Validasi input kosong
        if (newId.isEmpty() || nama.isEmpty() || hargaStr.isEmpty() || kategori.equals("Pilih Kategori")) {
            JOptionPane.showMessageDialog(null, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double harga = Double.parseDouble(hargaStr);

            // 2. Validasi duplikasi ID HANYA JIKA ID diubah
            if (!newId.equals(originalId)) {
                ResultSet rs = database.selectQuery("SELECT * FROM product WHERE id='" + newId + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "ID Produk baru sudah terdaftar, gunakan ID lain!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // 3. Buat query SQL yang juga MENGUBAH ID
            //    SET id='ID_BARU' ... WHERE id='ID_LAMA'
            String sql = String.format(Locale.US,
                    "UPDATE product SET id='%s', nama='%s', harga=%f, kategori='%s', stok=%d WHERE id='%s'",
                    newId, nama, harga, kategori, stok, originalId // Gunakan originalId di WHERE
            );

            // 4. Eksekusi query
            database.insertUpdateDeleteQuery(sql);

            // 5. Update baris di tabel secara langsung
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.setValueAt(newId, selectedIndex, 1);       // Perbarui kolom ID
            model.setValueAt(nama, selectedIndex, 2);
            model.setValueAt(harga, selectedIndex, 3);
            model.setValueAt(kategori, selectedIndex, 4);
            model.setValueAt(stok, selectedIndex, 5);

            // 6. Bersihkan form
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ----------------------------------------------------
    // HAPUS DATA PRODUK DARI DATABASE
    // ----------------------------------------------------
    public void deleteData() {
        String id = idField.getText().trim();

        try {
            // 1. Buat dan eksekusi query DELETE
            database.insertUpdateDeleteQuery("DELETE FROM product WHERE id='" + id + "'");

            // 2. Jika berhasil, refresh tabel dan bersihkan form
            productTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ----------------------------------------------------
    // RESET FORM INPUT KE KONDISI AWAL
    // ----------------------------------------------------
    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        stokSlider.setValue(50); // Reset slider ke nilai default
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1; // Reset status baris terpilih
        originalId = null;
    }
}