package com.mycompany.kasirkantinkampus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KasirKantinKampus extends JFrame {
    // Komponen Swing
    private JTextField txtNamaBarang;
    private JTextField txtHargaSatuan;
    private JTextField txtJumlah;
    private JTextField txtUangBayar;
    private JLabel lblTotalHarga;
    private JLabel lblKembalian;
    private JButton btnHitung;
    private JButton btnBayar;
    private JButton btnReset;

    public KasirKantinKampus() {
        // Setting JFrame
        setTitle("Aplikasi Kasir Kantin Kampus");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 2, 10, 10));

        // Inisialisasi Komponen
        txtNamaBarang = new JTextField();
        txtHargaSatuan = new JTextField();
        txtJumlah = new JTextField();
        txtUangBayar = new JTextField();
        lblTotalHarga = new JLabel("Total Harga : Rp 0");
        lblKembalian = new JLabel("Uang Kembalian : Rp 0");
        btnHitung = new JButton("Hitung");
        btnBayar = new JButton("Bayar");
        btnReset = new JButton("Reset");

        // Tambah Komponen ke Frame
        add(new JLabel("Nama Makanan / Minuman :"));
        add(txtNamaBarang);
        add(new JLabel("Harga Satuan (Rp) :"));
        add(txtHargaSatuan);
        add(new JLabel("Jumlah Pembelian :"));
        add(txtJumlah);
        add(new JLabel("Uang Pembayaran (Rp) :"));
        add(txtUangBayar);
        add(lblTotalHarga);
        add(lblKembalian);
        add(btnHitung);
        add(btnBayar);
        add(btnReset);

        // Event Tombol Hitung
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTotal();
            }
        });

        // Event Tombol Bayar
        btnBayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungKembalian();
            }
        });

        // Event Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    // Fungsi Hitung Total Harga
    private void hitungTotal() {
        try {
            String namaBarang = txtNamaBarang.getText().trim();
            if (namaBarang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Isi Nama Barang!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int hargaSatuan = Integer.parseInt(txtHargaSatuan.getText().trim());
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());

            if (hargaSatuan <= 0) {
                JOptionPane.showMessageDialog(this, "Harga Satuan Harus Lebih Dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah Pembelian Harus Lebih Dari 0!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int total = hargaSatuan * jumlah;
            lblTotalHarga.setText("Total Harga : Rp " + total);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan Angka Yang Benar!", "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi Hitung Kembalian
    private void hitungKembalian() {
        try {
            int hargaSatuan = Integer.parseInt(txtHargaSatuan.getText().trim());
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            int uangBayar = Integer.parseInt(txtUangBayar.getText().trim());
            int total = hargaSatuan * jumlah;

            if (uangBayar < total) {
                JOptionPane.showMessageDialog(this, "Pembayaran Kurang! Kurang Rp " + (total - uangBayar), "Peringatan", JOptionPane.WARNING_MESSAGE);
                lblKembalian.setText("Uang Kembalian : Rp 0");
                return;
            }
            int kembalian = uangBayar - total;
            lblKembalian.setText("Uang Kembalian : Rp " + kembalian);
            JOptionPane.showMessageDialog(this, "Pembayaran Berhasil ✅ Kembalian Rp " + kembalian);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan Angka Yang Benar!", "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi Reset Form
    private void resetForm() {
        txtNamaBarang.setText("");
        txtHargaSatuan.setText("");
        txtJumlah.setText("");
        txtUangBayar.setText("");
        lblTotalHarga.setText("Total Harga : Rp 0");
        lblKembalian.setText("Uang Kembalian : Rp 0");
        txtNamaBarang.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KasirKantinKampus frame = new KasirKantinKampus();
            frame.setVisible(true);
        });
    }
}