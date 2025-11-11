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

    public static boolean createDB(Mahasiswa mhs) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "insert into mahasiswa (nim, nama, tahun_masuk) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mhs.nim);
            ps.setString(2, mhs.nama);
            ps.setInt(3, mhs.tahunMasuk);
            ps.executeUpdate();
            System.out.println("Data untuk NIM " + mhs.nim + " berhasil ditambahkan.");
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
            return false;
        }
    }

    public static List<Mahasiswa> readDB() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        String sql = "select * from mahasiswa order by nim asc";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String resultNim = rs.getString("nim");
                String resultNama = rs.getString("nama");
                int resultTahun = rs.getInt("tahun_masuk");
                Mahasiswa mhs = new Mahasiswa(resultNim, resultNama, resultTahun);

                listMahasiswa.add(mhs);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }
        return listMahasiswa;
    }

    public static boolean updateDB(Mahasiswa mhs) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "update mahasiswa set nama = ?, tahun_masuk = ? where nim = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mhs.nama);
            ps.setInt(2, mhs.tahunMasuk);
            ps.setString(3, mhs.nim);

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

    public static void deleteDB(String nim) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "delete from mahasiswa where nim = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nim);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data dengan NIM " + nim + " berhasil dihapus.");
            } else {
                System.out.println("Gagal menghapus: Data dengan NIM " + nim + " tidak ditemukan.");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}
