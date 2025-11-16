/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javadatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author afrizal
 */
public class HandlerMahasiswa {

    private Connection conn;

    public HandlerMahasiswa(Connection conn) {
        this.conn = conn;
    }

    public boolean createDB(Mahasiswa mhs) {
        try {
            String sql = "insert into mahasiswa (nim, nama) values (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mhs.nim);
            ps.setString(2, mhs.nama);
            ps.executeUpdate();
            System.out.println("Data untuk NIM " + mhs.nim + " berhasil ditambahkan.");
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
    }

    public List<Mahasiswa> readDB() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        String sql = "select * from mahasiswa order by nim asc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String resultNim = rs.getString("nim");
                String resultNama = rs.getString("nama");
                Mahasiswa mhs = new Mahasiswa(resultNim, resultNama);

                listMahasiswa.add(mhs);
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }
        return listMahasiswa;
    }

    public boolean updateDB(Mahasiswa mhs) {
        try {
            String sql = "update mahasiswa set nama = ? where nim = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mhs.nama);
            ps.setString(2, mhs.nim);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data untuk NIM " + mhs.nim + " berhasil diupdate.");
                return true;
            } else {
                System.out.println("Gagal mengupdate: Data dengan NIM " + mhs.nim + " tidak ditemukan.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data: " + e.getMessage());
            return false;
        }
    }

    public void deleteDB(String nim) {
        try {
            String sql = "delete from mahasiswa where nim = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nim);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data dengan NIM " + nim + " berhasil dihapus.");
            } else {
                System.out.println("Gagal menghapus: Data dengan NIM " + nim + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }

    public void createListDB(List<Mahasiswa> listMahasiswa) {
        String sql = "insert into mahasiswa (nim, nama) values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Mahasiswa mhs : listMahasiswa) {
                ps.setString(1, mhs.nim);
                ps.setString(2, mhs.nama);
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (Exception e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }

    }
}
