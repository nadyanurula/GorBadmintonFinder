package com.example.uas_10119242.model;
import java.io.Serializable;
public class ModelGor implements Serializable {
    private String nama;
    private String alamat;
    private String gambar;
    private double latitude,longitude;
    private String key;

    public ModelGor(){}
    public ModelGor(String nama, String alamat, String gambar,double latitude, double longitude) {
        this.nama = nama;
        this.alamat = alamat;
        this.gambar = gambar;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
