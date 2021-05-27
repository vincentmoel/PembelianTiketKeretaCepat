/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Vincent Nathaniel
 */
public class mPenumpang {
    private int id_penumpang;
    private String nik;
    private String nama;
    private String jk;
    private String alamat;
    private String jamberangkat;
    
    public int getId() {
        return id_penumpang;
    }

    public void setId(int id_penumpang) {
        this.id_penumpang = id_penumpang;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJamberangkat() {
        return jamberangkat;
    }

    public void setJamberangkat(String jamberangkat) {
        this.jamberangkat = jamberangkat;
    }
    
    
}
