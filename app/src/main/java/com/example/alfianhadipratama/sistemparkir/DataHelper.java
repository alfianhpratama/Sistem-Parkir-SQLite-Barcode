package com.example.alfianhadipratama.sistemparkir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sistemparkirdb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABEL_ADMIN      = "adminnya";
    public static final String TABEL_KENDARAAN  = "kendaraan";
    public static final String TABEL_WAKTU      = "waktu";
    public static final String TABEL_HISTORI    = "histori";

    public static final String ID  			 = "id_admin";
    public static final String NAMA_ADMIN 	 = "nama_admin";
    public static final String NAMA_PEMILIK  = "nama_pemilik";
    public static final String PASSWORD      = "password";
    public static final String ALAMAT        = "alamat";
    public static final String PLAT_NOMOR    = "plat_nomor";
    public static final String WAKTU_MASUK   = "waktu_masuk";
    public static final String WAKTU_KELUAR  = "waktu_keluar";
    public static final String BARCODE       = "barcode";

    public static final String CREATE_TABEL_ADMIN = "CREATE TABLE "
            + TABEL_ADMIN + "(" + ID + " VARCHAR(11) NOT NULL PRIMARY KEY, "
            + NAMA_ADMIN + " VARCHAR(20), " + PASSWORD + " VARCHAR(20), " + ALAMAT + " VARCHAR(100), "
            + "no_hp VARCHAR(13))";

    public static final String CREATE_TABEL_KENDARAAN = "CREATE TABLE "
            + TABEL_KENDARAAN + "(" + PLAT_NOMOR + " VARCHAR(10) NOT NULL PRIMARY KEY, "
            + NAMA_PEMILIK + " VARCHAR(30), " + BARCODE + " VARCHAR(30))";

    public static final String CREATE_TABEL_WAKTU = "CREATE TABLE " + TABEL_WAKTU + "(" + NAMA_PEMILIK + " VARCHAR(30), "
            + PLAT_NOMOR + " VARCHAR(10), " + WAKTU_MASUK + " DATE)";

    public static final String CREATE_TABEL_HISTORI = "CREATE TABLE " + TABEL_HISTORI + "(" + NAMA_PEMILIK + " VARCHAR(30), "
            + PLAT_NOMOR + " VARCHAR(10), " + WAKTU_MASUK + " DATE, " + WAKTU_KELUAR + " DATE)";

    private static DataHelper instance;

    public static synchronized DataHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataHelper(context);
        return instance;
    }

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABEL_ADMIN);
        db.execSQL(CREATE_TABEL_KENDARAAN);
        db.execSQL(CREATE_TABEL_WAKTU);
        db.execSQL(CREATE_TABEL_HISTORI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}