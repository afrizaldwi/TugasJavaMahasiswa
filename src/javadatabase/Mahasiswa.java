package javadatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mahasiswa {

    public String nim;
    public String nama;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;

    }

}
