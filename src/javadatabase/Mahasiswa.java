package javadatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mahasiswa {

    public String nim;
    public String nama;
    public int tahunMasuk;

    public Mahasiswa(String nim, String nama, int tahunMasuk) {
        this.nim = nim;
        this.nama = nama;
        this.tahunMasuk = tahunMasuk;

    }

}
